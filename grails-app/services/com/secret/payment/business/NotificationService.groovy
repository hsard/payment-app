package com.secret.payment.business

import com.secret.payment.Transfer
import grails.gorm.transactions.Transactional
import grails.plugins.mail.MailService

@Transactional(readOnly = true)
class NotificationService {

    MailService mailService

    /**
     * @param transfer to be notified
     * Notifies source and destination accounts holders about the transfer
     */
    def notifyTransfer(Transfer transfer) {
        String notificationBody = "Transfer successfull!" +
                " \n From ${transfer.sourceAccount.name} " +
                " \n To ${transfer.destinationAccount.name}" +
                " \n Amount: ${transfer.amount}"
        notifyTransferParty(transfer.sourceAccount.email, notificationBody)
        notifyTransferParty(transfer.destinationAccount.email, notificationBody)
    }

    /**
     * @param transfer to be notified
     * Notifies source and destination accounts holders about the transfer
     */
    def notifyTransferParty(String toEmail, String emailBody) {
        notifyTransferParty(toEmail, "no-reply@payment.com", "Transfer notification", emailBody)
    }

    /**
     * @param toEmail recipient of the email
     * @param fromEmail sender of the email
     * @param emailSubject subject of the email
     * @param emailBody body of the email
     * Notifies source and destination accounts holders about the transfer
     */
    def notifyTransferParty(String toEmail, String fromEmail, String emailSubject, String emailBody) {
        mailService.sendMail {
            from fromEmail
            to toEmail
            subject emailSubject
            body emailBody
        }
    }
}
