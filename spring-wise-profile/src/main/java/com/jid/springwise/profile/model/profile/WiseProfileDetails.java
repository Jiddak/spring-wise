package com.jid.springwise.profile.model.profile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseProfileDetails {
    @JsonProperty("firstName")              private String firstName;
    @JsonProperty("lastName")               private String lastName;
    @JsonProperty("dateOfBirth")            private String dateOfBirth;
    @JsonProperty("phoneNumber")            private String phoneNumber;
    @JsonProperty("name")                   private String name;
    @JsonProperty("registrationNumber")     private String registrationNumber;
    @JsonProperty("acn")                    private String acn;
    @JsonProperty("abn")                    private String abn;
    @JsonProperty("arbn")                   private String arbn;
    @JsonProperty("companyType")            private String companyType;
    @JsonProperty("companyRole")            private String companyRole;
    @JsonProperty("descriptionOfBusiness")  private String descriptionOfBusiness;
    @JsonProperty("webpage")                private String webpage;
    @JsonProperty("primaryCurrency")        private String primaryCurrency;
}
