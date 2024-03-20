package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * @author Suvorina Aleksandra
 * @since 13.10.2021
 */
public class JsonExportServiceTest {

    @Test
    public void loadJsonFromString() throws IOException {
        JsonExportService jsonExportService = new JsonExportService();
//        byte[] encoded = Files.readAllBytes(Paths.get("D://temp/Tender_1021300001029_10012023.json"));
//        String json = new String(encoded, StandardCharsets.UTF_8);
        String json = "{\n" +
                "  \"publication\" : {\n" +
                "    \"bank\" : {\n" +
                "      \"inn\" : \"5008004581\",\n" +
                "      \"bik\" : \"044583259\",\n" +
                "      \"ogrn\" : \"1025000003830\",\n" +
                "      \"nameBank\" : \"АКЦИОНЕРНЫЙ КОММЕРЧЕСКИЙ БАНК \\\"НАЦИОНАЛЬНЫЙ ЗАЛОГОВЫЙ БАНК\\\" ОТКРЫТОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО\"\n" +
                "    },\n" +
                "    \"tenders\" : {\n" +
                "      \"typeTender\" : \"Обычный торг\",\n" +
                "      \"tradeId\" : \"\",\n" +
                "      \"numberOrder\" : \"\",\n" +
                "      \"tender\" : [ {\n" +
                "        \"idUser\" : \"1015\",\n" +
                "        \"etp\" : \"АО \\\"РАД\\\"\",\n" +
                "        \"orgTender\" : \"АО \\\"РАД\\\"\",\n" +
                "        \"typeTender\" : \"ppp\",\n" +
                "        \"tradeID\" : 8938,\n" +
                "        \"numberTender1\" : \"\",\n" +
                "        \"formTender\" : \"\",\n" +
                "        \"formApp\" : \"\",\n" +
                "        \"numberTender2\" : \"\",\n" +
                "        \"reviewTender\" : \"с 09:00 до 17:00; г. Москва, Павелецкая наб., д. 8, стр. 1; +7 (495) 725-31-47, доб. 46-58\",\n" +
                "        \"phone\" : \"+7 (495) 725-31-47, доб. 46-58; контактный номер телефона «Информационного центра по реализации активов» 8-800-505-80-32\",\n" +
                "        \"amountPeriod\" : 10,\n" +
                "        \"amountLot\" : 1,\n" +
                "        \"numberLots\" : \"\",\n" +
                "        \"report\" : \"https://www.asv.org.ru/news//\",\n" +
                "        \"periods\" : [ {\n" +
                "          \"numberPeriod\" : 1,\n" +
                "          \"startDatePeriod\" : \"01.03.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"13.04.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"01.03.2022\",\n" +
                "          \"endRequestPeriod\" : \"08.04.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 2,\n" +
                "          \"startDatePeriod\" : \"14.04.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"20.04.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"14.04.2022\",\n" +
                "          \"endRequestPeriod\" : \"15.04.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 3,\n" +
                "          \"startDatePeriod\" : \"21.04.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"27.04.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"21.04.2022\",\n" +
                "          \"endRequestPeriod\" : \"22.04.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 4,\n" +
                "          \"startDatePeriod\" : \"28.04.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"04.05.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"28.04.2022\",\n" +
                "          \"endRequestPeriod\" : \"29.04.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 5,\n" +
                "          \"startDatePeriod\" : \"05.05.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"17.05.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"05.05.2022\",\n" +
                "          \"endRequestPeriod\" : \"12.05.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 6,\n" +
                "          \"startDatePeriod\" : \"18.05.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"24.05.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"18.05.2022\",\n" +
                "          \"endRequestPeriod\" : \"19.05.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 7,\n" +
                "          \"startDatePeriod\" : \"25.05.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"31.05.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"25.05.2022\",\n" +
                "          \"endRequestPeriod\" : \"26.05.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 8,\n" +
                "          \"startDatePeriod\" : \"01.06.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"07.06.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"01.06.2022\",\n" +
                "          \"endRequestPeriod\" : \"02.06.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 9,\n" +
                "          \"startDatePeriod\" : \"08.06.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"14.06.2022\",\n" +
                "          \"endTimePeriod\" : \"23:59\",\n" +
                "          \"startRequestPeriod\" : \"08.06.2022\",\n" +
                "          \"endRequestPeriod\" : \"09.06.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        }, {\n" +
                "          \"numberPeriod\" : 10,\n" +
                "          \"startDatePeriod\" : \"15.06.2022\",\n" +
                "          \"timeAuction\" : \"\",\n" +
                "          \"endDatePeriod\" : \"21.06.2022\",\n" +
                "          \"endTimePeriod\" : \"14:00\",\n" +
                "          \"startRequestPeriod\" : \"15.06.2022\",\n" +
                "          \"endRequestPeriod\" : \"16.06.2022\",\n" +
                "          \"endTimeRequestPeriod\" : \"14:00\"\n" +
                "        } ],\n" +
                "        \"lots\" : [ {\n" +
                "          \"id\" : 112697,\n" +
                "          \"number\" : 1,\n" +
                "          \"name\" : \"ООО «Дальрыбпродукт», ИНН 5047113111 солидарно с Коренчук Александром Васильевичем, Коренчук Илоной Эрнестовной\",\n" +
                "          \"priceFirst\" : 2891061.12,\n" +
                "          \"amount\" : \"4\",\n" +
                "          \"description\" : \"ООО «Дальрыбпродукт», ИНН 5047113111 солидарно с Коренчук Александром Васильевичем, Коренчук Илоной Эрнестовной, КД 2879-к от 16.06.2016, решение АС г. Москвы от 07.08.2020 по делу 2-2133/20 (2 891 061,12 руб.)\",\n" +
                "          \"classifier\" : 25,\n" +
                "          \"location\" : \"Москва\",\n" +
                "          \"coordinateLongitude\" : \"\",\n" +
                "          \"coordinateLatitude\" : \"\",\n" +
                "          \"address\" : \"Москва\",\n" +
                "          \"restrictions\" : \"\",\n" +
                "          \"additionalInformation\" : \"\",\n" +
                "          \"infoLot\" : [ {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 1,\n" +
                "            \"pricePeriod\" : 2891061.12,\n" +
                "            \"deposit\" : 289106.11\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 2,\n" +
                "            \"pricePeriod\" : 2732052.76,\n" +
                "            \"deposit\" : 273205.28\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 3,\n" +
                "            \"pricePeriod\" : 2573044.4,\n" +
                "            \"deposit\" : 257304.44\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 4,\n" +
                "            \"pricePeriod\" : 2414036.04,\n" +
                "            \"deposit\" : 241403.6\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 5,\n" +
                "            \"pricePeriod\" : 2255027.67,\n" +
                "            \"deposit\" : 225502.77\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 6,\n" +
                "            \"pricePeriod\" : 2096019.31,\n" +
                "            \"deposit\" : 209601.93\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 7,\n" +
                "            \"pricePeriod\" : 1937010.95,\n" +
                "            \"deposit\" : 193701.1\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 8,\n" +
                "            \"pricePeriod\" : 1778002.59,\n" +
                "            \"deposit\" : 177800.26\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 9,\n" +
                "            \"pricePeriod\" : 1618994.23,\n" +
                "            \"deposit\" : 161899.42\n" +
                "          }, {\n" +
                "            \"idLot\" : 112697,\n" +
                "            \"numberPeriod\" : 10,\n" +
                "            \"pricePeriod\" : 1459985.87,\n" +
                "            \"deposit\" : 145998.59\n" +
                "          } ],\n" +
                "          \"documents\" : [ ],\n" +
                "          \"images\" : [ ],\n" +
                "          \"assets\" : [ {\n" +
                "            \"typeAsset\" : 25,\n" +
                "            \"idSya\" : \"53067\",\n" +
                "            \"nameAsset\" : \"2879-к\",\n" +
                "            \"locationAsset\" : \"Москва\",\n" +
                "            \"nameFoAsset\" : \"АКЦИОНЕРНЫЙ КОММЕРЧЕСКИЙ БАНК \\\"НАЦИОНАЛЬНЫЙ ЗАЛОГОВЫЙ БАНК\\\" ОТКРЫТОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО\",\n" +
                "            \"aboutAsset\" : \"\",\n" +
                "            \"soonAsset\" : \"Нет\",\n" +
                "            \"deposits\" : \"Нет\",\n" +
                "            \"other\" : \"\",\n" +
                "            \"restrictionsAsset\" : \"\",\n" +
                "            \"documentsAsset\" : [ ],\n" +
                "            \"imagesAsset\" : [ ],\n" +
                "            \"creditType\" : \"Кредиты ЮЛ\",\n" +
                "            \"borrowerName\" : \"ООО \\\"Дальрыбпродукт\\\"\",\n" +
                "            \"borrowerInn\" : \"5047113111\",\n" +
                "            \"weightAverageInterestRate\" : 18,\n" +
                "            \"averageRepaymentDate\" : \"09.06.2017\",\n" +
                "            \"debtRepaymentMethod\" : \"В конце срока\",\n" +
                "            \"availability\" : \"Нет\",\n" +
                "            \"descriptionPledges\" : {\n" +
                "              \"descriptionPledge\" : [ {\n" +
                "                \"about\" : \"Поручительство\",\n" +
                "                \"typeAsset\" : 24,\n" +
                "                \"idSya\" : \"1498530\",\n" +
                "                \"nameAsset\" : \"Поручительство, Коренчук Александр Васильевич\",\n" +
                "                \"locationAsset\" : \"Москва\",\n" +
                "                \"nameFoAsset\" : \"АКЦИОНЕРНЫЙ КОММЕРЧЕСКИЙ БАНК \\\"НАЦИОНАЛЬНЫЙ ЗАЛОГОВЫЙ БАНК\\\" ОТКРЫТОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО\",\n" +
                "                \"aboutAsset\" : \"\",\n" +
                "                \"soonAsset\" : \"Нет\",\n" +
                "                \"deposits\" : \"Да\",\n" +
                "                \"other\" : \"\",\n" +
                "                \"restrictionsAsset\" : \"\",\n" +
                "                \"documentsAsset\" : [ ],\n" +
                "                \"imagesAsset\" : [ ],\n" +
                "                \"legalStatus\" : \"Физическое лицо\",\n" +
                "                \"currency\" : \"Российский рубль\",\n" +
                "                \"kd\" : \"2879-к/п1\",\n" +
                "                \"dateKd\" : \"16.06.2016\",\n" +
                "                \"amountOfBalance\" : 37727000\n" +
                "              }, {\n" +
                "                \"about\" : \"Поручительство\",\n" +
                "                \"typeAsset\" : 24,\n" +
                "                \"idSya\" : \"1498531\",\n" +
                "                \"nameAsset\" : \"Поручительство, Коренчук Илона Эрнестовна\",\n" +
                "                \"locationAsset\" : \"Москва\",\n" +
                "                \"nameFoAsset\" : \"АКЦИОНЕРНЫЙ КОММЕРЧЕСКИЙ БАНК \\\"НАЦИОНАЛЬНЫЙ ЗАЛОГОВЫЙ БАНК\\\" ОТКРЫТОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО\",\n" +
                "                \"aboutAsset\" : \"\",\n" +
                "                \"soonAsset\" : \"Нет\",\n" +
                "                \"deposits\" : \"Да\",\n" +
                "                \"other\" : \"\",\n" +
                "                \"restrictionsAsset\" : \"\",\n" +
                "                \"documentsAsset\" : [ ],\n" +
                "                \"imagesAsset\" : [ ],\n" +
                "                \"legalStatus\" : \"Физическое лицо\",\n" +
                "                \"currency\" : \"Российский рубль\",\n" +
                "                \"kd\" : \"2879-к/п2\",\n" +
                "                \"dateKd\" : \"16.06.2016\",\n" +
                "                \"amountOfBalance\" : 37727000\n" +
                "              }, {\n" +
                "                \"about\" : \"Залог Залог товаров в обороте\",\n" +
                "                \"typeAsset\" : 31,\n" +
                "                \"idSya\" : \"1498438\",\n" +
                "                \"nameAsset\" : \"Товары в обороте (подтверждено документами)\",\n" +
                "                \"locationAsset\" : \"Московская область\",\n" +
                "                \"nameFoAsset\" : \"АКЦИОНЕРНЫЙ КОММЕРЧЕСКИЙ БАНК \\\"НАЦИОНАЛЬНЫЙ ЗАЛОГОВЫЙ БАНК\\\" ОТКРЫТОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО\",\n" +
                "                \"aboutAsset\" : \"\",\n" +
                "                \"soonAsset\" : \"Нет\",\n" +
                "                \"deposits\" : \"Да\",\n" +
                "                \"other\" : \"\",\n" +
                "                \"restrictionsAsset\" : \"\",\n" +
                "                \"documentsAsset\" : [ ],\n" +
                "                \"imagesAsset\" : [ ],\n" +
                "                \"mark\" : \"\",\n" +
                "                \"model\" : \"\",\n" +
                "                \"address\" : \"Московская обл., г. Наро-Фоминск, ул. Московская, 15; Московская обл., Наро-Фоминский район, п. Селятино\",\n" +
                "                \"typeOfObject\" : \"Прочее имущество\",\n" +
                "                \"amount\" : 1\n" +
                "              } ]\n" +
                "            },\n" +
                "            \"currency\" : \"Российский рубль\",\n" +
                "            \"kd\" : \"2879-к\",\n" +
                "            \"balanceDate\" : \"29.11.2019\",\n" +
                "            \"dateKd\" : \"16.06.2016\",\n" +
                "            \"amountOfBalance\" : 1398942.58,\n" +
                "            \"documentStorage\" : \"Москва\",\n" +
                "            \"overdue\" : 0,\n" +
                "            \"trial\" : \"Да\",\n" +
                "            \"endDate\" : \"22.03.2017\"\n" +
                "          } ],\n" +
                "          \"urlLotTorgiasv\" : \"https://www.torgiasv.ru/sales/214/112697\",\n" +
                "          \"urlLot1\" : \"https://catalog.lot-online.ru/e-auction/asv/8938/112697/\"\n" +
                "        } ],\n" +
                "        \"urlTender1\" : \"https://catalog.lot-online.ru/e-auction/asv/8938/\",\n" +
                "        \"urlTender2\" : \"https://catalog.lot-online.ru/e-auction/asv/8938/\"\n" +
                "      } ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Result<Publication, String> result = jsonExportService.loadJsonFromString(json, "test");
        assertEquals(result.isSuccess(), true);

    }
}
