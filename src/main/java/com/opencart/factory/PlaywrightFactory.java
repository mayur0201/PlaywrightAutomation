package com.opencart.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;

    private Page page;

    Properties prop;


    public Page initBrowser(Properties prop) {
        playwright = Playwright.create();
        String browserName = prop.getProperty("browser").trim();

        System.out.println("Browser Name is:" + browserName);

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
        page.navigate(prop.getProperty("url").trim());
        return page;
    }

    public Properties init_prop() throws FileNotFoundException {
        try {
            FileInputStream fp = new FileInputStream("src/test/resources/config/config.properties");


            prop = new Properties();
            prop.load(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }


}
