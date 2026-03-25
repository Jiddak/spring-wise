package com.jid.springwise.card.model.cardtransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseCardTransactionType {
    @JsonProperty("ACCOUNT_CREDIT")     ACCOUNT_CREDIT,
    @JsonProperty("ACCOUNT_FUNDING")    ACCOUNT_FUNDING,
    @JsonProperty("CASH_ADVANCE")       CASH_ADVANCE,
    @JsonProperty("CASH_WITHDRAWAL")    CASH_WITHDRAWAL,
    @JsonProperty("CHARGEBACK")         CHARGEBACK,
    @JsonProperty("CREDIT_TRANSACTION") CREDIT_TRANSACTION,
    @JsonProperty("ECOM_PURCHASE")      ECOM_PURCHASE,
    @JsonProperty("POS_PURCHASE")       POS_PURCHASE,
    @JsonProperty("REFUND")             REFUND,
}
