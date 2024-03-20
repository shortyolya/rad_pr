package com.baltinfo.radius.dadata.services;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.dadata.client.DadataClient;
import com.baltinfo.radius.dadata.dto.AddressDto;
import com.baltinfo.radius.slowTest.configuration.DadataClientConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 *     Тестовый файл для проверки работы DadataService
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 13.12.2019
 */
public class DadataServiceTest {
    private static final String TEST_BASE_URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs";
    private static final String TEST_STANDARD_BASE_URL = "https://cleaner.dadata.ru/api/v1/clean/address";
    private static final String TEST_API_KEY = "ce79cd2bb1f0ec3f3a1dd9b3c527ffa6e35db0af";
    private static final String TEST_SECRET_KEY = "14a0c0612f07b7aa9f783c8e3a130348119627bb";
    private static final int TEST_TIMEOUT = 5000;
    private static final boolean DEMO_MODE = true;
    private final DadataClient dadataClient;

    public DadataServiceTest() {
        this.dadataClient =
                new DadataClient(TEST_BASE_URL, TEST_STANDARD_BASE_URL, TEST_API_KEY, TEST_SECRET_KEY, DEMO_MODE);
    }

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }


    @Ignore
    @Test
    public void should_true_then_returnedAddressDtoShouldBeLikeComparedValues_testFirst() {
        String address = "Архангельск, Логинова 20/175 48";
        ApplicationContext context = new AnnotationConfigApplicationContext(DadataClientConfiguration.class);
        DadataClient dadataClient = (DadataClient) context.getBean("dadataClient");
        DadataService dadataService = new DadataService(dadataClient);
        AddressDto addressDto = dadataService.createAddressDtoByAddress(address);
        Assert.assertEquals(addressDto.getFiasId(), "870ed7ef-a187-4969-8422-49a6aad1202f");
        Assert.assertEquals(addressDto.getRegionFiasId(), "294277aa-e25d-428c-95ad-46719c4ddb44");
        Assert.assertEquals(addressDto.getCityFiasId(), "06814fb6-0dc3-4bec-ba20-11f894a0faf5");
        Assert.assertEquals(addressDto.getStreetFiasId(), "870ed7ef-a187-4969-8422-49a6aad1202f");
        Assert.assertEquals(addressDto.getHouseNumber(), "20/175");
        Assert.assertNull(addressDto.getBlockNumber());
        Assert.assertEquals(addressDto.getFlatNumber(), "48");
    }

    @Ignore
    @Test
    public void should_true_then_returnedAddressDtoShouldBeLikeComparedValues_testSecond() {
        String address = "Санкт-Петербург, Торфяная дорога, 7 лит Ф, 812";
        ApplicationContext context = new AnnotationConfigApplicationContext(DadataClientConfiguration.class);
        DadataClient dadataClient = (DadataClient) context.getBean("dadataClient");
        DadataService dadataService = new DadataService(dadataClient);
        AddressDto addressDto = dadataService.createAddressDtoByAddress(address);
        Assert.assertEquals(addressDto.getFiasId(), "4972a027-c20b-4837-bffe-1a8fd2dbdb42");
        Assert.assertEquals(addressDto.getRegionFiasId(), "c2deb16a-0330-4f05-821f-1d09c93331e6");
        Assert.assertNull(addressDto.getAreaFiasId());
        Assert.assertNull(addressDto.getCityFiasId());
        Assert.assertNull(addressDto.getCityDistrictFiasId());
        Assert.assertNull(addressDto.getSettlementFiasId());
        Assert.assertEquals(addressDto.getStreetFiasId(), "9e8bc9e3-e687-4c1e-a9cb-283209129e21");
        Assert.assertEquals(addressDto.getHouseFiasId(), "4972a027-c20b-4837-bffe-1a8fd2dbdb42");
        Assert.assertEquals(addressDto.getHouseNumber(), "7");
        Assert.assertEquals(addressDto.getBlockNumber(), "ф");
        Assert.assertEquals(addressDto.getFlatNumber(), "812");
        Assert.assertEquals(addressDto.getLatitude(), "59.9908661");
        Assert.assertEquals(addressDto.getLongitude(), "30.2599807");
    }

    @Ignore
    @Test
    public void should_true_then_compareDataFromDBAndFromDadataService() {
        String address = "Камчатский край, Вилючинск, Спортивная ул, дом 5, кв. 10";
        //String address = "Ставропольский край, г. Минеральные Воды, ул. Новая, д. 2а";
        //String address = "Карачаево-Черкесская Республика, г. Черкесск, 69 км. автодороги «Невинномысск-Домбай»";
        DadataService dadataService = new DadataService(dadataClient);
        AddressDto addressDto = dadataService.createAddressDtoByAddress(address);
        Assert.assertEquals(addressDto.getRegionFiasId(), "d02f30fc-83bf-4c0f-ac2b-5729a866a207");
        Assert.assertEquals(addressDto.getCityFiasId(), "b75a9701-5cd4-4623-99d5-4fa7463ceb47");
        Assert.assertEquals(addressDto.getStreetFiasId(), "d927b49d-13d8-482f-908f-d4bc040f8572");
        Assert.assertEquals(addressDto.getHouseNumber(), "5");
        Assert.assertEquals(addressDto.getFlatNumber(), "10");
        System.out.println(addressDto.getLatitude() + "but data from DB - 52.936447");
        System.out.println(addressDto.getLongitude() + "but data from DB - 158.411406");
    }
}
