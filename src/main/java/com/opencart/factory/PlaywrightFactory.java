package com.opencart.factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;


    public Page initBrowser(String browserName) {
        playwright = Playwright.create();

        System.out.println("Browser Name is:"+ browserName);

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate("https://naveenautomationlabs.com/opencart/");
        return page;
    }


}
