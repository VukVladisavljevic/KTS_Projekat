package com.kits.project.e2e;

import com.kits.project.e2e.pages.HomePage;
import com.kits.project.e2e.pages.LoginPage;
import com.kits.project.e2e.pages.MainTicketsPage;
import com.kits.project.e2e.pages.ShowOwnedTicketsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.*;

public class TicketBuyingTest {
    private WebDriver browser;
    MainTicketsPage mainTicketsPage;
    ShowOwnedTicketsPage ownedTicketsPage;
    LoginPage loginPage;
    HomePage homePage;


    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
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
        loginPage.loginAs("user", "aa");

        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.id("ticketsLink")));

        homePage.busIsDisplayed();
        homePage.ticketsLinkIsDisplayed();
        homePage.ticketLinkIsClickable();
        WebElement tickets = homePage.getTicketsLink();


        homePage.getTicketsLink().click();


        mainTicketsPage.ensureIsListDisplayed();
        mainTicketsPage.ensureIsListingClickable();
        mainTicketsPage.getListOwnedButton().click();

        WebDriverWait wait = new WebDriverWait(browser, 1);

        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.visibilityOf(
                        ownedTicketsPage.getTicketsTable()));

        int initialTicketsNumber = ownedTicketsPage.numberOfTickets();

        System.out.println(initialTicketsNumber);
        WebDriverWait wait2 = new WebDriverWait(browser, 1);

        ownedTicketsPage.ensureIsCloseButtonDisplayed();
        ownedTicketsPage.ensureIsCloseButtonClickable();
        ownedTicketsPage.ensureIsModalVisible();
        ownedTicketsPage.getCloseButton().click();

        mainTicketsPage.ensureIsBuyDisplayed();
        mainTicketsPage.ensureIsBuyClickable();
        mainTicketsPage.initBuying(browser, MainTicketsPage.Type.SINGLE);

        mainTicketsPage.ensureIsBuyDisplayed();
        mainTicketsPage.ensureIsBuyClickable();
        mainTicketsPage.initBuying(browser, MainTicketsPage.Type.MONTHLY);


        mainTicketsPage.ensureIsBuyDisplayed();
        mainTicketsPage.ensureIsBuyClickable();
        mainTicketsPage.initBuying(browser, MainTicketsPage.Type.YEARLY);


        mainTicketsPage.getListOwnedButton().click();
        (new WebDriverWait(browser, 5))
                .until(ExpectedConditions.visibilityOf(ownedTicketsPage.getTicketsTable()));


        int finalTicketsNumber = ownedTicketsPage.numberOfTickets();
        assertEquals(initialTicketsNumber + 3, finalTicketsNumber );
    }

    @Test
    public void checkUserTicketsTest(){

        loginPage.ensureIsDisplayed();
        loginPage.ensureIsDisplayed();
        loginPage.ensurePasswordIsDisplayed();
        loginPage.ensureLoginButtonIsDisplayed();
        loginPage.ensureIsClickable();
        loginPage.getLoginbutton().click();
        loginPage.loginAs("controller", "aa");

        homePage.busIsDisplayed();
        homePage.ticketsLinkIsDisplayed();
        homePage.ticketLinkIsClickable();
        homePage.getTicketsLink().click();

        mainTicketsPage.ensureIsControllerFieldVisible();
        mainTicketsPage.getUsernameInput().sendKeys("userasadsd");
        mainTicketsPage.getSearchUser().click();
        Alert alert = null;

        try {
            alert = mainTicketsPage.waitForAlert(browser);
        } catch (InterruptedException e) {
            e.printStackTrace();
        };
        assertEquals(alert.getText(),"User not found!");
        alert.accept();

        mainTicketsPage.ensureIsControllerFieldVisible();
        mainTicketsPage.getUsernameInput().clear();
        mainTicketsPage.getUsernameInput().sendKeys("user");
        mainTicketsPage.getSearchUser().click();
        assertNotSame(mainTicketsPage.numberOfTickets(), 0);;

    }



    @After
    public void closeSelenium() {
        browser.quit();
    }
}
