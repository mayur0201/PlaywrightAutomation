package com.opencart.base;

import com.microsoft.playwright.Page;
import com.opencart.Pages.HomePage;
import com.opencart.factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {

    PlaywrightFactory pf;
    Page page;
   protected HomePage hp;

   protected Properties prop;

    @BeforeTest
    public void setUp() throws FileNotFoundException {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        page = pf.initBrowser(prop);
        hp = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {

        page.context().browser().close();
    }
}
