package id.ten.frauddetectionservice

import com.fasterxml.jackson.annotation.JsonProperty

data class Transaction(
    @JsonProperty("transactionId") val transactionId: String,
    @JsonProperty("userId") val userId: String,
    @JsonProperty("amount") val amount: Double,
    @JsonProperty("timestamp") val timestamp: Long
)