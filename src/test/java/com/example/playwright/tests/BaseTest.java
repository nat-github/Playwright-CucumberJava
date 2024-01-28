package com.example.playwright.tests;

import com.example.playwright.utils.APIUtilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected APIUtilities manager;

    @BeforeClass
    public void setup() {
        manager = new APIUtilities();
        manager.createPlaywright();
        String baseUrl = "https://jsonplaceholder.typicode.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        manager.setApiRequestContext(baseUrl, headers);
    }
    @AfterClass
    public void tearDown() {
        manager.disposeAPIRequestContext();
        manager.closePlaywright();
    }
}
