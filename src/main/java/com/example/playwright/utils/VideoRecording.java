package com.example.playwright.utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class VideoRecording {
    public static void main(String... args){
        PlaywrightUtilities utils = new PlaywrightUtilities();
        Playwright playwright = Playwright.create();
        Browser  chrome = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = chrome.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("video/")));
        Page page = context.newPage();
        page.navigate("https://uibank.uipath.com/");
        page.getByPlaceholder("Enter username").click();
        page.getByPlaceholder("Enter username").fill("RABIT");
        page.getByPlaceholder("Enter password").click();
        page.getByPlaceholder("Enter password").fill("Test@123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Products")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Loans")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply For A Loan")).click();
        context.close();
        page.close();
        playwright.close();

    }
}
