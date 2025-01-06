package id.ten.frauddetectionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafkaStreams

@SpringBootApplication
@EnableKafkaStreams
class FraudDetectionServiceApplication

fun main(args: Array<String>) {
    runApplication<FraudDetectionServiceApplication>(*args)
}

