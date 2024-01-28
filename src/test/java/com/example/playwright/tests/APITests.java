package com.example.playwright.tests;

import com.example.playwright.api.Postdata;
import com.example.playwright.utils.Reportingutilities;
import com.google.common.io.Files;
import com.microsoft.playwright.APIResponse;

import java.io.File;
import java.io.IOException;

import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

public class APITests extends BaseTest {
    @Test
    public void testGetAPI() throws IOException {
        APIResponse response = manager.getRequest("/todos/1");
        String responseAsString = response.text();
        byte[] responseAsStringByte = responseAsString.getBytes();
        File responsefile = new File("src/main/resources/getResponse.json");
        Files.write(responseAsStringByte, responsefile);
        //JSONObject jsonObject = new JSONObject(response.text());
        //JSONObject dataObject = jsonObject.getJSONObject("data");
        System.out.println("Response Status "+ response.status());
        Reportingutilities.reportUtilLog("Get Response",responsefile);
    }
    @Test
    public void testPostAPI() throws IOException {
        Postdata data = new Postdata();
        data.setTitle("foo");
        data.setBody("bar");
        data.setUserId(2);
        APIResponse response = manager.postRequest("/posts", RequestOptions.create().setData(data));
        String responseAsString = response.text();
        byte[] responseAsStringByte = responseAsString.getBytes();
        File responsefile = new File("src/main/resources/postResponse.json");
        Files.write(responseAsStringByte, responsefile);
        System.out.println("Response Status "+ response.status());
        Reportingutilities.reportUtilLog("Post Response",responsefile);
    }
}
