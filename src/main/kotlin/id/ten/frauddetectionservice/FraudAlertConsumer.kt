package id.ten.frauddetectionservice

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class FraudAlertConsumer(private val mailSender: JavaMailSender) {

    @KafkaListener(topics = ["fraud-alerts"], groupId = "fraud-detection-group")
    fun listenToFraudAlerts(message: String) {
        sendEmail(message)
    }

    private fun sendEmail(message: String) {
        val mailMessage = SimpleMailMessage().apply {
            from = "your-email@gmail.com"
            to = "recipient-email@example.com"
            subject = "Fraud Alert"
            text = message
        }
        mailSender.send(mailMessage)
    }
}
