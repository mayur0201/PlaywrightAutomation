package com.opencart.tests;

import com.opencart.base.BaseTest;
import com.opencart.constants.ApplicationConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void loginPageNavigationTest()
    {
        lp = hp.navigateToLoginPage();
        String actloginPageTitle = lp.getLoginPageTitle();

        System.out.println("page act title: " + actloginPageTitle);
        Assert.assertEquals(actloginPageTitle, ApplicationConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void forgotPwdLinkExistTest() {
        Assert.assertTrue(lp.isFrgtPswdLinkExist());
    }

    @Test(priority = 3)
    public void appLoginTest() {
        Assert.assertTrue(lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
    }

}
