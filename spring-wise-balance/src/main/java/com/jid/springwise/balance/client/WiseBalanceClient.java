package com.jid.springwise.balance.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.balance.model.balance.*;
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
            .header("X-idempotence-uuid", request.getIdempotenceUuid() != null ? request.getIdempotenceUuid() : java.util.UUID.randomUUID().toString())
            .body(request.getBalance()).retrieve().toEntity(WiseBalance.class).getBody();
        return CreateBalanceResponse.builder().balance(b).build();
    }

    public GetBalancesResponse getBalances(GetBalancesRequest request) {
        List<WiseBalance> list = restClient.get()
            .uri(b -> b.path("v4/profiles/{profileId}/balances")
                .queryParam("types", request.getTypes())
                .build(request.getProfileId()))
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
        WiseBalanceMovementResponse r = restClient.post()
            .uri("v2/profiles/{profileId}/balance-movements", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .header("X-idempotence-uuid", request.getIdempotenceUuid() != null ? request.getIdempotenceUuid() : java.util.UUID.randomUUID().toString())
            .body(request.getBalanceMovement()).retrieve().toEntity(WiseBalanceMovementResponse.class).getBody();
        return MoveBalanceFundsResponse.builder().movement(r).build();
    }

    public GetBalanceCapacityResponse getBalanceCapacity(GetBalanceCapacityRequest request) {
        WiseBalanceCapacity c = restClient.get()
            .uri(b -> b.path("v1/profiles/{profileId}/balance-capacity")
                .queryParam("currency", request.getCurrency())
                .build(request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseBalanceCapacity.class).getBody();
        return GetBalanceCapacityResponse.builder().capacity(c).build();
    }

    public AddExcessMoneyAccountResponse addExcessMoneyAccount(AddExcessMoneyAccountRequest request) {
        WiseExcessMoneyAccount a = restClient.post()
            .uri("v1/profiles/{profileId}/excess-money-account", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getExcessMoneyAccount()).retrieve().toEntity(WiseExcessMoneyAccount.class).getBody();
        return AddExcessMoneyAccountResponse.builder().excessMoneyAccount(a).build();
    }

    public GetTotalFundsResponse getTotalFunds(GetTotalFundsRequest request) {
        WiseTotalFunds t = restClient.get()
            .uri("v1/profiles/{profileId}/total-funds/{currency}",
                Map.of("profileId", request.getProfileId(), "currency", request.getCurrency()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseTotalFunds.class).getBody();
        return GetTotalFundsResponse.builder().totalFunds(t).build();
    }

    public GetBalanceStatementResponse getBalanceStatement(GetBalanceStatementRequest request) {
        WiseBalanceStatement s = restClient.get()
            .uri(b -> b.path("v1/profiles/{profileId}/balance-statements/{balanceId}/statement.json")
                .queryParam("currency", request.getCurrency())
                .queryParam("intervalStart", request.getIntervalStart())
                .queryParam("intervalEnd", request.getIntervalEnd())
                .queryParamIfPresent("type", java.util.Optional.ofNullable(request.getType()))
                .queryParamIfPresent("statementLocale", java.util.Optional.ofNullable(request.getStatementLocale()))
                .build(request.getProfileId(), request.getBalanceId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseBalanceStatement.class).getBody();
        return GetBalanceStatementResponse.builder().statement(s).build();
    }
}
