package com.khwilo.employee_manager;

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
public class AuthenticationTests {
    @Test
    public void givenEmployeeDoesNotExist_whenEmployeeAccIsCreated_then201IsReceived()
            throws ClientProtocolException, IOException {
        String employeeRegistration = SampleData.createEmployee(
                "jayza", "zebraa", "jay@tidal.com", "rda2#@as012"
        );

        APIRequest apiRequest = new APIRequest();
        CloseableHttpResponse signUpResponse = apiRequest.signUp(
                "http://localhost:5000/api/v1/auth/register", employeeRegistration
        );
        HttpEntity signUpEntity = signUpResponse.getEntity();
        JsonObject result = (JsonObject) new JsonParser().parse(EntityUtils.toString(signUpEntity));

        assertEquals(signUpResponse.getStatusLine().getStatusCode(), 201);
        assertEquals(result.get("status").getAsInt(), 201);
        assertEquals(result.get("message").getAsString(), "Employee account creation is successful!");
    }
}
