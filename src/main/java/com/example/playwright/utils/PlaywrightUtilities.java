package com.example.playwright.utils;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import com.example.playwright.enums.Browsers;
import com.google.common.io.Files;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Consumer;
import java.util.function.Function;

public class PlaywrightUtilities {
    Playwright playwright = Playwright.create();
    Page page;
    Browser browser;
    Video video;
    BrowserContext context;

    public void setDriver(String browserType){
        if(browserType.equalsIgnoreCase(Browsers.CHROME.name())) {
            BrowserType chrome = playwright.chromium();
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            browser = chrome.launch(options);
            page = browser.newPage();
            page.setDefaultTimeout(10000); // set the timeout to 10 seconds
        }
        else if(browserType.equalsIgnoreCase(Browsers.FIREFOX.name())){
            BrowserType firefox = playwright.firefox();
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            browser = firefox.launch(options);
            page = browser.newPage();
            page.setDefaultTimeout(10000);
        }
        else if(browserType.equalsIgnoreCase(Browsers.Moon.name())){
            BrowserType moon = playwright.chromium();
            String moonUrl = "";
            Browser browser = moon.connect(moonUrl);
            BrowserContext context = browser.newContext();
            page = context.newPage();
        }
    }
    public void navigate(String url) {
        page.navigate(url);
    }

    public Locator findElement(String locator) {
        return page.locator(locator);
    }

    public void click(String locator) {
        findElement(locator).click();
        ReportPortal.emitLog("Element Type: "+locator , LogLevel.INFO.name(), new Date());
    }

    public void type(String locator, String text) {
        findElement(locator).fill(text);
        ReportPortal.emitLog("Element Type: "+locator +"Value: "+text , LogLevel.INFO.name(), new Date());
    }

    public String getText(String locator) {
        return findElement(locator).innerText();
    }

    public void waitForLoadState() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void close() {
        //context.close();
        page.close();
        //browser.close();
        playwright.close();
    }

    public CompletableFuture<Void> async(Consumer<CompletableFuture<Void>> action) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        action.accept(future);
        return future;
    }

    public CompletableFuture<Locator> asyncFindElement(Function<Page, CompletableFuture<Locator>> action) {
        return action.apply(page);
    }

    public Locator findElementByRole(AriaRole role) {
        return page.getByRole(role);
    }

    public Locator findElementByText(String text) {
        return page.getByText(text);
    }

    public Locator findElementByPlaceholder(String placeholder) {
        return page.getByPlaceholder(placeholder);
    }

    public Locator findElementByTestId(String testId) {
        return page.locator("[data-testid='" + testId + "']");
    }

    public void clickByRole(AriaRole role) {
        findElementByRole(role).click();
    }

    public void clickByText(String text) {
        findElementByText(text).click();
    }

    public void clickByPlaceholder(String placeholder) {
        findElementByPlaceholder(placeholder).click();
    }

    public void clickByTestId(String testId) {
        findElementByTestId(testId).click();
    }


    public void typeByRole(AriaRole role, String text) {
        findElementByRole(role).fill(text);
    }

    public void typeByText(String text, String textToType) {
        findElementByText(text).fill(textToType);
    }

    public void typeByPlaceholder(String placeholder, String text) {
        findElementByPlaceholder(placeholder).fill(text);
    }

    public void typeByTestId(String testId, String text) {
        findElementByTestId(testId).fill(text);
    }

    public List<Locator> findElementsByAccessibilityLabel(String label) {
        return page.locator("[aria-label='" + label + "']").all();
    }

    public List<Locator> findElementsByRole(String role) {
        return page.locator("[role='" + role + "']").all();
    }

    public List<Locator> findElementsByText(String text) {
        return page.getByText(text).all();
    }

    public List<Locator> findElementsByPlaceholder(String placeholder) {
        return page.getByPlaceholder(placeholder).all();
    }

    public List<Locator> findElementsByTestId(String testId) {
        return page.locator("[data-testid='" + testId + "']").all();
    }

    public void clickAllByAccessibilityLabel(String label) {
        List<Locator> elements = findElementsByAccessibilityLabel(label);
        for (Locator element : elements) {
            element.click();
        }
    }

    public void clickAllByRole(String role) {
        List<Locator> elements = findElementsByRole(role);
        for (Locator element : elements) {
            element.click();
        }
    }

    public void clickAllByText(String text) {
        List<Locator> elements = findElementsByText(text);
        for (Locator element : elements) {
            element.click();
        }
    }

    public void clickAllByPlaceholder(String placeholder) {
        List<Locator> elements = findElementsByPlaceholder(placeholder);
        for (Locator element : elements) {
            element.click();
        }
    }

    public void clickAllByTestId(String testId) {
        List<Locator> elements = findElementsByTestId(testId);
        for (Locator element : elements) {
            element.click();
        }
    }

    public void typeAllByAccessibilityLabel(String label, String text) {
        List<Locator> elements = findElementsByAccessibilityLabel(label);
        for (Locator element : elements) {
            element.fill(text);
        }
    }

    public void typeAllByRole(String role, String text) {
        List<Locator> elements = findElementsByRole(role);
        for (Locator element : elements) {
            element.fill(text);
        }
    }

    public void typeAllByText(String text, String textToType) {
        List<Locator> elements = findElementsByText(text);
        for (Locator element : elements) {
            element.fill(textToType);
        }
    }

    public void typeAllByPlaceholder(String placeholder, String text) {
        List<Locator> elements = findElementsByPlaceholder(placeholder);
        for (Locator element : elements) {
            element.fill(text);
        }
    }
    public void waitForSelector(String selector) {
        page.waitForSelector(selector);
    }

    public void waitForSelector(String selector, int timeout) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeout));
    }

    public void waitForXPath(String xpath) {
        page.waitForSelector(xpath);
    }

    public void waitForXPath(String xpath, int timeout) {
        page.waitForSelector(xpath, new Page.WaitForSelectorOptions().setTimeout(timeout));
    }

    public void waitForLoadState(LoadState state) {
        page.waitForLoadState(state);
    }

    public void waitForLoadState(LoadState state, int timeout) {
        page.waitForLoadState(state, new Page.WaitForLoadStateOptions().setTimeout(timeout));
    }
    public void waitForTimeout(int milliseconds) {
        page.waitForTimeout(milliseconds);
    }
    public void selectOptionByValue(String selector, String value) {
        page.selectOption(selector, new SelectOption[]{new SelectOption().setValue(value)});
    }

    public void selectOptionByText(String selector, String text) {
        page.selectOption(selector, new SelectOption[]{new SelectOption().setLabel(text)});
    }

    public void selectOptionByIndex(String selector, int index) {
        page.selectOption(selector, new SelectOption[]{new SelectOption().setIndex(index)});
    }

    public void selectMultipleOptionsByValues(String selector, String[] values) {
        SelectOption[] options = new SelectOption[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = new SelectOption().setValue(values[i]);
        }
        page.selectOption(selector, options);
    }

    public void selectMultipleOptionsByTexts(String selector, String[] texts) {
        SelectOption[] options = new SelectOption[texts.length];
        for (int i = 0; i < texts.length; i++) {
            options[i] = new SelectOption().setLabel(texts[i]);
        }
        page.selectOption(selector, options);
    }

    public void selectMultipleOptionsByIndices(String selector, int[] indices) {
        SelectOption[] options = new SelectOption[indices.length];
        for (int i = 0; i < indices.length; i++) {
            options[i] = new SelectOption().setIndex(indices[i]);
        }
        page.selectOption(selector, options);
    }
    public void takeScreenshot(String fileName) throws IOException {
        byte[] screenshot = page.screenshot
                (new Page.ScreenshotOptions().setPath(Paths.get(fileName)).setFullPage(true));
        File screenShotFile = new File(fileName);
        try ( FileOutputStream outputStream = new FileOutputStream(screenShotFile); ) {
            outputStream.write(screenshot);
        }
        ReportPortal.emitLog("Taking screenshot", LogLevel.INFO.name(), new Date(),screenShotFile);
    }
    public void takeElementScreenshot(String element,String fileName) {
        page.locator(element).screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
     public void videoRecording(){
          context = browser.newContext(new Browser.NewContextOptions()
                 .setRecordVideoDir(Paths.get("videos/")));
     }

     public void popup(String popupText){
         Page popup = page.waitForPopup(() -> {
             page.getByText(popupText).click();
         });
         popup.waitForLoadState();
     }

     public void allpopups(){
         page.onPopup(popup -> {
             popup.waitForLoadState();
             System.out.println(popup.title());
         });
     }

     public void goToPage(String pageName){
         Page newPage = context.waitForPage(() -> {
             page.getByText(pageName).click(); // Opens a new tab
         });
         newPage.waitForLoadState();
     }

     public void getAllPages(){
         context.onPage(page -> {
             page.waitForLoadState();
             System.out.println(page.title());
         });
     }
     public void closeBrowsercontext(){
        context.close();
        System.out.println(page.video().path());
     }

}
