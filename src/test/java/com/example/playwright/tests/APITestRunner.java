package com.example.playwright.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com/example/playwright/tests"},
        plugin = { "pretty","html:target/MyReports/report.html" },
        monochrome = true,
        tags="@PlaywrightAPI"
)
@Test
public class APITestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
