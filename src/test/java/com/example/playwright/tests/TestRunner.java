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
        //tags = "@UAT or @OFF"
        //tags = "@UAT and @OFF"
        //tags = "@UAT or @SIT"
        //tags = "@UAT and @SIT"
        //tags="@UAT and @RABIT"
        //tags = "@Web2.0Parametrization"
        //tags="@Web2.0"
        //tags="@Siebel"
        //tags="@Tabledata"
        //tags="@BrowserStack"
        //tags = "@ExcelDriver"
        //tags="@SAM"
        //tags="@Playitwright"
        //tags="@TeamNibbles"
        //tags="@ON and @FTM",
        //tags="@JSON"
        //tags="@Playitwright"
        tags="@Playwright"
        //tags="@Certificate"
        //tags = "@JavascriptExecutor"
        //tags="@SeleniumManager"
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
