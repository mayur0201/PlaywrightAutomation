package com.opencart.Pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    private String emailID = "input#input-email";
    private String password = "input#input-password";
    private String loginButton = "//*[@value='Login']";

    private String forgotPasswrodLink = "a:text('Forgotten Password')";
    private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";

    public LoginPage(Page page)
    {
        this.page = page;
    }

     public String getLoginPageTitle()
     {
         return page.title();
     }

     public boolean isFrgtPswdLinkExist()
     {
         return page.isVisible(forgotPasswrodLink);
     }

     public boolean doLogin(String appusername , String appPassword)
     {
         System.out.println("App Creds are" + appusername + ":" + appPassword);
         page.fill(emailID,appusername);
         page.fill(password,appPassword);

         page.click(loginButton);
         if(page.locator(logoutLink).isVisible()) {
             System.out.println("user is logged in successfully....");
             return true;
         }else {
             System.out.println("user is not logged in successfully....");
             return false;
         }
     }


}
