package com.baltinfo.radius.feed.jcat.api;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.feed.jcat.api.client.JCatApiClient;
import com.baltinfo.radius.feed.jcat.api.model.ReportUrl;
import com.baltinfo.radius.utils.Result;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
public class JCatApiClientTest {

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    @Test
    @Ignore
    public void getActiveReports() throws Exception {
        JCatApiClient jCatApiClient = new JCatApiClient("https://api.jcat.ru/v1/", "ae77200f-37a4-11ea-bf6e-ac1f6bf61c28");
        Result<List<ReportUrl>, String> result = jCatApiClient.getActiveReports();
        assertTrue(result.isSuccess());
        List<ReportUrl> reports = result.getResult();
        Result<String, String> xml = jCatApiClient.getReportXml(reports.get(0).getReportUrl());
        assertTrue(xml.isSuccess());
    }

}
