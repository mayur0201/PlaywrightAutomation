package com.opencart.tests;

import com.microsoft.playwright.Page;
import com.opencart.Pages.HomePage;
import com.opencart.factory.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest {

    PlaywrightFactory pf ;
    Page page;
    HomePage hp;

    @BeforeTest
    public void setUp()
    {
        pf = new PlaywrightFactory();
        page = pf.initBrowser("chromium");
        hp = new HomePage(page);
    }

    @Test
    public void homepageTitleTest()
    {
        String actutalText = hp.getPageTitle();
        Assert.assertEquals(actutalText,"Your Store");
    }

    @Test
    public void homepageUrlTest()
    {
        String actutalURL = hp.getHomePageURL();
        Assert.assertEquals(actutalURL,"https://naveenautomationlabs.com/opencart/");
    }

    @Test(dataProvider = "getProductName")
    public void searchProduct(String productName)
    {
        String textHeader = hp.doSearch(productName);
        Assert.assertEquals(textHeader,"Search - "+productName);

    }

    @DataProvider
    public Object[][] getProductName()
    {
        return new Object[][]
                {
                        {"iMac"},
                        {"Macbook"},
                        {"Apple"}
                };
    }

    @AfterTest
    public void tearDown()
    {
        page.context().browser().close();
    }

}
