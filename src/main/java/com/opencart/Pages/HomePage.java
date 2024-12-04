package com.opencart.Pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;

    private String search = "input[name='search']";
    private String searchButton = "div#search button";

    private String pageHeader = "div#content h1";

    public HomePage(Page page)
    {
        this.page = page;

    }

    public String getPageTitle()
    {
       String title = page.title();
       System.out.println("Title of page:" + title);

       return title;
    }

    public String getHomePageURL() {

        String url = page.url();
        System.out.println("Url of page:" + url);
        return url;
    }

    public String doSearch(String productName)
    {
        page.fill(search,productName);
        page.click(searchButton);
        String header = page.textContent(pageHeader);
        System.out.println("Search Header:" + header);
        return header;
    }


}
