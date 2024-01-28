package com.example.playwright.tests;

import com.example.playwright.api.Postdata;
import com.example.playwright.pageLocators.UIBankLocators;
import com.example.playwright.utils.PlaywrightUtilities;
import com.example.playwright.utils.Reportingutilities;
import com.google.common.io.Files;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class stepDefinitions extends BaseTest {
    Playwright playwright = Playwright.create();
    PlaywrightUtilities utils = new PlaywrightUtilities();
    @Given("Browser driver : {string}")
    public void browserDriver(String browserType){
        utils.setDriver(browserType);
    }
    @Given("User navigate to {string} using playwright")
    public void playWrightOpen(String URL) {
        utils.navigate(URL);
    }
    @When("User enter the username and password for the application using playwright")
    public void login() throws InterruptedException {
        utils.type(UIBankLocators._username,"RABIT");
        utils.type(UIBankLocators._password,"Test@123");
        utils.click(UIBankLocators._signIN);
    }
    @Then("User should be able to apply for loan")
    public void applyLoan() throws IOException {
        utils.takeScreenshot("loan1.png");
        utils.waitForLoadState();
        utils.waitForSelector(UIBankLocators._selectproducts,10);
        utils.click(UIBankLocators._selectproducts);
        utils.takeScreenshot("loan2.png");
        utils.click(UIBankLocators._linkloans);
        utils.takeScreenshot("loan3.png");
        utils.waitForSelector(UIBankLocators._btnapply,10);
        utils.click(UIBankLocators._btnapply);
        utils.type(UIBankLocators._txtEmail,"");
        utils.selectOptionByValue(UIBankLocators._selectTerm,"3");
        utils.type(UIBankLocators._txtAmount,"1000");
        utils.type(UIBankLocators._txtIncome,"2000");
        utils.type(UIBankLocators._txtAge,"30");
        utils.takeScreenshot("loan5.png");
        utils.click(UIBankLocators._btnsubmitLoan);
        utils.click(UIBankLocators._linkLogout);
        utils.takeScreenshot("loan6.png");
        utils.close();

    }
    @Given("User performs get request with the base url {string} with endpoint {string}")
    public void testGetAPI(String baseURL,String endpoint) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        APIRequestContext apiRequestContext= playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(baseURL).setExtraHTTPHeaders(headers));
        APIResponse response = apiRequestContext.get(endpoint);
        String responseAsString = response.text();
        byte[] responseAsStringByte = responseAsString.getBytes();
        File responsefile = new File("src/main/resources/getResponse.json");
        Files.write(responseAsStringByte, responsefile);
        //JSONObject jsonObject = new JSONObject(response.text());
        //JSONObject dataObject = jsonObject.getJSONObject("data");
        System.out.println("Response Status "+ response.status());
        Reportingutilities.reportUtilLog("Get Response",responsefile);
    }
    @Given("User performs post request with the base url {string} with endpoint {string}")
    public void testPostAPI(String baseURL,String endpoint) throws IOException {
            Postdata data = new Postdata();
            data.setTitle("foo");
            data.setBody("bar");
            data.setUserId(2);
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        APIRequestContext apiRequestContext= playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(baseURL).setExtraHTTPHeaders(headers));
            APIResponse response = apiRequestContext.post(endpoint, RequestOptions.create().setData(data));
            String responseAsString = response.text();
            byte[] responseAsStringByte = responseAsString.getBytes();
            File responsefile = new File("src/main/resources/postResponse.json");
            Files.write(responseAsStringByte, responsefile);
            System.out.println("Response Status "+ response.status());
            Reportingutilities.reportUtilLog("Post Response",responsefile);
    }
    @Then("validate the response")
    public void  responseValidation(){
        System.out.println("Write your tests here to validate");
    }

}
