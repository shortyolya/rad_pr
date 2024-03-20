package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.constants.AuctionParticipantLimitation;
import com.baltinfo.radius.db.constants.CalcPriceConstant;
import com.baltinfo.radius.db.constants.DepositTypeConstant;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.constants.TypeSubjectConstant;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.dto.DateDto;
import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.dto.LoadLotDto;
import com.baltinfo.radius.db.dto.LoadRsDto;
import com.baltinfo.radius.db.model.ClAsv;
import com.baltinfo.radius.loadauction.constants.CompetitionManagerConstant;
import com.baltinfo.radius.loadauction.constants.DpPayeeConstant;
import com.baltinfo.radius.loadauction.ftp.FileStorage;
import com.baltinfo.radius.loadauction.model.AssetFull;
import com.baltinfo.radius.loadauction.model.Bank;
import com.baltinfo.radius.loadauction.model.Lot;
import com.baltinfo.radius.loadauction.model.LotInfo;
import com.baltinfo.radius.loadauction.model.Period;
import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.loadauction.model.Tender;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * Сервис загрузки xml файлов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class XmlExportService {

    private static final Logger logger = LoggerFactory.getLogger(XmlExportService.class);

    /**
     * Производит загрузку информации из строки {@param source}
     * в объект {@link Publication}
     *
     * @param source xml-файл записанный в строку типа String
     * @return объект {@link Publication} в случае успешной загрузки или строку, содержащую информацию об ошибке
     */
    public Result<Publication, String> loadXmlFromString(String source, String filename) {
        try {
            XmlMapper xmlMapper = new XmlMapper(createJacksonXmlModule());
            xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            Publication deserializedData = xmlMapper.readValue(source, Publication.class);
            if (deserializedData != null) {
                return Result.ok(deserializedData);
            } else {
                logger.error("Can't deserialize xml-file = {}, deserialization result is null", filename);
                return Result.error("Can't deserialize xml-file, deserialization result is null");
            }
        } catch (IOException e) {
            logger.error("Can't deserialize xml-file = {}", filename, e);
            return Result.error("Can't deserialize xml-file" + e);
        }
    }

    private JacksonXmlModule createJacksonXmlModule() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.addDeserializer(BigDecimal.class, new JsonDeserializer<BigDecimal>() {
            @Override
            public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                String valueAsString = jp.getValueAsString();
                if (StringUtils.isEmpty(valueAsString)) {
                    return null;
                }
                return new BigDecimal(valueAsString.replaceAll(",", "\\.").replaceAll("[^0-9.]", ""));
            }
        });
        module.addDeserializer(Integer.class, new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                String valueAsString = jp.getValueAsString();
                if (StringUtils.isEmpty(valueAsString)) {
                    return null;
                }
                return new Integer(valueAsString.replaceAll("[^0-9]", ""));
            }
        });
        module.addDeserializer(Long.class, new JsonDeserializer<Long>() {
            @Override
            public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                String valueAsString = jp.getValueAsString();
                if (StringUtils.isEmpty(valueAsString)) {
                    return null;
                }
                return new Long(valueAsString.replaceAll("[^0-9]", ""));
            }
        });
        module.setDefaultUseWrapper(false);
        return module;
    }
}
