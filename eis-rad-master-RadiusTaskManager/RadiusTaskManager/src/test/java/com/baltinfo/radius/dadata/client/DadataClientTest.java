package com.baltinfo.radius.dadata.client;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.dadata.model.AddressResponse;
import com.baltinfo.radius.dadata.model.AddressStandardResponse;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.slowTest.configuration.DadataClientConfiguration;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * <p>
 * Тесты для проверки работы сервиса DadataClient
 * Тестовые запросы к API dadata.ru
 * Эмуляция работа сервиса
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 09.12.2019
 */

public class DadataClientTest {
    private static final String TEST_BASE_URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs";
    private static final String TEST_STANDARD_BASE_URL = "https://cleaner.dadata.ru/api/v1/clean/address";
    private static final String TEST_API_KEY = "ce79cd2bb1f0ec3f3a1dd9b3c527ffa6e35db0af";
    private static final String TEST_SECRET_KEY = "14a0c0612f07b7aa9f783c8e3a130348119627bb";
    private static final int TEST_TIMEOUT = 5000;
    private static final boolean DEMO_MODE = false;
    private final DadataClient dadataClient;

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    public DadataClientTest() {
        this.dadataClient =
                new DadataClient(TEST_BASE_URL, TEST_STANDARD_BASE_URL, TEST_API_KEY, TEST_SECRET_KEY,DEMO_MODE);
    }

    @Ignore
    @Test
    public void should_true_then_LatAndLonParametersAreValid() {
        String lat = "55.712";
        String lon = "37.714";
        Result<AddressResponse, String> response = dadataClient.getAddressByCoordinate(lat, lon);
        Assert.assertEquals(response.getResult().getSuggestions().get(0).getValue(), "г Москва, Волгоградский пр-кт, д 42 к 36");
    }

    @Ignore
    @Test
    public void should_true_then_addressWithThisLatAndLonParametersDoesNotExist() {
        String lat = "50.712";
        String lon = "37.714";
        Result<AddressResponse, String> response = dadataClient.getAddressByCoordinate(lat, lon);
        Assert.assertTrue(response.getResult().getSuggestions().isEmpty());
    }

    @Ignore
    @Test
    public void should_true_then_thereAre4AddressesWithSuchLatAndLonParameters() {
        String lat = "55.878";
        String lon = "37.653";
        Result<AddressResponse, String> response = dadataClient.getAddressByCoordinate(lat, lon);
        Assert.assertEquals(response.getResult().getSuggestions().size(), 4);
    }

    @Ignore
    @Test
    public void should_true_then_WithSuchLatAndLonParametersNearestAddressLikeInAnswerVariablesBelow() {
        String lat = "59.714";
        String lon = "30.413";
        String valueAnswer = "г Санкт-Петербург, г Пушкин, Софийский б-р, д 10";
        String unrestrictedValueAnswer = "г Санкт-Петербург, г Пушкин, Софийский б-р, д 10";
        Result<AddressResponse, String> response = dadataClient.getAddressByCoordinate(lat, lon);
        Assert.assertEquals(response.getResult().getSuggestions().get(0).getValue(), valueAnswer);
        Assert.assertEquals(response.getResult().getSuggestions().get(0).getUnrestrictedValue(), unrestrictedValueAnswer);
    }

    @Ignore
    @Test
    public void should_true_then_WithSuchLatAndLonParametersAddressesExist() {
        String lat = "59.715";
        String lon = "30.421";
        Result<AddressResponse, String> response = dadataClient.getAddressByCoordinate(lat, lon);
        Assert.assertFalse(response.getResult().toString().isEmpty());
    }

    @Ignore
    @Test
    public void should_true_then_mockDadataClientReturnSomeData_when_getAddressByCoordinateMethodIsCalled() {
        String lat = "59.715";
        String lon = "30.421";
        ApplicationContext context = new AnnotationConfigApplicationContext(DadataClientConfiguration.class);
        DadataClient dadataClient = (DadataClient) context.getBean("dadataClient");
        Result<AddressResponse, String> response = dadataClient.getAddressByCoordinate(lat, lon);
        Assert.assertFalse(response.getResult().toString().isEmpty());
    }

    @Ignore
    @Test
    public void should_true_then_ReturnedDataShouldBeLikeInAnswerBelow() {
        String address = "Архангельск, Логинова 20/175 48";
        Result<AddressStandardResponse[], String> response = dadataClient.getAddressStandard(address);
        Assert.assertEquals(response.getResult()[0].getCity(), "Архангельск");
        Assert.assertEquals(response.getResult()[0].getStreet(), "Логинова");
        Assert.assertEquals(response.getResult()[0].getHouse(), "20/175");
        Assert.assertEquals(response.getResult()[0].getFlat(), "48");
    }

    @Ignore
    @Test
    public void should_true_then_mockDadataClientReturnSomeData_when_getAddressStandardMethodIsCalled_AndThisDataShouldBeLikeInAnswerBelow() {
        String address = "Архангельск, Логинова 20/175 48";
        ApplicationContext context = new AnnotationConfigApplicationContext(DadataClientConfiguration.class);
        DadataClient dadataClient = (DadataClient) context.getBean("dadataClient");
        Result<AddressStandardResponse[], String> response = dadataClient.getAddressStandard(address);
        Assert.assertEquals(response.getResult()[0].getCity(), "Архангельск");
        Assert.assertEquals(response.getResult()[0].getStreet(), "Логинова");
        Assert.assertEquals(response.getResult()[0].getHouse(), "20/175");
        Assert.assertEquals(response.getResult()[0].getFlat(), "48");
    }

    @Ignore
    @Test
    public void updateAddress() {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        try{
            List<Address> addressList = entityManager.createQuery(
                            "select a from Address a " +
                                    "where a.objUnid in (select o from ObjectJPA o " +
                                    "where o.subUnid in (select s.subUnid from Subject s where s.subInn = '7832000076' and s.indActual = 1) " +
                                    "  and o.indActual = 1)" +
                                    "  and a.indActual = 1")
                    .getResultList();
            entityManager.getTransaction().begin();
            for (Address address: addressList) {
                if (address.getAddrDistrict() == null || address.getAddrDistrict().isEmpty()) {
                    Result<AddressStandardResponse[], String> response =
                            dadataClient.getAddressStandard(address.getAddrAddress());
                    String district = response.getResult()[0].getCityDistrict();
                    if (district != null && !district.isEmpty()) {
                        address.setAddrDistrict(district);
                        address.setFoundB("#52698");
                        entityManager.merge(address);
                    }
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

}
