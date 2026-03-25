package com.jid.springwise.transfer.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.transfer.model.delivery.*;
import com.jid.springwise.transfer.model.quote.*;
import com.jid.springwise.transfer.model.rate.*;
import com.jid.springwise.transfer.model.recipient.*;
import com.jid.springwise.transfer.model.transfer.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Map;

public class WiseTransferClient {

    private final RestClient restClient;

    public WiseTransferClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseTransferClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    public CreateQuoteResponse createQuote(CreateQuoteRequest request) {
        WiseQuote q = restClient.post()
            .uri("v3/profiles/{profileId}/quotes", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getQuote()).retrieve().toEntity(WiseQuote.class).getBody();
        return CreateQuoteResponse.builder().quote(q).build();
    }

    public GetQuoteResponse getQuote(GetQuoteRequest request) {
        WiseQuote q = restClient.get()
            .uri("v3/profiles/{profileId}/quotes/{quoteId}",
                Map.of("profileId", request.getProfileId(), "quoteId", request.getQuoteId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseQuote.class).getBody();
        return GetQuoteResponse.builder().quote(q).build();
    }

    public UpdateQuoteResponse updateQuote(UpdateQuoteRequest request) {
        WiseQuote q = restClient.patch()
            .uri("v3/profiles/{profileId}/quotes/{quoteId}",
                Map.of("profileId", request.getProfileId(), "quoteId", request.getQuoteId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getQuote()).retrieve().toEntity(WiseQuote.class).getBody();
        return UpdateQuoteResponse.builder().quote(q).build();
    }

    public CreateRecipientAccountResponse createRecipientAccount(CreateRecipientAccountRequest request) {
        WiseRecipientAccount a = restClient.post().uri("v1/accounts")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getRecipientAccount()).retrieve().toEntity(WiseRecipientAccount.class).getBody();
        return CreateRecipientAccountResponse.builder().recipientAccount(a).build();
    }

    public GetRecipientAccountResponse getRecipientAccount(GetRecipientAccountRequest request) {
        WiseRecipientAccount a = restClient.get()
            .uri("v1/accounts/{accountId}", Map.of("accountId", request.getAccountId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseRecipientAccount.class).getBody();
        return GetRecipientAccountResponse.builder().recipientAccount(a).build();
    }

    public GetRecipientAccountsResponse getRecipientAccounts(GetRecipientAccountsRequest request) {
        List<WiseRecipientAccount> list = restClient.get()
            .uri(b -> {
                var builder = b.path("v1/accounts").queryParam("profile", request.getProfileId());
                if (request.getCurrency() != null) builder.queryParam("currency", request.getCurrency());
                return builder.build();
            })
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseRecipientAccount>>() {}).getBody();
        return GetRecipientAccountsResponse.builder().recipientAccounts(list).build();
    }

    public DeleteRecipientAccountResponse deleteRecipientAccount(DeleteRecipientAccountRequest request) {
        restClient.delete().uri("v1/accounts/{accountId}", Map.of("accountId", request.getAccountId()))
            .retrieve().toBodilessEntity();
        return DeleteRecipientAccountResponse.builder().success(true).build();
    }

    public CreateTransferResponse createTransfer(CreateTransferRequest request) {
        WiseTransfer t = restClient.post().uri("v1/transfers")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getTransfer()).retrieve().toEntity(WiseTransfer.class).getBody();
        return CreateTransferResponse.builder().transfer(t).build();
    }

    public GetTransferResponse getTransfer(GetTransferRequest request) {
        WiseTransfer t = restClient.get()
            .uri("v1/transfers/{transferId}", Map.of("transferId", request.getTransferId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseTransfer.class).getBody();
        return GetTransferResponse.builder().transfer(t).build();
    }

    public GetTransfersResponse getTransfers(GetTransfersRequest request) {
        List<WiseTransfer> list = restClient.get()
            .uri(b -> b.path("v1/transfers").queryParam("profile", request.getProfileId()).build())
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseTransfer>>() {}).getBody();
        return GetTransfersResponse.builder().transfers(list).build();
    }

    public FundTransferResponse fundTransfer(FundTransferRequest request) {
        WiseFundTransferDetails d = restClient.post()
            .uri("v3/profiles/{profileId}/transfers/{transferId}/payments",
                Map.of("profileId", request.getProfileId(), "transferId", request.getTransferId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getFundTransfer()).retrieve().toEntity(WiseFundTransferDetails.class).getBody();
        return FundTransferResponse.builder().fundTransfer(d).build();
    }

    public CancelTransferResponse cancelTransfer(CancelTransferRequest request) {
        WiseTransfer t = restClient.put()
            .uri("v1/transfers/{transferId}/cancel", Map.of("transferId", request.getTransferId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseTransfer.class).getBody();
        return CancelTransferResponse.builder().transfer(t).build();
    }

    public GetRatesResponse getRates(GetRatesRequest request) {
        List<WiseRate> rates = restClient.get()
            .uri(b -> b.path("v1/rates")
                .queryParam("source", request.getSource())
                .queryParam("target", request.getTarget()).build())
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseRate>>() {}).getBody();
        return GetRatesResponse.builder().rates(rates).build();
    }

    public GetRatesResponse getAllRates() {
        List<WiseRate> rates = restClient.get().uri("v1/rates")
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseRate>>() {}).getBody();
        return GetRatesResponse.builder().rates(rates).build();
    }

    public GetDeliveryEstimateResponse getDeliveryEstimate(GetDeliveryEstimateRequest request) {
        WiseDeliveryEstimate e = restClient.get()
            .uri("v1/delivery-estimates/{transferId}", Map.of("transferId", request.getTransferId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseDeliveryEstimate.class).getBody();
        return GetDeliveryEstimateResponse.builder().deliveryEstimate(e).build();
    }
}
