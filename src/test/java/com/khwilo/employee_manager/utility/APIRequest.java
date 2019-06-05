package com.khwilo.employee_manager.utility;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class APIRequest {
    CloseableHttpClient client;

    public APIRequest() {
        this.client = HttpClients.createDefault();
    }

    public CloseableHttpResponse signUp(String signUpUrl, String employeeRegistration)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(employeeRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");

        return this.client.execute(signUpPost);
    }

    public CloseableHttpResponse login(
            String signUpUrl, String employeeRegistration, String loginUrl, String employeeLogin)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(employeeRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");
        this.client.execute(signUpPost);

        HttpPost loginPost = new HttpPost(loginUrl);
        StringEntity loginEntity = new StringEntity(employeeLogin);
        loginPost.setEntity(loginEntity);
        loginPost.setHeader("Accept", "application/json");
        loginPost.setHeader("Content-type", "application/json");

        return this.client.execute(loginPost);
    }

    public CloseableHttpResponse createRole(
            String signUpUrl, String employeeRegistration, String createRoleUrl, String rolePayload)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(employeeRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");
        this.client.execute(signUpPost);

        HttpPost createRolePost = new HttpPost(createRoleUrl);
        StringEntity roleEntity = new StringEntity(rolePayload);
        createRolePost.setEntity(roleEntity);
        createRolePost.setHeader("Accept", "application/json");
        createRolePost.setHeader("Content-type", "application/json");

        return this.client.execute(createRolePost);
    }

    public CloseableHttpResponse fetchAllEmployees(
            String signUpUrl, String employeeRegistration, String fetchEmployeesUrl)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(employeeRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");
        this.client.execute(signUpPost);

        HttpGet getAllEmployees = new HttpGet(fetchEmployeesUrl);
        getAllEmployees.setHeader("Content-type", "application/json");

        return this.client.execute(getAllEmployees);
    }

    public CloseableHttpResponse fetchAllEmployeesWithoutRoles(
            String signUpUrl, String employeeRegistration, String fetchEmployeesWithRolesUrl)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(employeeRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");
        this.client.execute(signUpPost);

        HttpGet getAllEmployeesWithRoles = new HttpGet(fetchEmployeesWithRolesUrl);
        getAllEmployeesWithRoles.setHeader("Content-type", "application/json");

        return this.client.execute(getAllEmployeesWithRoles);
    }

    public CloseableHttpResponse fetchAllEmployeesWithRoles(
            String createRoleUrl, String rolePayload, String fetchEmployeesWithRolesUrl)
            throws ClientProtocolException, IOException {
        HttpPost createRolePost = new HttpPost(createRoleUrl);
        StringEntity roleEntity = new StringEntity(rolePayload);
        createRolePost.setEntity(roleEntity);
        createRolePost.setHeader("Accept", "application/json");
        createRolePost.setHeader("Content-type", "application/json");
        this.client.execute(createRolePost);

        HttpGet getAllEmployeesWithRoles = new HttpGet(fetchEmployeesWithRolesUrl);
        getAllEmployeesWithRoles.setHeader("Content-type", "application/json");

        return this.client.execute(getAllEmployeesWithRoles);
    }

    public CloseableHttpResponse assignItems(
            String signUpUrl, String employeeRegistration, String assignItemsUrl, String itemsPayload)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(employeeRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");
        this.client.execute(signUpPost);

        HttpPost assignItemsPost = new HttpPost(assignItemsUrl);
        StringEntity itemsEntity = new StringEntity(itemsPayload);
        assignItemsPost.setEntity(itemsEntity);
        assignItemsPost.setHeader("Accept", "application/json");
        assignItemsPost.setHeader("Content-type", "application/json");

        return this.client.execute(assignItemsPost);
    }

    public CloseableHttpResponse fetchAllItems(String fetchItemsUrl) throws ClientProtocolException, IOException {
        HttpGet fetchItems = new HttpGet(fetchItemsUrl);
        fetchItems.setHeader("Content-type", "application/json");

        return this.client.execute(fetchItems);
    }
}
