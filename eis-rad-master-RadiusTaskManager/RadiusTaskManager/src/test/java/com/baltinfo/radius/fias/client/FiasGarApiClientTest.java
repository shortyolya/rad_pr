package com.baltinfo.radius.fias.client;

import com.baltinfo.radius.fias.dto.FiasAddrobj;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FiasGarApiClientTest {

    private final static String ADDR_JSON_1 = "{" +
            "    \"aoguid\": \"0c5b2444-70a0-4932-980c-b4dc0d3f02b5\"," +
            "    \"aolevel\": \"1\"," +
            "    \"areacode\": \"0\"," +
            "    \"citycode\": \"0\"," +
            "    \"formalname\": \"Москва\"," +
            "    \"id\": 1729288," +
            "    \"objectid\": 1405113," +
            "    \"offname\": \"Москва\"," +
            "    \"parentguid\": null," +
            "    \"placecode\": \"0\"," +
            "    \"plancode\": \"0\"," +
            "    \"regioncode\": \"77\"," +
            "    \"shortname\": \"г\"," +
            "    \"streetcode\": \"0\"" +
            "}";

    @Mock
    CloseableHttpClient httpClient;

    @Mock
    CloseableHttpResponse httpResponse;

    @Mock
    HttpEntity httpEntity;

    @Captor
    ArgumentCaptor<HttpGet> httpGetArgumentCaptor;

    @Test
    public void testGetAddrobjByAoguid() throws Exception {
        when(httpClient.execute(httpGetArgumentCaptor.capture())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(ADDR_JSON_1.getBytes(StandardCharsets.UTF_8)));

        FiasGarApiClient apiClient = new FiasGarApiClient("http://localhost:8082/fias-gar", httpClient);

        FiasAddrobj addrobj = apiClient.getAddrobjByAoguid("0c5b2444-70a0-4932-980c-b4dc0d3f02b5");

        Assert.assertEquals(addrobj.getAoguid(), "0c5b2444-70a0-4932-980c-b4dc0d3f02b5");

        Assert.assertEquals(httpGetArgumentCaptor.getValue().getURI().toString(),
                "http://localhost:8082/fias-gar/addrobj/id/0c5b2444-70a0-4932-980c-b4dc0d3f02b5");
    }
}
