package id.ten.frauddetectionservice

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Transaction(
    @JsonProperty("transactionId") val transactionId: String,
    @JsonProperty("userId") val userId: String,
    @JsonProperty("amount") val amount: Double,
    @JsonProperty("timestamp") val timestamp: Long
)

data class FraudAlert @JsonCreator constructor(
    @JsonProperty("sender") val sender: String = "",
    @JsonProperty("totalAmount") val totalAmount: Double = 0.0,
    @JsonProperty("date") val date: String = ""
)
