package com.secret.payment.business

import com.icegreen.greenmail.util.GreenMailUtil
import com.secret.payment.Account
import com.secret.payment.Transfer
import grails.testing.mixin.integration.Integration
import spock.lang.Specification
import spock.lang.Unroll

import javax.mail.Part

import static com.icegreen.greenmail.util.GreenMailUtil.getAddressList
import static com.secret.payment.utils.TestHelperUtils.DESTINATION_ACCOUNT_PROPERTIES_MAP
import static com.secret.payment.utils.TestHelperUtils.SOURCE_ACCOUNT_PROPERTIES_MAP
import static javax.mail.Message.RecipientType.TO

@Integration
class NotificationServiceTest extends Specification {
    def notificationService
    def greenMail

    static final String FROM_EMAIL = "no-reply@payment.com"
    static final String SUBJECT = "Transfer notification"

    @Unroll
    def "test NotifyTransfer"() {
        given:
        Account sourceAccount = Account.of(SOURCE_ACCOUNT_PROPERTIES_MAP)
        Account destinationAccount = Account.of(DESTINATION_ACCOUNT_PROPERTIES_MAP)
        Transfer transfer = new Transfer(sourceAccount: sourceAccount, destinationAccount: destinationAccount, amount: 100)

        when:
        notificationService.notifyTransfer(transfer)

        then:
        assertMailCount(2)
        Part sourceAccountMessage = greenMail.getReceivedMessages()[0] as Part
        Part destinationAccountMessage = greenMail.getReceivedMessages()[1] as Part

        assertMailContent(sourceAccountMessage, sourceAccount.email, FROM_EMAIL, SUBJECT)
        assertMailContent(destinationAccountMessage, destinationAccount.email, FROM_EMAIL, SUBJECT)
    }

    @Unroll
    def "test NotifyTransferParty"() {
        given:
        String toEmail = "user@test.com"
        String body = "My email body"

        when:
        notificationService.notifyTransferParty(toEmail, body)

        then:
        Part message = greenMail.getReceivedMessages()[0] as Part
        assertMailContent(message, toEmail, FROM_EMAIL, SUBJECT, body)
    }

    private void assertMailCount(int count) {
        assert greenMail.getReceivedMessages().length == count
    }

    private static void assertMailContent(Part mailMessage, String toEmail, String fromEmail, String subject, String body = null) {
        assert toEmail == getAddressList(mailMessage.getRecipients(TO))
        assert fromEmail == getAddressList(mailMessage.from)
        assert subject == mailMessage.subject
        if (body) {
            assert body == GreenMailUtil.getBody(mailMessage)
        }
    }
}
