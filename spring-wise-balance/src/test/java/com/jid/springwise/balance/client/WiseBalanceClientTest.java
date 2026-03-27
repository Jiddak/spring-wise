package com.jid.springwise.balance.client;

import com.jid.springwise.balance.model.balance.*;
import com.jid.springwise.balance.model.statement.*;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.core.WiseClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SuppressWarnings({"rawtypes", "unchecked"})
@ExtendWith(MockitoExtension.class)
class WiseBalanceClientTest {

    @Mock private RestClient restClient;
    @Mock private WiseClient wiseClient;
    @Mock private RestClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock private RestClient.RequestBodySpec requestBodySpec;
    @Mock private RestClient.RequestHeadersSpec requestHeadersSpec;
    @Mock private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock private RestClient.ResponseSpec responseSpec;

    private WiseBalanceClient client;

    @BeforeEach
    void setUp() {
        when(wiseClient.getRestClient()).thenReturn(restClient);
        client = new WiseBalanceClient(wiseClient);
    }

    @Test
    void constructor_withConfig_buildsClient() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("test-token")
            .build();
        WiseBalanceClient c = new WiseBalanceClient(config);
        assertNotNull(c);
    }

    @Test
    void createBalance_returnsResponse() {
        WiseBalance balance = WiseBalance.builder().id(1L).currency("GBP").build();
        CreateBalanceRequest request = CreateBalanceRequest.builder()
            .profileId(123L)
            .balance(WiseBalanceCreate.builder().currency("GBP").build())
            .build();

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString(), any(Map.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.header(anyString(), any(String[].class))).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseBalance.class)).thenReturn(ResponseEntity.ok(balance));

        CreateBalanceResponse response = client.createBalance(request);

        assertEquals(balance, response.getBalance());
    }

    @Test
    void createBalance_nullBodyPropagated() {
        CreateBalanceRequest request = CreateBalanceRequest.builder()
            .profileId(123L)
            .balance(WiseBalanceCreate.builder().build())
            .build();

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString(), any(Map.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.header(anyString(), any(String[].class))).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseBalance.class)).thenReturn(ResponseEntity.ok(null));

        CreateBalanceResponse response = client.createBalance(request);

        assertNull(response.getBalance());
    }

    @Test
    void getBalances_returnsResponse() {
        List<WiseBalance> balances = List.of(
            WiseBalance.builder().id(1L).currency("GBP").build(),
            WiseBalance.builder().id(2L).currency("USD").build()
        );
        GetBalancesRequest request = GetBalancesRequest.builder()
            .profileId(123L)
            .types("STANDARD")
            .build();

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(any())).thenReturn(ResponseEntity.ok(balances));

        GetBalancesResponse response = client.getBalances(request);

        assertEquals(2, response.getBalances().size());
        assertEquals("GBP", response.getBalances().get(0).getCurrency());
    }

    @Test
    void getBalance_returnsResponse() {
        WiseBalance balance = WiseBalance.builder().id(42L).currency("EUR").build();
        GetBalanceRequest request = GetBalanceRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .build();

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Map.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseBalance.class)).thenReturn(ResponseEntity.ok(balance));

        GetBalanceResponse response = client.getBalance(request);

        assertEquals(balance, response.getBalance());
        assertEquals(42L, response.getBalance().getId());
    }

    @Test
    void deleteBalance_returnsSuccessResponse() {
        DeleteBalanceRequest request = DeleteBalanceRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .build();

        when(restClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Map.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toBodilessEntity()).thenReturn(ResponseEntity.noContent().build());

        DeleteBalanceResponse response = client.deleteBalance(request);

        assertTrue(response.isSuccess());
    }

    @Test
    void moveBalanceFunds_returnsResponse() {
        WiseBalanceMovementResponse movement = WiseBalanceMovementResponse.builder()
            .id(99L)
            .state("COMPLETED")
            .build();
        MoveBalanceFundsRequest request = MoveBalanceFundsRequest.builder()
            .profileId(123L)
            .balanceMovement(WiseBalanceMovement.builder()
                .sourceBalanceId(1L)
                .targetBalanceId(2L)
                .build())
            .build();

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString(), any(Map.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.header(anyString(), any(String[].class))).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseBalanceMovementResponse.class)).thenReturn(ResponseEntity.ok(movement));

        MoveBalanceFundsResponse response = client.moveBalanceFunds(request);

        assertEquals(movement, response.getMovement());
        assertEquals("COMPLETED", response.getMovement().getState());
    }

    @Test
    void getBalanceCapacity_returnsResponse() {
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder().hasLimit(false).build();
        GetBalanceCapacityRequest request = GetBalanceCapacityRequest.builder()
            .profileId(123L)
            .currency("GBP")
            .build();

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseBalanceCapacity.class)).thenReturn(ResponseEntity.ok(capacity));

        GetBalanceCapacityResponse response = client.getBalanceCapacity(request);

        assertEquals(capacity, response.getCapacity());
        assertFalse(response.getCapacity().getHasLimit());
    }

    @Test
    void addExcessMoneyAccount_returnsResponse() {
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder()
            .userProfileId(123L)
            .recipientId(456L)
            .build();
        AddExcessMoneyAccountRequest request = AddExcessMoneyAccountRequest.builder()
            .profileId(123L)
            .excessMoneyAccount(WiseExcessMoneyAccountCreate.builder().recipientId(456L).build())
            .build();

        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString(), any(Map.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseExcessMoneyAccount.class)).thenReturn(ResponseEntity.ok(account));

        AddExcessMoneyAccountResponse response = client.addExcessMoneyAccount(request);

        assertEquals(account, response.getExcessMoneyAccount());
        assertEquals(456L, response.getExcessMoneyAccount().getRecipientId());
    }

    @Test
    void getTotalFunds_returnsResponse() {
        WiseTotalFunds totalFunds = WiseTotalFunds.builder()
            .totalWorth(new com.jid.springwise.core.model.WiseMoney(new BigDecimal("1000.00"), "GBP"))
            .build();
        GetTotalFundsRequest request = GetTotalFundsRequest.builder()
            .profileId(123L)
            .currency("GBP")
            .build();

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Map.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseTotalFunds.class)).thenReturn(ResponseEntity.ok(totalFunds));

        GetTotalFundsResponse response = client.getTotalFunds(request);

        assertEquals(totalFunds, response.getTotalFunds());
    }

    @Test
    void getBalanceStatement_returnsResponse() {
        WiseBalanceStatement statement = WiseBalanceStatement.builder().build();
        GetBalanceStatementRequest request = GetBalanceStatementRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .currency("GBP")
            .intervalStart("2024-01-01T00:00:00Z")
            .intervalEnd("2024-01-31T23:59:59Z")
            .build();

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseBalanceStatement.class)).thenReturn(ResponseEntity.ok(statement));

        GetBalanceStatementResponse response = client.getBalanceStatement(request);

        assertEquals(statement, response.getStatement());
    }

    @Test
    void getBalanceStatement_withOptionalParams_returnsResponse() {
        WiseBalanceStatement statement = WiseBalanceStatement.builder().build();
        GetBalanceStatementRequest request = GetBalanceStatementRequest.builder()
            .profileId(123L)
            .balanceId(42L)
            .currency("GBP")
            .intervalStart("2024-01-01T00:00:00Z")
            .intervalEnd("2024-01-31T23:59:59Z")
            .type(WiseStatementType.FLAT)
            .statementLocale("en-GB")
            .build();

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(WiseBalanceStatement.class)).thenReturn(ResponseEntity.ok(statement));

        GetBalanceStatementResponse response = client.getBalanceStatement(request);

        assertNotNull(response);
    }

}
