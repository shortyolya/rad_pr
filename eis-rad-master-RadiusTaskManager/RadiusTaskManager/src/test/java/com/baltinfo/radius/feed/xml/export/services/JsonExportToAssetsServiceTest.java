package com.baltinfo.radius.feed.xml.export.services;

import com.baltinfo.radius.loadauction.service.JsonExportToAssetsService;
import com.baltinfo.radius.utils.Result;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <p>
 *     Тест проверки конвертации json в Asset'ы
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 29.01.2020
 */
public class JsonExportToAssetsServiceTest {
    private final String json = "[ {\n" +
            "  \"typeAsset\" : 25,\n" +
            "  \"idSya\" : \"6496125\",\n" +
            "  \"nameAsset\" : \"47/18-к\",\n" +
            "  \"locationAsset\" : \"Нижегородская область\",\n" +
            "  \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "  \"soonAsset\" : \"Нет\",\n" +
            "  \"deposits\" : \"Нет\",\n" +
            "  \"borrowerName\" : \"ООО \\\"Стаблес\\\"\",\n" +
            "  \"borrowerInn\" : \"5249131040\",\n" +
            "  \"kd\" : \"47/18-к\",\n" +
            "  \"weightAverageInterestRate\" : 6.5,\n" +
            "  \"dateKd\" : \"47/18-к\",\n" +
            "  \"averageRepaymentDate\" : \"27.06.2025\",\n" +
            "  \"debtRepaymentMethod\" : \"Ежемесячно по графику\",\n" +
            "  \"availability\" : \"Да\",\n" +
            "  \"descriptionPledges\" : {\n" +
            "    \"descriptionPledge\" : [ {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494371\",\n" +
            "      \"nameAsset\" : \"Здание - 1035.2 кв.м., адрес: Нижегородская область, р-н Тоншаевский, рп Тоншаево, ул Я.Горева, д 31\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 1035.2,\n" +
            "      \"address\" : \"Нижегородская область, р-н Тоншаевский, рп Тоншаево, ул Я.Горева, д 31\",\n" +
            "      \"floorNumber\" : \"2\",\n" +
            "      \"floors\" : 2,\n" +
            "      \"cadastralNumber\" : \"52:02:0090003:226\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 1,\n" +
            "      \"idSya\" : \"12494372\",\n" +
            "      \"nameAsset\" : \"Земельные участки - 1073 кв.м., адрес: Нижегородская область, р-н. Тоншаевский, рп. Тоншаево, ул. Якова Горева, д. 31\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Земельные участки\",\n" +
            "      \"square\" : 1073,\n" +
            "      \"address\" : \"Населенных пунктов (поселений)\",\n" +
            "      \"landCategory\" : \"Нижегородская область, р-н. Тоншаевский, рп. Тоншаево, ул. Якова Горева, д. 31\",\n" +
            "      \"permittedUse\" : \"Для иных видов использования, характерных для населенных пунктов\",\n" +
            "      \"distanceToTheRegionalCenter\" : null,\n" +
            "      \"cadastralNumber\" : \"52:02:0090003:87\",\n" +
            "      \"communications\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 31,\n" +
            "      \"idSya\" : \"7240660\",\n" +
            "      \"nameAsset\" : \"Станок горбыльно-ребровой, развалочный, торцовочный, многопильный\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : \"Реквизиты для  почтового уведомления Залогодателя об осмотре: 603004, Нижегородская обл, г. Нижний Новгород, Ленина пр-кт, дом 109, офис 602. Наличие обеспечения не подтверждено.Наличие обеспечения не подтверждено\",\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог оборудования\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Прочее имущество\",\n" +
            "      \"model\" : null,\n" +
            "      \"mark\" : null,\n" +
            "      \"address\" : \"Нижегородская обл., г. Семенов, проезд Железнодорожный, д.12.\",\n" +
            "      \"amount\" : 30\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494425\",\n" +
            "      \"nameAsset\" : \"Здание - 1260.6 кв.м., адрес: Нижегородская область, г Семенов проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 1260.6,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : \"4\",\n" +
            "      \"floors\" : 5,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:249\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 1,\n" +
            "      \"idSya\" : \"12494426\",\n" +
            "      \"nameAsset\" : \"Земельные участки - 3822 кв.м., адрес: Российская Федерация, Нижегородская обл, городской округ Семеновский, г Семенов, проезд Железнодорожный, земельный участок 12а\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Земельные участки\",\n" +
            "      \"square\" : 3822,\n" +
            "      \"address\" : \"Населенных пунктов (поселений)\",\n" +
            "      \"landCategory\" : \"Российская Федерация, Нижегородская обл, городской округ Семеновский, г Семенов, проезд Железнодорожный, земельный участок 12а\",\n" +
            "      \"permittedUse\" : \"для размещения подъезных железнодорожных путей\",\n" +
            "      \"distanceToTheRegionalCenter\" : null,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:121\",\n" +
            "      \"communications\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494427\",\n" +
            "      \"nameAsset\" : \"Сооружение -  кв.м., адрес: Нижегородская обл, р-н Семеновский, г Семенов, проезд Железнодорожный, дом 12, подъездные железнодорожные пути.\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Сооружение\",\n" +
            "      \"square\" : null,\n" +
            "      \"address\" : \"Нижегородская обл, р-н Семеновский, г Семенов, проезд Железнодорожный, дом 12, подъездные железнодорожные пути.\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : null,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:136\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 1,\n" +
            "      \"idSya\" : \"12494428\",\n" +
            "      \"nameAsset\" : \"Земельные участки - 61113 кв.м., адрес: Российская Федерация,Нижегородская область,городской округ Семеновский,город Семенов,проезд Железнодорожный,земельный участок 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Земельные участки\",\n" +
            "      \"square\" : 61113,\n" +
            "      \"address\" : \"Населенных пунктов (поселений)\",\n" +
            "      \"landCategory\" : \"Российская Федерация,Нижегородская область,городской округ Семеновский,город Семенов,проезд Железнодорожный,земельный участок 12\",\n" +
            "      \"permittedUse\" : \"для производственных целей\",\n" +
            "      \"distanceToTheRegionalCenter\" : null,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:38\",\n" +
            "      \"communications\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494429\",\n" +
            "      \"nameAsset\" : \"Сооружение -  кв.м., адрес: Нижегородская область, Семеновский район, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Сооружение\",\n" +
            "      \"square\" : null,\n" +
            "      \"address\" : \"Нижегородская область, Семеновский район, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : null,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:251\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494430\",\n" +
            "      \"nameAsset\" : \"Здание - 395.9 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 395.9,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : \"2\",\n" +
            "      \"floors\" : 2,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:256\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494431\",\n" +
            "      \"nameAsset\" : \"Здание - 3231.1 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 3231.1,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 5,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:172\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494432\",\n" +
            "      \"nameAsset\" : \"Здание - 351.4 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 351.4,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:254\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494433\",\n" +
            "      \"nameAsset\" : \"Здание - 584.7 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 584.7,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:261\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494434\",\n" +
            "      \"nameAsset\" : \"Здание - 280.1 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 280.1,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:260\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494435\",\n" +
            "      \"nameAsset\" : \"Здание - 198.3 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 198.3,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:257\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494436\",\n" +
            "      \"nameAsset\" : \"Здание - 248.7 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 248.7,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 2,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:253\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494437\",\n" +
            "      \"nameAsset\" : \"Здание - 123.8 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 123.8,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 2,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:252\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494438\",\n" +
            "      \"nameAsset\" : \"Здание - 624 кв.м., адрес: Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 624,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:248\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494439\",\n" +
            "      \"nameAsset\" : \"Здание - 728.5 кв.м., адрес: Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 728.5,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:246\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494440\",\n" +
            "      \"nameAsset\" : \"Здание - 1846.7 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 1846.7,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 16,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:255\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494441\",\n" +
            "      \"nameAsset\" : \"Здание - 409.9 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 409.9,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 2,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:258\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494442\",\n" +
            "      \"nameAsset\" : \"Здание - 14.8 кв.м., адрес: Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 14.8,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:247\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494443\",\n" +
            "      \"nameAsset\" : \"Здание - 219.8 кв.м., адрес: Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 219.8,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, проезд Железнодорожный, д 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 4,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:259\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 4,\n" +
            "      \"idSya\" : \"12494444\",\n" +
            "      \"nameAsset\" : \"Здание - 150.5 кв.м., адрес: Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог коммерческой недвижимости\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"typeOfObject\" : \"Здание\",\n" +
            "      \"square\" : 150.5,\n" +
            "      \"address\" : \"Нижегородская область, г Семенов, г. Семенов, проезд Железнодорожный, д. 12\",\n" +
            "      \"floorNumber\" : null,\n" +
            "      \"floors\" : 1,\n" +
            "      \"cadastralNumber\" : \"52:12:1800327:250\",\n" +
            "      \"communications\" : null,\n" +
            "      \"categor\" : null,\n" +
            "      \"assessmentValue\" : null,\n" +
            "      \"assessmentDate\" : null,\n" +
            "      \"shareFo\" : 100,\n" +
            "      \"yearBuilt\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 9,\n" +
            "      \"idSya\" : \"7240348\",\n" +
            "      \"nameAsset\" : \" null, null, VIN отсутствует\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : \"Наличие обеспечения не подтверждено.Наличие обеспечения не подтверждено\",\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог оборудования\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"address\" : \"Нижегородская обл., г. Семенов, проезд Железнодорожный, д.12.\"\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 9,\n" +
            "      \"idSya\" : \"7240858\",\n" +
            "      \"nameAsset\" : \" null, null, VIN отсутствует\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : \"Наличие обеспечения не подтверждено.Наличие обеспечения не подтверждено\",\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Залог Залог оборудования\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"address\" : \"Нижегородская обл., д. Бабылевка, ул. Кирпичная, д.3\"\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 24,\n" +
            "      \"idSya\" : \"7242549\",\n" +
            "      \"nameAsset\" : \"Поручительство, Кесельман Михаил Викторович\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"aboutAsset\" : null,\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"other\" : null,\n" +
            "      \"restrictionsAsset\" : null,\n" +
            "      \"about\" : \"Поручительство\",\n" +
            "      \"Documents_asset\" : null,\n" +
            "      \"Images_asset\" : null,\n" +
            "      \"creditType\" : null,\n" +
            "      \"averageInterestRate\" : null,\n" +
            "      \"averageRepaymentDate\" : null,\n" +
            "      \"debtRepaymentMethod\" : null,\n" +
            "      \"availability\" : null,\n" +
            "      \"descriptionPledges\" : null,\n" +
            "      \"currency\" : \"Российский рубль\",\n" +
            "      \"kd\" : \"47/18-п\",\n" +
            "      \"balanceDate\" : null,\n" +
            "      \"dateKd\" : \"47/18-п\",\n" +
            "      \"amountOfBalance\" : 116400000,\n" +
            "      \"documentStorage\" : null,\n" +
            "      \"overdue\" : null,\n" +
            "      \"trial\" : null,\n" +
            "      \"endDate\" : null\n" +
            "    }, {\n" +
            "      \"typeAsset\" : 25,\n" +
            "      \"idSya\" : \"7242895\",\n" +
            "      \"nameAsset\" : \"Поручительство, АНО \\\"АРСГ МФО НО\\\"\",\n" +
            "      \"locationAsset\" : \"Нижегородская область\",\n" +
            "      \"nameFoAsset\" : \"Акционерное общество коммерческий банк \\\"Ассоциация\\\"\",\n" +
            "      \"soonAsset\" : \"Нет\",\n" +
            "      \"deposits\" : \"Да\",\n" +
            "      \"about\" : \"Поручительство\",\n" +
            "      \"borrowerName\" : \"АНО \\\"АРСГ МФО НО\\\"\",\n" +
            "      \"borrowerInn\" : \"5260248556\",\n" +
            "      \"kd\" : \"928\",\n" +
            "      \"dateKd\" : \"928\",\n" +
            "      \"currency\" : \"Российский рубль\",\n" +
            "      \"amountOfBalance\" : 18000000\n" +
            "    } ]\n" +
            "  },\n" +
            "  \"currency\" : \"Российский рубль\",\n" +
            "  \"amountOfBalance\" : 53205074,\n" +
            "  \"overdue\" : 0,\n" +
            "  \"trial\" : \"Нет\",\n" +
            "  \"documentStorage\" : \"Нижегородская область\",\n" +
            "  \"creditType\" : \"Кредиты ЮЛ\",\n" +
            "  \"balanceDate\" : \"19.09.2019\"\n" +
            "} ]";
    private final JsonExportToAssetsService service = new JsonExportToAssetsService();

//    @Ignore
    @Test
    public void convertJson() {
        Result<String, String> result = service.buildHtmlFromAsset(json);
        System.out.println(result.getResult());
    }
}
