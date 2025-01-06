package id.ten.frauddetectionservice

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(private val kafkaTemplate: KafkaTemplate<String, Transaction>) {

    @PostMapping
    fun sendTransaction(@RequestBody transaction: Transaction) {
        kafkaTemplate.send("transactions", transaction.userId, transaction)
    }
}