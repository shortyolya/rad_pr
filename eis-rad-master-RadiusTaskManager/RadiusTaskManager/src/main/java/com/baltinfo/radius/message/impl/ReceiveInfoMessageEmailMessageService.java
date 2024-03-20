package com.baltinfo.radius.message.impl;

import com.baltinfo.radius.application.configuration.EmailMessageProperties;
import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.BlockAuctionController;
import com.baltinfo.radius.db.controller.EventController;
import com.baltinfo.radius.db.controller.ParticipantAgentController;
import com.baltinfo.radius.db.model.BlockAuction;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.message.CreateEmailMessageService;
import com.baltinfo.radius.message.EmailMessageService;
import com.baltinfo.radius.message.EmailParams;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *     Сервис для проверки наличия документов с типом 8 (информационное сообщение) уведомление по которым ещё не было отправлено на почту
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 01.04.2020
 */
public class ReceiveInfoMessageEmailMessageService implements EmailMessageService {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveInfoMessageEmailMessageService.class);

    private final EventController eventController;
    private final BlockAuctionController blockAuctionController;
    private final EmailMessageProperties emailMessageProperties;
    private final CreateEmailMessageService createEmailMessageService;
    private final ParticipantAgentController participantAgentController;

    public ReceiveInfoMessageEmailMessageService(EventController eventController, BlockAuctionController blockAuctionController,
                                                 EmailMessageProperties emailMessageProperties, CreateEmailMessageService createEmailMessageService, ParticipantAgentController participantAgentController) {
        this.eventController = Objects.requireNonNull(eventController, "Can't get eventController");
        this.blockAuctionController = Objects.requireNonNull(blockAuctionController, "Can't get blockAuctionController");
        this.emailMessageProperties = Objects.requireNonNull(emailMessageProperties, "Can't get emailMessageProperties");
        this.createEmailMessageService = Objects.requireNonNull(createEmailMessageService, "Can't get createEmailMessageService");
        this.participantAgentController = Objects.requireNonNull(participantAgentController, "Can't get participantAgentController");
    }

    public void sendEmailMessage() {
        List<Document> documentList = eventController.getDocumentsWithoutEvent(TypeDocConstant.INFORMATION_MESSAGE, TypeEventConstant.RECEIVE_INFORMATION_MESSAGE);
        for (Document document : documentList) {
            BlockAuction blockAuction = blockAuctionController.getBlockAuctionByUnid(document.getBaUnid());
            ParticipantAgent pa = participantAgentController.getParticipantAgent(blockAuction.getPaUnid());
            EmailParams params = new EmailParams(TypeEventConstant.RECEIVE_INFORMATION_MESSAGE.getUnid(), emailMessageProperties.getInfoMessageTheme(),
                    emailMessageProperties.getInfoMessageBody(), emailMessageProperties.getAsvEmail());
            Result<Void, String> createEventResult = createEmailMessageService.createEmailMessage(blockAuction, params, pa);
            if (createEventResult.isError()) {
                logger.error(createEventResult.getError());
            }
        }
    }
}
