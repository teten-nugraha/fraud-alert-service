package id.ten.frauddetectionservice

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {

    fun sendFraudAlertEmail(alert: FraudAlert) {
        val message = SimpleMailMessage()
        message.setTo("security-team@example.com")
        message.subject = "Fraud Alert Detected"
        message.text = """
            Fraud detected for sender: ${alert.sender}.
            Total Amount Transferred: ${alert.totalAmount}.
            Date: ${alert.date}.
        """.trimIndent()
        mailSender.send(message)
    }
}
