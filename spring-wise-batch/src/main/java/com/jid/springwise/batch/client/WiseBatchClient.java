package com.jid.springwise.batch.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.batch.model.batch.*;
import com.jid.springwise.batch.model.settlement.*;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import java.util.Map;

public class WiseBatchClient {

    private final RestClient restClient;

    public WiseBatchClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseBatchClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    public CreateBatchGroupResponse createBatchGroup(CreateBatchGroupRequest request) {
        WiseBatchGroup g = restClient.post()
            .uri("v3/profiles/{profileId}/batch-groups", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getBatchGroup()).retrieve().toEntity(WiseBatchGroup.class).getBody();
        return CreateBatchGroupResponse.builder().batchGroup(g).build();
    }

    public GetBatchGroupResponse getBatchGroup(GetBatchGroupRequest request) {
        WiseBatchGroup g = restClient.get()
            .uri("v3/profiles/{profileId}/batch-groups/{batchGroupId}",
                Map.of("profileId", request.getProfileId(), "batchGroupId", request.getBatchGroupId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseBatchGroup.class).getBody();
        return GetBatchGroupResponse.builder().batchGroup(g).build();
    }

    public UpdateBatchGroupResponse updateBatchGroup(UpdateBatchGroupRequest request) {
        WiseBatchGroup g = restClient.patch()
            .uri("v3/profiles/{profileId}/batch-groups/{batchGroupId}",
                Map.of("profileId", request.getProfileId(), "batchGroupId", request.getBatchGroupId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getBatchGroupUpdate()).retrieve().toEntity(WiseBatchGroup.class).getBody();
        return UpdateBatchGroupResponse.builder().batchGroup(g).build();
    }

    public AddTransferToBatchGroupResponse addTransferToBatchGroup(AddTransferToBatchGroupRequest request) {
        WiseBatchTransfer t = restClient.post()
            .uri("v3/profiles/{profileId}/batch-groups/{batchGroupId}/transfers",
                Map.of("profileId", request.getProfileId(), "batchGroupId", request.getBatchGroupId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getTransfer()).retrieve().toEntity(WiseBatchTransfer.class).getBody();
        return AddTransferToBatchGroupResponse.builder().transfer(t).build();
    }

    public InitiateBatchPaymentResponse initiateBatchPayment(InitiateBatchPaymentRequest request) {
        WisePaymentInitiation p = restClient.post()
            .uri("v1/profiles/{profileId}/batch-groups/{batchGroupId}/payment-initiations",
                Map.of("profileId", request.getProfileId(), "batchGroupId", request.getBatchGroupId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getPaymentInitiation()).retrieve().toEntity(WisePaymentInitiation.class).getBody();
        return InitiateBatchPaymentResponse.builder().paymentInitiation(p).build();
    }

    public GetBatchPaymentInitiationResponse getBatchPaymentInitiation(GetBatchPaymentInitiationRequest request) {
        WisePaymentInitiation p = restClient.get()
            .uri("v1/profiles/{profileId}/batch-groups/{batchGroupId}/payment-initiations/{paymentInitiationId}",
                Map.of("profileId", request.getProfileId(),
                       "batchGroupId", request.getBatchGroupId(),
                       "paymentInitiationId", request.getPaymentInitiationId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WisePaymentInitiation.class).getBody();
        return GetBatchPaymentInitiationResponse.builder().paymentInitiation(p).build();
    }

    public FundBatchGroupResponse fundBatchGroup(FundBatchGroupRequest request) {
        WiseBatchPaymentResult r = restClient.post()
            .uri("v3/profiles/{profileId}/batch-payments/{batchGroupId}/payments",
                Map.of("profileId", request.getProfileId(), "batchGroupId", request.getBatchGroupId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getPayment()).retrieve().toEntity(WiseBatchPaymentResult.class).getBody();
        return FundBatchGroupResponse.builder().result(r).build();
    }

    public CreateBulkSettlementResponse createBulkSettlement(CreateBulkSettlementRequest request) {
        WiseBulkSettlementResult r = restClient.post().uri("v1/settlements")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getSettlement()).retrieve().toEntity(WiseBulkSettlementResult.class).getBody();
        return CreateBulkSettlementResponse.builder().result(r).build();
    }
}
