package com.jid.springwise.balance.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.balance.model.balance.*;
import com.jid.springwise.balance.model.statement.*;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@ExtendWith(MockitoExtension.class)
class WiseBalanceClientTest {

    private static final String BASE_URL = "https://api.wise.com";
    private static final String API_TOKEN = "test-token";

    @Mock private WiseClient wiseClient;

    private MockRestServiceServer mockServer;
    private WiseBalanceClient client;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = WiseApiMapper.getMapper();

        RestClient.Builder restClientBuilder = RestClient.builder()
            .baseUrl(BASE_URL)
            .defaultHeader("Authorization", "Bearer " + API_TOKEN)
            .messageConverters(converters -> {
                converters.clear();
                converters.add(new MappingJackson2HttpMessageConverter(mapper));
            });

        mockServer = MockRestServiceServer.bindTo(restClientBuilder).build();

        when(wiseClient.getRestClient()).thenReturn(restClientBuilder.build());
        client = new WiseBalanceClient(wiseClient);
    }

    @AfterEach
    void tearDown() {
        mockServer.verify();
    }

    @Test
    void createBalance_sendsPostWithCorrectPathAndHeaders() throws Exception {
        WiseBalance balance = WiseBalance.builder()
            .id(1L)
            .currency("GBP")
            .type(WiseBalanceType.STANDARD)
            .build();

        mockServer.expect(requestTo(BASE_URL + "/v4/profiles/123/balances"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(header("X-idempotence-uuid", "test-uuid"))
            .andExpect(header("Content-Type", containsString("application/json")))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(balance), MediaType.APPLICATION_JSON));

        CreateBalanceRequest request = CreateBalanceRequest.builder()
            .profileId(123L)
            .idempotenceUuid("test-uuid")
            .balance(WiseBalanceCreate.builder()
                .currency("GBP")
                .type(WiseBalanceType.STANDARD)
                .build())
            .build();

        CreateBalanceResponse response = client.createBalance(request);

        assertEquals(1L, response.getBalance().getId());
        assertEquals("GBP", response.getBalance().getCurrency());
        assertEquals(WiseBalanceType.STANDARD, response.getBalance().getType());
    }

    @Test
    void createBalance_sendsBalanceBodyInRequest() throws Exception {
        WiseBalance balance = WiseBalance.builder().id(2L).currency("EUR").build();

        mockServer.expect(requestTo(BASE_URL + "/v4/profiles/456/balances"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string(containsString("\"currency\":\"EUR\"")))
            .andExpect(content().string(containsString("\"type\":\"SAVINGS\"")))
            .andExpect(content().string(containsString("\"name\":\"My Savings\"")))
            .andRespond(withSuccess(mapper.writeValueAsString(balance), MediaType.APPLICATION_JSON));

        CreateBalanceRequest request = CreateBalanceRequest.builder()
            .profileId(456L)
            .idempotenceUuid("uuid-2")
            .balance(WiseBalanceCreate.builder()
                .currency("EUR")
                .type(WiseBalanceType.SAVINGS)
                .name("My Savings")
                .build())
            .build();

        client.createBalance(request);
    }

    @Test
    void getBalances_sendsGetWithProfileIdAndTypesQueryParam() throws Exception {
        List<WiseBalance> balances = List.of(
            WiseBalance.builder().id(1L).currency("GBP").build(),
            WiseBalance.builder().id(2L).currency("USD").build()
        );

        mockServer.expect(requestTo(containsString("/v4/profiles/123/balances")))
            .andExpect(requestTo(containsString("types=STANDARD")))
            .andExpect(method(HttpMethod.GET))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(balances), MediaType.APPLICATION_JSON));

        GetBalancesRequest request = GetBalancesRequest.builder()
            .profileId(123L)
            .types("STANDARD")
            .build();

        GetBalancesResponse response = client.getBalances(request);

        assertEquals(2, response.getBalances().size());
        assertEquals("GBP", response.getBalances().get(0).getCurrency());
        assertEquals("USD", response.getBalances().get(1).getCurrency());
    }

    @Test
    void getBalance_sendsGetWithProfileIdAndBalanceId() throws Exception {
        WiseBalance balance = WiseBalance.builder()
            .id(42L)
            .currency("EUR")
            .type(WiseBalanceType.SAVINGS)
            .build();

        mockServer.expect(requestTo(BASE_URL + "/v4/profiles/123/balances/42"))
            .andExpect(method(HttpMethod.GET))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(balance), MediaType.APPLICATION_JSON));

        GetBalanceRequest request = GetBalanceRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .build();

        GetBalanceResponse response = client.getBalance(request);

        assertEquals(42L, response.getBalance().getId());
        assertEquals("EUR", response.getBalance().getCurrency());
        assertEquals(WiseBalanceType.SAVINGS, response.getBalance().getType());
    }

    @Test
    void deleteBalance_sendsDeleteAndReturnsSuccess() throws Exception {
        mockServer.expect(requestTo(BASE_URL + "/v4/profiles/123/balances/42"))
            .andExpect(method(HttpMethod.DELETE))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andRespond(withStatus(HttpStatus.NO_CONTENT));

        DeleteBalanceRequest request = DeleteBalanceRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .build();

        DeleteBalanceResponse response = client.deleteBalance(request);

        assertTrue(response.isSuccess());
    }

    @Test
    void moveBalanceFunds_sendsPostWithCorrectPathAndHeaders() throws Exception {
        WiseBalanceMovementResponse movement = WiseBalanceMovementResponse.builder()
            .id(99L)
            .type("CONVERSION")
            .state("COMPLETED")
            .rate(new BigDecimal("1.25"))
            .build();

        mockServer.expect(requestTo(BASE_URL + "/v2/profiles/123/balance-movements"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(header("X-idempotence-uuid", "move-uuid"))
            .andExpect(header("Content-Type", containsString("application/json")))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(movement), MediaType.APPLICATION_JSON));

        MoveBalanceFundsRequest request = MoveBalanceFundsRequest.builder()
            .profileId(123L)
            .idempotenceUuid("move-uuid")
            .balanceMovement(WiseBalanceMovement.builder()
                .sourceBalanceId(1L)
                .targetBalanceId(2L)
                .amount(new WiseMoney(new BigDecimal("100.00"), "GBP"))
                .build())
            .build();

        MoveBalanceFundsResponse response = client.moveBalanceFunds(request);

        assertEquals(99L, response.getMovement().getId());
        assertEquals("COMPLETED", response.getMovement().getState());
        assertEquals(new BigDecimal("1.25"), response.getMovement().getRate());
    }

    @Test
    void moveBalanceFunds_sendsMovementBodyInRequest() throws Exception {
        mockServer.expect(requestTo(BASE_URL + "/v2/profiles/1/balance-movements"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string(containsString("\"quoteId\":\"q-123\"")))
            .andExpect(content().string(containsString("\"sourceBalanceId\":10")))
            .andExpect(content().string(containsString("\"targetBalanceId\":20")))
            .andRespond(withSuccess(
                mapper.writeValueAsString(WiseBalanceMovementResponse.builder().id(1L).build()),
                MediaType.APPLICATION_JSON));

        MoveBalanceFundsRequest request = MoveBalanceFundsRequest.builder()
            .profileId(1L)
            .idempotenceUuid("u")
            .balanceMovement(WiseBalanceMovement.builder()
                .quoteId("q-123")
                .sourceBalanceId(10L)
                .targetBalanceId(20L)
                .build())
            .build();

        client.moveBalanceFunds(request);
    }

    @Test
    void getBalanceCapacity_sendsGetWithCurrencyQueryParam() throws Exception {
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder()
            .hasLimit(true)
            .depositLimit(WiseDepositLimit.builder()
                .amount(new BigDecimal("10000.00"))
                .currency("GBP")
                .build())
            .build();

        mockServer.expect(requestTo(containsString("/v1/profiles/123/balance-capacity")))
            .andExpect(requestTo(containsString("currency=GBP")))
            .andExpect(method(HttpMethod.GET))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(capacity), MediaType.APPLICATION_JSON));

        GetBalanceCapacityRequest request = GetBalanceCapacityRequest.builder()
            .profileId(123L)
            .currency("GBP")
            .build();

        GetBalanceCapacityResponse response = client.getBalanceCapacity(request);

        assertTrue(response.getCapacity().getHasLimit());
        assertEquals(new BigDecimal("10000.00"), response.getCapacity().getDepositLimit().getAmount());
    }

    @Test
    void addExcessMoneyAccount_sendsPostWithNoIdempotenceHeader() throws Exception {
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder()
            .userProfileId(123L)
            .recipientId(456L)
            .build();

        mockServer.expect(requestTo(BASE_URL + "/v1/profiles/123/excess-money-account"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(headerDoesNotExist("X-idempotence-uuid"))
            .andExpect(header("Content-Type", containsString("application/json")))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(account), MediaType.APPLICATION_JSON));

        AddExcessMoneyAccountRequest request = AddExcessMoneyAccountRequest.builder()
            .profileId(123L)
            .excessMoneyAccount(WiseExcessMoneyAccountCreate.builder().recipientId(456L).build())
            .build();

        AddExcessMoneyAccountResponse response = client.addExcessMoneyAccount(request);

        assertEquals(123L, response.getExcessMoneyAccount().getUserProfileId());
        assertEquals(456L, response.getExcessMoneyAccount().getRecipientId());
    }

    @Test
    void addExcessMoneyAccount_sendsBodyInRequest() throws Exception {
        mockServer.expect(requestTo(BASE_URL + "/v1/profiles/1/excess-money-account"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string(containsString("\"recipientId\":789")))
            .andRespond(withSuccess(
                mapper.writeValueAsString(WiseExcessMoneyAccount.builder().build()),
                MediaType.APPLICATION_JSON));

        AddExcessMoneyAccountRequest request = AddExcessMoneyAccountRequest.builder()
            .profileId(1L)
            .excessMoneyAccount(WiseExcessMoneyAccountCreate.builder().recipientId(789L).build())
            .build();

        client.addExcessMoneyAccount(request);
    }

    @Test
    void getTotalFunds_sendsGetWithProfileIdAndCurrencyInPath() throws Exception {
        WiseTotalFunds totalFunds = WiseTotalFunds.builder()
            .totalWorth(new WiseMoney(new BigDecimal("5000.00"), "GBP"))
            .totalAvailable(new WiseMoney(new BigDecimal("4500.00"), "GBP"))
            .build();

        mockServer.expect(requestTo(BASE_URL + "/v1/profiles/123/total-funds/GBP"))
            .andExpect(method(HttpMethod.GET))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(totalFunds), MediaType.APPLICATION_JSON));

        GetTotalFundsRequest request = GetTotalFundsRequest.builder()
            .profileId(123L)
            .currency("GBP")
            .build();

        GetTotalFundsResponse response = client.getTotalFunds(request);

        assertEquals(new BigDecimal("5000.00"), response.getTotalFunds().getTotalWorth().getValue());
        assertEquals("GBP", response.getTotalFunds().getTotalWorth().getCurrency());
    }

    @Test
    void getBalanceStatement_sendsGetWithRequiredQueryParams() throws Exception {
        WiseBalanceStatement statement = WiseBalanceStatement.builder()
            .query(WiseStatementQuery.builder()
                .currency("GBP")
                .accountId(42L)
                .build())
            .transactions(List.of())
            .build();

        mockServer.expect(requestTo(containsString("/v1/profiles/123/balance-statements/42/statement.json")))
            .andExpect(requestTo(containsString("currency=GBP")))
            .andExpect(requestTo(containsString("intervalStart=")))
            .andExpect(requestTo(containsString("intervalEnd=")))
            .andExpect(requestTo(not(containsString("type="))))
            .andExpect(requestTo(not(containsString("statementLocale="))))
            .andExpect(method(HttpMethod.GET))
            .andExpect(header("Authorization", "Bearer " + API_TOKEN))
            .andExpect(header("Accept", containsString("application/json")))
            .andRespond(withSuccess(mapper.writeValueAsString(statement), MediaType.APPLICATION_JSON));

        GetBalanceStatementRequest request = GetBalanceStatementRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .currency("GBP")
            .intervalStart("2024-01-01T00:00:00Z")
            .intervalEnd("2024-01-31T23:59:59Z")
            .build();

        GetBalanceStatementResponse response = client.getBalanceStatement(request);

        assertNotNull(response.getStatement());
        assertEquals("GBP", response.getStatement().getQuery().getCurrency());
    }

    @Test
    void getBalanceStatement_withOptionalParams_includesThemInQuery() throws Exception {
        mockServer.expect(requestTo(containsString("/v1/profiles/123/balance-statements/42/statement.json")))
            .andExpect(requestTo(containsString("type=FLAT")))
            .andExpect(requestTo(containsString("statementLocale=en-US")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(
                mapper.writeValueAsString(WiseBalanceStatement.builder().build()),
                MediaType.APPLICATION_JSON));

        GetBalanceStatementRequest request = GetBalanceStatementRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .currency("USD")
            .intervalStart("2024-06-01T00:00:00Z")
            .intervalEnd("2024-06-30T23:59:59Z")
            .type(WiseStatementType.FLAT)
            .statementLocale("en-US")
            .build();

        GetBalanceStatementResponse response = client.getBalanceStatement(request);

        assertNotNull(response);
    }

}
