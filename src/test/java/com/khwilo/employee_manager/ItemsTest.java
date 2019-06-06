package com.khwilo.employee_manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.khwilo.employee_manager.utility.APIRequest;
import com.khwilo.employee_manager.utility.SampleData;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemsTest {
    @Test
    public void givenEmployeeExists_whenItemIsAssigned_then201IsCreated() throws ClientProtocolException, IOException {
        String employeeRegistration = SampleData.createEmployee(
                "jayza", "zebraa", "jay@tidal.com", "rda2#@as012"
        );
        String item = SampleData.itemPayload(
                "frank@gmail.com", "Laptop", "jay@saleforce.com", 24
        );

        APIRequest apiRequest = new APIRequest();
        CloseableHttpResponse assignItemResponse = apiRequest.assignItems(
                "http://localhost:5000/api/v1/auth/register", employeeRegistration,
                "http://localhost:5000/api/v1/item/assign/2", item
        );
        HttpEntity httpEntity = assignItemResponse.getEntity();
        JsonObject result = (JsonObject) new JsonParser().parse(EntityUtils.toString(httpEntity));

        assertEquals(assignItemResponse.getStatusLine().getStatusCode(), 201);
        assertEquals(result.get("status").getAsInt(), 201);
        assertEquals(result.get("message").getAsString(), "Laptop has been successfully been assigned");
    }

    @Test
    public void givenItemsDontExist_whenFetched_then404IsCreated() throws ClientProtocolException, IOException {
        APIRequest apiRequest = new APIRequest();

        CloseableHttpResponse fetchItemsResponse = apiRequest.fetchAllItems(
                "http://localhost:5000/api/v1/item/all"
        );

        HttpEntity httpEntity = fetchItemsResponse.getEntity();
        JsonObject result = (JsonObject) new JsonParser().parse(EntityUtils.toString(httpEntity));

        assertEquals(fetchItemsResponse.getStatusLine().getStatusCode(), 404);
        assertEquals(result.get("status").getAsInt(), 404);
        assertEquals(result.get("message").getAsString(), "No item has been added yet!");
    }
}
