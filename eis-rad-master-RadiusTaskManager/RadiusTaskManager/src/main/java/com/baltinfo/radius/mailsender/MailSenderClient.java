package com.baltinfo.radius.mailsender;

import com.baltinfo.radius.rest.client.HttpRequestService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSenderClient {
    private static final Logger logger = LoggerFactory.getLogger(MailSenderClient.class);

    private final HttpRequestService vitrinaHttpRequestService;

    public MailSenderClient(HttpRequestService vitrinaHttpRequestService) {
        this.vitrinaHttpRequestService = vitrinaHttpRequestService;
    }

    public Result<Void, String> sendMessage(MessagePack message) {

        try {
            Result<MessagePackResult, String> result = vitrinaHttpRequestService.executePostObject("/mail-sender/message/send", MessagePackResult.class, message);
            if (result.isSuccess()) {
                if (result.getResult().getCabinet() > 0 && result.getResult().getMessage() == null) {
                    return Result.ok();
                } else {
                    logger.error("Got result: " + result.getResult().getMessage());
                    return Result.error("Ошибка при отправке уведомления:" + result.getResult().getMessage());
                }
            }
            return Result.error("Ошибка при отправке уведомления");
        } catch (Throwable e) {
            logger.error("Error sendMessage message = {}", message, e);
            return Result.error("Ошибка при отправке уведомления");
        }
    }
}
