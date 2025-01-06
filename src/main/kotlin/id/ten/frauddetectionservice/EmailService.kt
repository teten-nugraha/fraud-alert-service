package id.ten.frauddetectionservice

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {

    fun sendFraudAlertEmail(alert: String) {
        val message = SimpleMailMessage().apply {
            from = "test@mail.com"
            setTo("security-team@example.com")
            subject = "Fraud Alert Detected"
            text = alert
        }
        mailSender.send(message)
    }
}
