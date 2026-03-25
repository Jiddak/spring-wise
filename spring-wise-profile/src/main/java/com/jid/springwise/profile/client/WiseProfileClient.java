package com.jid.springwise.profile.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.profile.model.address.*;
import com.jid.springwise.profile.model.kyc.*;
import com.jid.springwise.profile.model.profile.*;
import com.jid.springwise.profile.model.user.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Map;

public class WiseProfileClient {

    private final RestClient restClient;

    public WiseProfileClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseProfileClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    public GetProfilesResponse getProfiles() {
        List<WiseProfile> list = restClient.get().uri("v1/profiles")
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseProfile>>() {}).getBody();
        return GetProfilesResponse.builder().profiles(list).build();
    }

    public GetProfileResponse getProfile(GetProfileRequest request) {
        WiseProfile p = restClient.get()
            .uri("v1/profiles/{profileId}", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseProfile.class).getBody();
        return GetProfileResponse.builder().profile(p).build();
    }

    public CreateProfileResponse createProfile(CreateProfileRequest request) {
        WiseProfile p = restClient.post().uri("v1/profiles")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getProfile()).retrieve().toEntity(WiseProfile.class).getBody();
        return CreateProfileResponse.builder().profile(p).build();
    }

    public CreateAddressResponse createAddress(CreateAddressRequest request) {
        WiseAddress a = restClient.post().uri("v1/addresses")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getAddress()).retrieve().toEntity(WiseAddress.class).getBody();
        return CreateAddressResponse.builder().address(a).build();
    }

    public GetAddressesResponse getAddresses() {
        List<WiseAddress> list = restClient.get().uri("v1/addresses")
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseAddress>>() {}).getBody();
        return GetAddressesResponse.builder().addresses(list).build();
    }

    public GetAddressResponse getAddress(GetAddressRequest request) {
        WiseAddress a = restClient.get()
            .uri("v1/addresses/{addressId}", Map.of("addressId", request.getAddressId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseAddress.class).getBody();
        return GetAddressResponse.builder().address(a).build();
    }

    public GetAddressRequirementsResponse getAddressRequirements() {
        List<Object> list = restClient.get().uri("v1/address-requirements")
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<Object>>() {}).getBody();
        return GetAddressRequirementsResponse.builder().requirements(list).build();
    }

    public GetAddressRequirementsForCountryResponse getAddressRequirementsForCountry(
            GetAddressRequirementsForCountryRequest request) {
        List<Object> list = restClient.post().uri("v1/address-requirements")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getFilter()).retrieve()
            .toEntity(new ParameterizedTypeReference<List<Object>>() {}).getBody();
        return GetAddressRequirementsForCountryResponse.builder().requirements(list).build();
    }

    public GetCurrentUserResponse getCurrentUser() {
        WiseUser u = restClient.get().uri("v1/me")
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseUser.class).getBody();
        return GetCurrentUserResponse.builder().user(u).build();
    }

    public GetUserSecurityResponse getUserSecurity() {
        WiseUserSecurity s = restClient.get().uri("v1/me/security")
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseUserSecurity.class).getBody();
        return GetUserSecurityResponse.builder().security(s).build();
    }

    public GetKycReviewResponse getKycReview(GetKycReviewRequest request) {
        WiseKycReview k = restClient.get()
            .uri("v1/profiles/{profileId}/kyc-reviews", Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseKycReview.class).getBody();
        return GetKycReviewResponse.builder().kycReview(k).build();
    }

    public GetRequiredEvidencesResponse getRequiredEvidences(GetRequiredEvidencesRequest request) {
        List<WiseRequiredEvidence> list = restClient.get()
            .uri("v3/profiles/{profileId}/verification-status/required-evidences",
                Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON).retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseRequiredEvidence>>() {}).getBody();
        return GetRequiredEvidencesResponse.builder().evidences(list).build();
    }

    public UploadVerificationDocumentResponse uploadVerificationDocument(
            UploadVerificationDocumentRequest request) {
        restClient.post()
            .uri("v3/profiles/{profileId}/verification-status/upload-document",
                Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getDocument()).retrieve().toBodilessEntity();
        return UploadVerificationDocumentResponse.builder().success(true).build();
    }

    public UploadVerificationEvidencesResponse uploadVerificationEvidences(
            UploadVerificationEvidencesRequest request) {
        restClient.post()
            .uri("v5/profiles/{profileId}/additional-verification/upload-evidences",
                Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getEvidences()).retrieve().toBodilessEntity();
        return UploadVerificationEvidencesResponse.builder().success(true).build();
    }
}
