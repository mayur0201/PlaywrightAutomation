package com.opencart.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;

    private Page page;

    Properties prop;

    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();


    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public Page initBrowser(Properties prop) {

        tlPlaywright.set(Playwright.create());
//        playwright = Playwright.create();
        String browserName = prop.getProperty("browser").trim();

        System.out.println("Browser Name is:" + browserName);

        switch (browserName.toLowerCase()) {
            case "chromium":

                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;

            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;

            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;

            case "chrome":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
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

    /**
     * take screenshot
     *
     */

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }


}
