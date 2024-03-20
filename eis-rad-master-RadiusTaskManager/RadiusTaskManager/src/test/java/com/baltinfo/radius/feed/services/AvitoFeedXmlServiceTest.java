package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.SubjectConstant;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.avito.model.Ads;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class AvitoFeedXmlServiceTest {

    private FeedService avitoFeedService;

    @Before
    public void pre() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        avitoFeedService = (FeedService) context.getBean("avitoFeedService");
    }

    @Ignore
    @Test
    public void test() throws JAXBException {
        Ads ads = new Ads();
        Ad ad = new Ad();
        ad.setId("ID");
        ad.setAddress("ADDRESS");
        ad.setDescription("DESCRIPTION");
        ad.setCategory("CATEGORY");
        ad.setOperationType("Продам");
        Double price = 123.45d;
        //ad.setPrice(price.longValue());
        ad.setRooms("Студия");
        ad.setSquare(price);
        ad.setFloor("10");
        ad.setFloors(12);
        ad.setHouseType("HOUSE_TYPE");
        ad.setMarketType("MARKET_TYPE");
        ad.setPropertyRights(null);
        ads.getAd().add(ad);

        JAXBContext context = JAXBContext.newInstance(Ads.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(ads, writer);
        System.out.println(writer.toString());
    }

    @Ignore
    @Test
    public void test2_RAD() {
        avitoFeedService.formFeed(MarketingEvent.AVITO.getUnid(), SubjectConstant.RAD.getId());
    }

    @Ignore
    @Test
    public void test2_RADHOLDING() {
        avitoFeedService.formFeed(MarketingEvent.AVITO.getUnid(), SubjectConstant.RAD_HOLDING.getId());
    }
}
