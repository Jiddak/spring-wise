package com.jid.springwise.auth.client;

import com.jid.springwise.auth.model.jose.GetJwksResponse;
import com.jid.springwise.auth.model.jose.WiseJwks;
import com.jid.springwise.auth.model.onetimetoken.GetOneTimeTokenResponse;
import com.jid.springwise.auth.model.onetimetoken.WiseOneTimeToken;
import com.jid.springwise.auth.model.sca.*;
import com.jid.springwise.auth.model.token.*;
import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Map;

public class WiseAuthClient {

    private final RestClient restClient;

    public WiseAuthClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseAuthClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    public ExchangeAuthCodeResponse exchangeAuthCode(ExchangeAuthCodeRequest request) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("client_id", request.getClientId());
        form.add("client_secret", request.getClientSecret());
        form.add("code", request.getCode());
        form.add("redirect_uri", request.getRedirectUri());
        WiseUserToken t = restClient.post().uri("v1/oauth2/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON)
            .body(form).retrieve().toEntity(WiseUserToken.class).getBody();
        return ExchangeAuthCodeResponse.builder().token(t).build();
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "refresh_token");
        form.add("client_id", request.getClientId());
        form.add("client_secret", request.getClientSecret());
        form.add("refresh_token", request.getRefreshToken());
        WiseUserToken t = restClient.post().uri("v1/oauth2/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON)
            .body(form).retrieve().toEntity(WiseUserToken.class).getBody();
        return RefreshTokenResponse.builder().token(t).build();
    }

    public GetClientCredentialsTokenResponse getClientCredentialsToken(GetClientCredentialsTokenRequest request) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");
        form.add("client_id", request.getClientId());
        form.add("client_secret", request.getClientSecret());
        WiseUserToken t = restClient.post().uri("v1/oauth2/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON)
            .body(form).retrieve().toEntity(WiseUserToken.class).getBody();
        return GetClientCredentialsTokenResponse.builder().token(t).build();
    }

    public GetOneTimeTokenResponse getOneTimeToken() {
        WiseOneTimeToken ott = restClient.post().uri("v1/oauth2/one-time-token")
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseOneTimeToken.class).getBody();
        return GetOneTimeTokenResponse.builder().oneTimeToken(ott).build();
    }

    public InitiateScaChallengeResponse initiateScaChallenge(InitiateScaChallengeRequest request) {
        WiseScaChallenge c = restClient.post()
            .uri("v1/profiles/{profileId}/sca-challenges", Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .body(request.getChallenge()).retrieve().toEntity(WiseScaChallenge.class).getBody();
        return InitiateScaChallengeResponse.builder().challenge(c).build();
    }

    public GetScaChallengeResponse getScaChallenge(GetScaChallengeRequest request) {
        WiseScaChallenge c = restClient.get()
            .uri("v1/profiles/{profileId}/sca-challenges/{challengeId}",
                Map.of("profileId", request.getProfileId(), "challengeId", request.getChallengeId()))
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseScaChallenge.class).getBody();
        return GetScaChallengeResponse.builder().challenge(c).build();
    }

    public GetJwksResponse getJwks() {
        WiseJwks jwks = restClient.get().uri("v1/auth/jose/response/public-keys")
            .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(WiseJwks.class).getBody();
        return GetJwksResponse.builder().jwks(jwks).build();
    }
}
