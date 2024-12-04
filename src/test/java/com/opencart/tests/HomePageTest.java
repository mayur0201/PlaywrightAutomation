package com.opencart.tests;


import com.opencart.base.BaseTest;

import com.opencart.constants.ApplicationConstants;
import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {


    @Test
    public void homepageTitleTest() {
        String actutalText = hp.getPageTitle();
        Assert.assertEquals(actutalText, ApplicationConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void homepageUrlTest() {
        String actutalURL = hp.getHomePageURL();
        Assert.assertEquals(actutalURL, prop.getProperty("url"));
    }

    @Test(dataProvider = "getProductName")
    public void searchProduct(String productName) {
        String textHeader = hp.doSearch(productName);
        Assert.assertEquals(textHeader, "Search - " + productName);

    }

    @DataProvider
    public Object[][] getProductName() {
        return new Object[][]
                {
                        {"iMac"},
                        {"Macbook"},
                        {"Apple"}
                };
    }


}
