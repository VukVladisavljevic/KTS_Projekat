package com.kits.project.e2e.tickets_test;

import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class TicketBuyingTest {
    private WebDriver browser;
    MainTicketsPage mainTicketsPage;
    ShowOwnedTicketsPage ownedTicketsPage;
    LoginPage loginPage;
    HomePage homePage;


    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        browser = new ChromeDriver();
    //    browser.manage().window().maximize();
        browser.get("http://localhost:4200/login");
        loginPage = PageFactory.initElements(browser, LoginPage.class);
        homePage = PageFactory.initElements(browser, HomePage.class);
        mainTicketsPage = PageFactory.initElements(browser, MainTicketsPage.class);
        ownedTicketsPage = PageFactory.initElements(browser, ShowOwnedTicketsPage.class);
    }

    @Test
    public void testTicketsPage() {
        loginPage.ensureIsDisplayed();
        loginPage.ensurePasswordIsDisplayed();
        loginPage.ensureLoginButtonIsDisplayed();
        loginPage.ensureIsClickable();
        loginPage.getLoginbutton().click();
        loginPage.loginAs("aa", "aa");

        homePage.ticketsLinkIsDisplayed();
        homePage.ticketLinkIsClickable();
        WebElement tickets = homePage.getTicketsLink();
//        (new WebDriverWait(browser, 10))
//                .until(ExpectedConditions.elementToBeClickable(tickets));
//
//        try {
//            Thread.sleep(2*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        homePage.getTicketsLink().click();

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainTicketsPage.ensureIsListDisplayed();
        mainTicketsPage.ensureIsListingClickable();
        mainTicketsPage.getListOwnedButton().click();

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int initialTicketsNumber = ownedTicketsPage.numberOfTickets(browser);
        System.out.println(initialTicketsNumber);

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ownedTicketsPage.ensureIsCloseButtonDisplayed();
        ownedTicketsPage.getCloseButton().click();

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainTicketsPage.ensureIsBuyDisplayed();
        mainTicketsPage.ensureIsBuyClickable();
        mainTicketsPage.initBuying(browser, MainTicketsPage.Type.SINGLE);

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainTicketsPage.ensureIsBuyDisplayed();
        mainTicketsPage.ensureIsBuyClickable();
        mainTicketsPage.initBuying(browser, MainTicketsPage.Type.MONTHLY);

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainTicketsPage.ensureIsBuyDisplayed();
        mainTicketsPage.ensureIsBuyClickable();
        mainTicketsPage.initBuying(browser, MainTicketsPage.Type.YEARLY);


        mainTicketsPage.getListOwnedButton().click();
        (new WebDriverWait(browser, 5))
                .until(ExpectedConditions.visibilityOf(ownedTicketsPage.getTicketsTable()));

        int finalTicketsNumber = ownedTicketsPage.numberOfTickets(browser);

        assertEquals(finalTicketsNumber, initialTicketsNumber + 3);

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @After
    public void closeSelenium() {
        browser.quit();
    }
}
