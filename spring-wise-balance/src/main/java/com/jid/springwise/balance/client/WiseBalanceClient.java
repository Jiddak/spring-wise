package com.jid.springwise.balance.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.balance.model.balance.*;
import com.jid.springwise.balance.model.bankdetails.*;
import com.jid.springwise.balance.model.multicurrency.*;
import com.jid.springwise.balance.model.statement.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Map;

public class WiseBalanceClient {

    private final RestClient restClient;

    public WiseBalanceClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseBalanceClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    public CreateBalanceResponse createBalance(CreateBalanceRequest request) {
        WiseBalance b = restClient.post()
            .uri("v4/profiles/{profileId}/balances", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getBalance()).retrieve().toEntity(WiseBalance.class).getBody();
        return CreateBalanceResponse.builder().balance(b).build();
    }

    public GetBalancesResponse getBalances(GetBalancesRequest request) {
        List<WiseBalance> list = restClient.get()
            .uri("v4/profiles/{profileId}/balances", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseBalance>>() {}).getBody();
        return GetBalancesResponse.builder().balances(list).build();
    }

    public GetBalanceResponse getBalance(GetBalanceRequest request) {
        WiseBalance b = restClient.get()
            .uri("v4/profiles/{profileId}/balances/{balanceId}",
                Map.of("profileId", request.getProfileId(), "balanceId", request.getBalanceId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseBalance.class).getBody();
        return GetBalanceResponse.builder().balance(b).build();
    }

    public DeleteBalanceResponse deleteBalance(DeleteBalanceRequest request) {
        restClient.delete()
            .uri("v4/profiles/{profileId}/balances/{balanceId}",
                Map.of("profileId", request.getProfileId(), "balanceId", request.getBalanceId()))
            .retrieve().toBodilessEntity();
        return DeleteBalanceResponse.builder().success(true).build();
    }

    public MoveBalanceFundsResponse moveBalanceFunds(MoveBalanceFundsRequest request) {
        WiseBalance b = restClient.post()
            .uri("v2/profiles/{profileId}/balance-movements", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getBalanceMovement()).retrieve().toEntity(WiseBalance.class).getBody();
        return MoveBalanceFundsResponse.builder().balance(b).build();
    }

    public GetBalanceStatementResponse getBalanceStatement(GetBalanceStatementRequest request) {
        WiseBalanceStatement s = restClient.get()
            .uri(b -> b.path("v1/profiles/{profileId}/balance-statements/{balanceId}/statement.json")
                .queryParam("intervalStart", request.getIntervalStart())
                .queryParam("intervalEnd", request.getIntervalEnd())
                .build(request.getProfileId(), request.getBalanceId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseBalanceStatement.class).getBody();
        return GetBalanceStatementResponse.builder().statement(s).build();
    }

    public GetAccountDetailsResponse getAccountDetails(GetAccountDetailsRequest request) {
        List<WiseBankAccountDetails> list = restClient.get()
            .uri("v1/profiles/{profileId}/account-details", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseBankAccountDetails>>() {}).getBody();
        return GetAccountDetailsResponse.builder().accountDetails(list).build();
    }

    public CreateAccountDetailsOrderResponse createAccountDetailsOrder(CreateAccountDetailsOrderRequest request) {
        WiseAccountDetailsOrder o = restClient.post()
            .uri("v1/profiles/{profileId}/account-details-orders", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getOrder()).retrieve().toEntity(WiseAccountDetailsOrder.class).getBody();
        return CreateAccountDetailsOrderResponse.builder().order(o).build();
    }

    public GetAccountDetailsOrdersResponse getAccountDetailsOrders(GetAccountDetailsOrdersRequest request) {
        List<WiseAccountDetailsOrder> list = restClient.get()
            .uri("v3/profiles/{profileId}/account-details-orders", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseAccountDetailsOrder>>() {}).getBody();
        return GetAccountDetailsOrdersResponse.builder().orders(list).build();
    }

    public CreateBankDetailsResponse createBankDetails(CreateBankDetailsRequest request) {
        WiseBankAccountDetails d = restClient.post()
            .uri("v3/profiles/{profileId}/bank-details", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getBankDetails()).retrieve().toEntity(WiseBankAccountDetails.class).getBody();
        return CreateBankDetailsResponse.builder().bankAccountDetails(d).build();
    }

    public GetMultiCurrencyAccountResponse getMultiCurrencyAccount(GetMultiCurrencyAccountRequest request) {
        WiseMultiCurrencyAccount a = restClient.get()
            .uri("v1/profiles/{profileId}/multi-currency-account", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseMultiCurrencyAccount.class).getBody();
        return GetMultiCurrencyAccountResponse.builder().account(a).build();
    }
}
