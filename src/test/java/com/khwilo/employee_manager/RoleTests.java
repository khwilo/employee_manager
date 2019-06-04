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
public class RoleTests {
    @Test
    public void givenEmployeeExists_whenRoleIsAssigned_then201IsCreated() throws ClientProtocolException, IOException {
        String employeeRegistration = SampleData.createEmployee(
                "jayza", "zebraa", "jay@tidal.com", "rda2#@as012"
        );
        String rolePayload = SampleData.rolePayload("Software Engineer");

        APIRequest apiRequest = new APIRequest();
        CloseableHttpResponse response = apiRequest.createRole(
                "http://localhost:5000/api/v1/auth/register", employeeRegistration,
                "http://localhost:5000/api/v1/role/create/2", rolePayload
        );
        HttpEntity httpEntity = response.getEntity();
        JsonObject result = (JsonObject) new JsonParser().parse(EntityUtils.toString(httpEntity));

        assertEquals(response.getStatusLine().getStatusCode(), 201);
        assertEquals(result.get("status").getAsInt(), 201);
        assertEquals(result.get("message").getAsString(), "Role successfully created!");
    }
}
