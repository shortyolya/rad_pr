package com.baltinfo.radius.feed.xml.export.services;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.db.controller.VLoadJournalController;
import com.baltinfo.radius.db.model.VLoadJournal;
import com.baltinfo.radius.loadauction.service.MessageAboutNewTenderService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * @author Kulikov Semyon
 * @since 30.01.2020
 */

public class MessageAboutNewTenderServiceTest {

    private MessageAboutNewTenderService messageAboutNewTenderService;
    private VLoadJournalController vLoadJournalController;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        messageAboutNewTenderService = (MessageAboutNewTenderService) context.getBean("messageAboutNewTenderService");
        vLoadJournalController = (VLoadJournalController) context.getBean("vLoadJournalController");
    }

//    @Test
    public void test() {
        System.out.println(new Date());
        VLoadJournal auctionFromJournal = vLoadJournalController.getLoadJournalByBaUnid(316L);
        messageAboutNewTenderService.createMessageEmail(auctionFromJournal);
        auctionFromJournal = vLoadJournalController.getLoadJournalByBaUnid(299L);
        messageAboutNewTenderService.createMessageEmail(auctionFromJournal);
        auctionFromJournal = vLoadJournalController.getLoadJournalByBaUnid(332L);
        messageAboutNewTenderService.createMessageEmail(auctionFromJournal);
        auctionFromJournal = vLoadJournalController.getLoadJournalByBaUnid(343L);
        messageAboutNewTenderService.createMessageEmail(auctionFromJournal);
    }
}
