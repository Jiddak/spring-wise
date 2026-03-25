package com.jid.springwise.card.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.card.model.card.*;
import com.jid.springwise.card.model.cardkiosk.*;
import com.jid.springwise.card.model.cardorder.*;
import com.jid.springwise.card.model.cardtransaction.*;
import com.jid.springwise.card.model.spendcontrols.*;
import com.jid.springwise.card.model.spendlimits.*;
import com.jid.springwise.card.model.threedssecure.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Map;

public class WiseCardClient {

    private final RestClient restClient;

    public WiseCardClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseCardClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    public GetCardsResponse getCards(GetCardsRequest request) {
        List<WiseCard> list = restClient.get()
            .uri("v3/spend/profiles/{profileId}/cards", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseCard>>() {}).getBody();
        return GetCardsResponse.builder().cards(list).build();
    }

    public GetCardResponse getCard(GetCardRequest request) {
        WiseCard c = restClient.get()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseCard.class).getBody();
        return GetCardResponse.builder().card(c).build();
    }

    public UpdateCardStatusResponse updateCardStatus(UpdateCardStatusRequest request) {
        WiseCard c = restClient.put()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}/status",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getStatusUpdate()).retrieve().toEntity(WiseCard.class).getBody();
        return UpdateCardStatusResponse.builder().card(c).build();
    }

    public ResetPinCountResponse resetPinCount(ResetPinCountRequest request) {
        restClient.post()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}/reset-pin-count",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .retrieve().toBodilessEntity();
        return ResetPinCountResponse.builder().success(true).build();
    }

    public GetSpendingPermissionsResponse getSpendingPermissions(GetSpendingPermissionsRequest request) {
        WiseCardSpendingPermissions p = restClient.get()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}/spending-permissions",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseCardSpendingPermissions.class).getBody();
        return GetSpendingPermissionsResponse.builder().permissions(p).build();
    }

    public UpdateSpendingPermissionsResponse updateSpendingPermissions(UpdateSpendingPermissionsRequest request) {
        WiseCardSpendingPermissions p = restClient.patch()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}/spending-permissions",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getPermissions()).retrieve().toEntity(WiseCardSpendingPermissions.class).getBody();
        return UpdateSpendingPermissionsResponse.builder().permissions(p).build();
    }

    public ProduceCardResponse produceCard(ProduceCardRequest request) {
        WiseCardProduction prod = restClient.put()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}/production",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getProduction()).retrieve().toEntity(WiseCardProduction.class).getBody();
        return ProduceCardResponse.builder().production(prod).build();
    }

    public GetCardProductionResponse getCardProduction(GetCardProductionRequest request) {
        WiseCardProduction prod = restClient.get()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}/production",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseCardProduction.class).getBody();
        return GetCardProductionResponse.builder().production(prod).build();
    }

    public CreateCardOrderResponse createCardOrder(CreateCardOrderRequest request) {
        WiseCardOrder o = restClient.post()
            .uri("v3/spend/profiles/{profileId}/card-orders", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getCardOrder()).retrieve().toEntity(WiseCardOrder.class).getBody();
        return CreateCardOrderResponse.builder().cardOrder(o).build();
    }

    public GetCardOrdersResponse getCardOrders(GetCardOrdersRequest request) {
        List<WiseCardOrder> list = restClient.get()
            .uri("v3/spend/profiles/{profileId}/card-orders", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseCardOrder>>() {}).getBody();
        return GetCardOrdersResponse.builder().cardOrders(list).build();
    }

    public GetCardOrderResponse getCardOrder(GetCardOrderRequest request) {
        WiseCardOrder o = restClient.get()
            .uri("v3/spend/profiles/{profileId}/card-orders/{cardOrderId}",
                Map.of("profileId", request.getProfileId(), "cardOrderId", request.getCardOrderId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseCardOrder.class).getBody();
        return GetCardOrderResponse.builder().cardOrder(o).build();
    }

    public UpdateCardOrderStatusResponse updateCardOrderStatus(UpdateCardOrderStatusRequest request) {
        WiseCardOrder o = restClient.put()
            .uri("v3/spend/profiles/{profileId}/card-orders/{cardOrderId}/status",
                Map.of("profileId", request.getProfileId(), "cardOrderId", request.getCardOrderId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getStatusUpdate()).retrieve().toEntity(WiseCardOrder.class).getBody();
        return UpdateCardOrderStatusResponse.builder().cardOrder(o).build();
    }

    public GetCardOrderRequirementsResponse getCardOrderRequirements(GetCardOrderRequirementsRequest request) {
        Object req = restClient.get()
            .uri("v3/spend/profiles/{profileId}/card-orders/{cardOrderId}/requirements",
                Map.of("profileId", request.getProfileId(), "cardOrderId", request.getCardOrderId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(Object.class).getBody();
        return GetCardOrderRequirementsResponse.builder().requirements(req).build();
    }

    public GetCardTransactionsResponse getCardTransactions(GetCardTransactionsRequest request) {
        List<WiseCardTransaction> list = restClient.get()
            .uri("v3/spend/profiles/{profileId}/card-transactions", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseCardTransaction>>() {}).getBody();
        return GetCardTransactionsResponse.builder().transactions(list).build();
    }

    public GetCardTransactionResponse getCardTransaction(GetCardTransactionRequest request) {
        WiseCardTransaction t = restClient.get()
            .uri("v3/spend/profiles/{profileId}/card-transactions/{transactionId}",
                Map.of("profileId", request.getProfileId(), "transactionId", request.getTransactionId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseCardTransaction.class).getBody();
        return GetCardTransactionResponse.builder().transaction(t).build();
    }

    public CreateSpendControlResponse createSpendControl(CreateSpendControlRequest request) {
        WiseSpendControl sc = restClient.post()
            .uri("v3/spend/profiles/{profileId}/spend-controls", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getSpendControl()).retrieve().toEntity(WiseSpendControl.class).getBody();
        return CreateSpendControlResponse.builder().spendControl(sc).build();
    }

    public GetSpendControlsResponse getSpendControls(GetSpendControlsRequest request) {
        List<WiseSpendControl> list = restClient.get()
            .uri("v3/spend/profiles/{profileId}/spend-controls", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseSpendControl>>() {}).getBody();
        return GetSpendControlsResponse.builder().spendControls(list).build();
    }

    public DeleteSpendControlResponse deleteSpendControl(DeleteSpendControlRequest request) {
        restClient.delete()
            .uri("v3/spend/profiles/{profileId}/spend-controls/{spendControlId}",
                Map.of("profileId", request.getProfileId(), "spendControlId", request.getSpendControlId()))
            .retrieve().toBodilessEntity();
        return DeleteSpendControlResponse.builder().success(true).build();
    }

    public CreateSpendLimitResponse createSpendLimit(CreateSpendLimitRequest request) {
        WiseSpendLimit sl = restClient.post()
            .uri("v3/spend/profiles/{profileId}/spend-limits", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getSpendLimit()).retrieve().toEntity(WiseSpendLimit.class).getBody();
        return CreateSpendLimitResponse.builder().spendLimit(sl).build();
    }

    public GetSpendLimitsResponse getSpendLimits(GetSpendLimitsRequest request) {
        List<WiseSpendLimit> list = restClient.get()
            .uri("v3/spend/profiles/{profileId}/spend-limits", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseSpendLimit>>() {}).getBody();
        return GetSpendLimitsResponse.builder().spendLimits(list).build();
    }

    public DeleteSpendLimitResponse deleteSpendLimit(DeleteSpendLimitRequest request) {
        restClient.delete()
            .uri("v3/spend/profiles/{profileId}/spend-limits/{spendLimitId}",
                Map.of("profileId", request.getProfileId(), "spendLimitId", request.getSpendLimitId()))
            .retrieve().toBodilessEntity();
        return DeleteSpendLimitResponse.builder().success(true).build();
    }

    public Submit3dsChallengeResponse submit3dsChallenge(Submit3dsChallengeRequest request) {
        Wise3dsChallengeResult r = restClient.post()
            .uri("v3/spend/profiles/{profileId}/3dsecure/challenge-result",
                Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getChallenge()).retrieve().toEntity(Wise3dsChallengeResult.class).getBody();
        return Submit3dsChallengeResponse.builder().result(r).build();
    }
}
