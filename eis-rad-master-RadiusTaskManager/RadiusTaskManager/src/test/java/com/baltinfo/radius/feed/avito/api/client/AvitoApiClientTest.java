package com.baltinfo.radius.feed.avito.api.client;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportItem;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportResponse;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportsList;
import com.baltinfo.radius.feed.avito.api.model.AvitoUser;
import com.baltinfo.radius.feed.avito.api.model.PostStatisticResponse;
import com.baltinfo.radius.feed.avito.api.model.ReportFromList;
import com.baltinfo.radius.feed.avito.api.model.ViewStatisticRequestBody;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.ListUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class AvitoApiClientTest {

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }


    @Test
    @Ignore
    public void shouldTrue_whenGetLastReportFromList() {
        AvitoApiClient client = new AvitoApiClient("https://api.avito.ru",
                "zBygctFVnWPL3XiGtKiR",
                "P7cy5LHUKfLwCcG0EeC2PVdBFEeafbaKSZDDVgoA",
                "2023-01-01");

        Result<String, String> authResult = client.authorization();
        assertTrue(authResult.isSuccess());
        assertFalse(authResult.getResult().isEmpty());

        Result<AvitoUser, String> userResult = client.getUser(authResult.getResult());
        assertTrue(userResult.isSuccess());
        assertNotNull(userResult.getResult().getId());

        System.out.println(authResult.getResult());
        System.out.println(userResult.getResult().getId());

        Result<List<ReportFromList>, String> reportsListResult = client.getReportsList(authResult.getResult());
        assertTrue(reportsListResult.isSuccess());
        assertNotNull(reportsListResult.getResult());
        Optional<ReportFromList> actualReport = reportsListResult.getResult().stream()
                .filter(report -> report.getFinishedAt() != null)
                .findFirst();
        assertTrue(actualReport.isPresent());

        Result<List<AvitoReportItem>, String> report = client.getReportById(authResult.getResult(), actualReport.get().getId());
        assertTrue(report.isSuccess());
        assertNotNull(report.getResult());
    }

    @Ignore
    @Test
    public void shouldTrue_whenGetViewStatistics() throws JsonProcessingException {
        AvitoApiClient client = new AvitoApiClient("https://api.avito.ru",
                "zBygctFVnWPL3XiGtKiR",
                "P7cy5LHUKfLwCcG0EeC2PVdBFEeafbaKSZDDVgoA",
                "2023-01-01");

        Result<String, String> authResult = client.authorization();
        assertTrue(authResult.isSuccess());
        assertFalse(authResult.getResult().isEmpty());

        Result<AvitoUser, String> userResult = client.getUser(authResult.getResult());
        assertTrue(userResult.isSuccess());
        assertNotNull(userResult.getResult().getId());

        System.out.println(authResult.getResult());
        System.out.println(userResult.getResult().getId());

        Result<List<ReportFromList>, String> reportsListResult =
                client.getReportsList(authResult.getResult());
        assertTrue(reportsListResult.isSuccess());
        assertNotNull(reportsListResult.getResult());

        Optional<ReportFromList> actualReport = reportsListResult.getResult().stream()
                .filter(report -> report.getFinishedAt() != null)
                .findFirst();


        Result<List<AvitoReportItem>, String> reportByIdItemsResult = client
                .getReportById(authResult.getResult(), actualReport.get().getId());
        assertTrue(reportByIdItemsResult.isSuccess());
        assertNotNull(reportByIdItemsResult.getResult());

        List<Long> offerIdList = reportByIdItemsResult.getResult().stream()
                .filter(ad -> ad.getAvitoId() != null && ad.getAdId().equals("21809"))
                .map(ad -> ad.getAvitoId())
                .collect(Collectors.toList());

        List<List<Long>> offerIdLists = ListUtils.partition(offerIdList, 200);
        for (List<Long> list : offerIdLists) {
            ViewStatisticRequestBody bodyObject = new ViewStatisticRequestBody(list);
            String body = new ObjectMapper().writeValueAsString(bodyObject);

            Result<PostStatisticResponse, String> statistics =
                    client.getViewsStatistics(authResult.getResult(), userResult.getResult().getId(), body);
            assertTrue(statistics.isSuccess());
            assertNotNull(statistics.getResult());
        }

    }
}
