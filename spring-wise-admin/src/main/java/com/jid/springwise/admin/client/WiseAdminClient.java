package com.jid.springwise.admin.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.admin.model.activity.*;
import com.jid.springwise.admin.model.comparison.*;
import com.jid.springwise.admin.model.contact.*;
import com.jid.springwise.admin.model.currencies.*;
import com.jid.springwise.admin.model.directdebit.*;
import com.jid.springwise.admin.model.dispute.*;
import com.jid.springwise.admin.model.facetec.*;
import com.jid.springwise.admin.model.partnercases.*;
import com.jid.springwise.admin.model.payindeposit.*;
import com.jid.springwise.admin.model.simulation.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Map;

public class WiseAdminClient {

    private final RestClient restClient;

    public WiseAdminClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseAdminClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    public GetActivitiesResponse getActivities(GetActivitiesRequest request) {
        List<WiseActivity> list = restClient.get()
            .uri("v1/profiles/{profileId}/activities", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseActivity>>() {}).getBody();
        return GetActivitiesResponse.builder().activities(list).build();
    }

    public GetCurrenciesResponse getCurrencies() {
        List<WiseCurrency> list = restClient.get().uri("v1/currencies")
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseCurrency>>() {}).getBody();
        return GetCurrenciesResponse.builder().currencies(list).build();
    }

    public CompareResponse compare(CompareRequest request) {
        WiseComparison c = restClient.get()
            .uri(b -> b.path("v1/comparison")
                .queryParam("source", request.getSourceCurrency())
                .queryParam("target", request.getTargetCurrency())
                .queryParam("sourceAmount", request.getSourceAmount()).build())
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseComparison.class).getBody();
        return CompareResponse.builder().comparison(c).build();
    }

    public CreateContactResponse createContact(CreateContactRequest request) {
        WiseContact c = restClient.post().uri("v1/contacts")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getContact()).retrieve().toEntity(WiseContact.class).getBody();
        return CreateContactResponse.builder().contact(c).build();
    }

    public GetContactsResponse getContacts() {
        List<WiseContact> list = restClient.get().uri("v1/contacts")
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseContact>>() {}).getBody();
        return GetContactsResponse.builder().contacts(list).build();
    }

    public CreateDirectDebitAccountResponse createDirectDebitAccount(CreateDirectDebitAccountRequest request) {
        WiseDirectDebitAccount a = restClient.post()
            .uri("v1/profiles/{profileId}/direct-debit-accounts", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getDirectDebitAccount()).retrieve().toEntity(WiseDirectDebitAccount.class).getBody();
        return CreateDirectDebitAccountResponse.builder().directDebitAccount(a).build();
    }

    public GetDirectDebitAccountsResponse getDirectDebitAccounts(GetDirectDebitAccountsRequest request) {
        List<WiseDirectDebitAccount> list = restClient.get()
            .uri("v1/profiles/{profileId}/direct-debit-accounts", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseDirectDebitAccount>>() {}).getBody();
        return GetDirectDebitAccountsResponse.builder().directDebitAccounts(list).build();
    }

    public DeleteDirectDebitAccountResponse deleteDirectDebitAccount(DeleteDirectDebitAccountRequest request) {
        restClient.delete()
            .uri("v1/profiles/{profileId}/direct-debit-accounts/{directDebitAccountId}",
                Map.of("profileId", request.getProfileId(),
                       "directDebitAccountId", request.getDirectDebitAccountId()))
            .retrieve().toBodilessEntity();
        return DeleteDirectDebitAccountResponse.builder().success(true).build();
    }

    public CreateDisputeResponse createDispute(CreateDisputeRequest request) {
        WiseDispute d = restClient.post()
            .uri("v1/profiles/{profileId}/disputes", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getDispute()).retrieve().toEntity(WiseDispute.class).getBody();
        return CreateDisputeResponse.builder().dispute(d).build();
    }

    public GetDisputeResponse getDispute(GetDisputeRequest request) {
        WiseDispute d = restClient.get()
            .uri("v1/profiles/{profileId}/disputes/{disputeId}",
                Map.of("profileId", request.getProfileId(), "disputeId", request.getDisputeId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseDispute.class).getBody();
        return GetDisputeResponse.builder().dispute(d).build();
    }

    public CreatePartnerCaseResponse createPartnerCase(CreatePartnerCaseRequest request) {
        WisePartnerCase c = restClient.post()
            .uri("v1/profiles/{profileId}/cases", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getPartnerCase()).retrieve().toEntity(WisePartnerCase.class).getBody();
        return CreatePartnerCaseResponse.builder().partnerCase(c).build();
    }

    public GetPartnerCaseResponse getPartnerCase(GetPartnerCaseRequest request) {
        WisePartnerCase c = restClient.get()
            .uri("v1/profiles/{profileId}/cases/{caseId}",
                Map.of("profileId", request.getProfileId(), "caseId", request.getCaseId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WisePartnerCase.class).getBody();
        return GetPartnerCaseResponse.builder().partnerCase(c).build();
    }

    public GetPayinDepositDetailResponse getPayinDepositDetail(GetPayinDepositDetailRequest request) {
        WisePayinDepositDetail d = restClient.get()
            .uri("v1/profiles/{profileId}/payin-deposit-details/{paymentId}",
                Map.of("profileId", request.getProfileId(), "paymentId", request.getPaymentId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WisePayinDepositDetail.class).getBody();
        return GetPayinDepositDetailResponse.builder().depositDetail(d).build();
    }

    public SimulateTransferStateChangeResponse simulateTransferStateChange(
            SimulateTransferStateChangeRequest request) {
        WiseSimulationResult r = restClient.post()
            .uri("v1/simulation/transfers/{transferId}/{status}",
                Map.of("transferId", request.getTransferId(),
                       "status", request.getSimulation().getStatus()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseSimulationResult.class).getBody();
        return SimulateTransferStateChangeResponse.builder().result(r).build();
    }

    public SimulateCardProductionStateChangeResponse simulateCardProductionStateChange(
            SimulateCardProductionStateChangeRequest request) {
        WiseSimulationResult r = restClient.post()
            .uri("v3/spend/profiles/{profileId}/cards/{cardToken}/simulation/production",
                Map.of("profileId", request.getProfileId(), "cardToken", request.getCardToken()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getSimulation()).retrieve().toEntity(WiseSimulationResult.class).getBody();
        return SimulateCardProductionStateChangeResponse.builder().result(r).build();
    }

    public GetFaceTecPublicKeyResponse getFaceTecPublicKey() {
        WiseFaceTecPublicKey k = restClient.get().uri("v1/facetec/public-key")
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseFaceTecPublicKey.class).getBody();
        return GetFaceTecPublicKeyResponse.builder().publicKey(k).build();
    }
}
