package id.ten.frauddetectionservice

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class FraudAlertConsumer(private val emailService: EmailService) {

    @KafkaListener(topics = ["fraud-alerts2"], groupId = "fraud-detection-group")
    fun consumeFraudAlert(message: String) {
        emailService.sendFraudAlertEmail(message)
    }
}
