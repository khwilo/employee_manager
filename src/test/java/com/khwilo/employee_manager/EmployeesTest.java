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
public class EmployeesTest {
    @Test
    public void givenEmployeeExists_whenFetched_then200IsCreated() throws ClientProtocolException, IOException {
        String employeeRegistration = SampleData.createEmployee(
                "jayza", "zebraa", "jay@tidal.com", "rda2#@as012"
        );

        APIRequest apiRequest = new APIRequest();
        CloseableHttpResponse getAllEmployeesResponse = apiRequest.fetchAllEmployees(
                "http://localhost:5000/api/v1/auth/register", employeeRegistration,
                "http://localhost:5000/api/v1/employees"
        );
        HttpEntity httpEntity = getAllEmployeesResponse.getEntity();
        JsonArray result = (JsonArray) new JsonParser().parse(EntityUtils.toString(httpEntity));

        assertEquals(getAllEmployeesResponse.getStatusLine().getStatusCode(), 200);
        assertEquals(((JsonObject) result.get(0)).get("firstName").getAsString(), "Frank");
        assertEquals(((JsonObject) result.get(1)).get("emailAddress").getAsString(), "jay@tidal.com");
    }

    @Test
    public void givenEmployeeRolesDoesNotExist_whenFetched_then404IsCreated() throws
            ClientProtocolException, IOException {
        String employeeRegistration = SampleData.createEmployee(
                "jayza", "zebraa", "jay@tidal.com", "rda2#@as012");

        APIRequest apiRequest = new APIRequest();
        CloseableHttpResponse getAllEmployeesWithRolesResponse = apiRequest.fetchAllEmployeesWithoutRoles(
                "http://localhost:5000/api/v1/auth/register", employeeRegistration,
                "http://localhost:5000/api/v1/employees/roles"
        );
        HttpEntity httpEntity = getAllEmployeesWithRolesResponse.getEntity();
        JsonObject result = (JsonObject) new JsonParser().parse(EntityUtils.toString(httpEntity));

        assertEquals(getAllEmployeesWithRolesResponse.getStatusLine().getStatusCode(), 404);
        assertEquals(result.get("message").getAsString(), "Employee Role is not set!");
    }

    @Test
    public void givenEmployeeRolesDoesExist_whenFetched_then200IsCreated() throws ClientProtocolException, IOException {
        String rolePayload = SampleData.rolePayload("frank@gmail.com","Line Manager");

        APIRequest apiRequest = new APIRequest();
        CloseableHttpResponse getAllEmployeesWithRolesResponse = apiRequest.fetchAllEmployeesWithRoles(
                "http://localhost:5000/api/v1/role/create/1", rolePayload,
                "http://localhost:5000/api/v1/employees/roles"
        );
        HttpEntity httpEntity = getAllEmployeesWithRolesResponse.getEntity();
        JsonArray result = (JsonArray) new JsonParser().parse(EntityUtils.toString(httpEntity));

        assertEquals(getAllEmployeesWithRolesResponse.getStatusLine().getStatusCode(), 200);
        assertEquals(((JsonObject) result.get(0)).get("role").getAsString(), "Line Manager");
    }
}
