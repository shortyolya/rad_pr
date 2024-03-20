package com.baltinfo.radius.mailsender;

import com.baltinfo.radius.rest.client.HttpRequestService;
import com.baltinfo.radius.utils.Result;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

public class MailSenderClientTest {

    @Test
    @Ignore
    public void test() {
        HttpRequestService vitrinaHttpRequestService = new HttpRequestService("https://test-catalog.lot-online.ru/");

        MessagePack message = new MessagePack();
        message.setCabinet(true);
        message.setDisterb(false);
        MessageRecipient messageRecipient = new MessageRecipient();
        messageRecipient.setBp(9915L);
        message.setTo(Arrays.asList(messageRecipient));
        message.setSubject("Платежное поручение по лоту <span>РАД-110852</span>");
        message.setText("<p>Здравствуйте!</p><p>Вам было направлено платежное поручение по лоту <span>РАД-110852</span>.</p><p><a href=\"https://test-catalog.lot-online.ru/index.php?dispatch=rad_files.get&amp;id=55604\"><span>Скачать платежное поручение</span></p>");
        message.setEmail(false);
        Result<MessagePackResult, String> result = vitrinaHttpRequestService.executePostObject("/mail-sender/message/send", MessagePackResult.class, message);
        if (result.isSuccess()) {

        }
    }

}