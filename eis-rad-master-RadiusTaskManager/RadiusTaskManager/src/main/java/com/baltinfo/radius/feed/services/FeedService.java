package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.feed.utils.ResultForFeed;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @author Suvorina Aleksandra
 * @since 15.10.2020
 */
public abstract class FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedService.class);

    public abstract void formFeed(Long mevUnid, Long subUnid);

    protected Result<String, String> getXml(ResultForFeed result) {
        try {
            Object feed = result.getFeed();
            JAXBContext context = JAXBContext.newInstance(feed.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter writer = new StringWriter();
            marshaller.marshal(result.getFeed(), writer);
            return Result.ok(writer.toString());
        } catch (JAXBException e) {
            logger.error("Error when trying to convert object to xml", e);
            return Result.error(e.getMessage());
        }
    }
}
