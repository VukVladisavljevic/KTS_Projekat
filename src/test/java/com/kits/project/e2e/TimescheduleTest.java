package com.kits.project.e2e;

import com.kits.project.e2e.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertEquals;

public class TimescheduleTest {

    private WebDriver browser;
    TimeScheduleHomePage mainTimeschedulePage;
    AddTimeSchedulePage addTimeSchedulePage;
    ShowTimeSchedulesPage showTimeSchedulesPage;
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
        mainTimeschedulePage = PageFactory.initElements(browser, TimeScheduleHomePage.class);
        addTimeSchedulePage = PageFactory.initElements(browser, AddTimeSchedulePage.class);
        showTimeSchedulesPage = PageFactory.initElements(browser, ShowTimeSchedulesPage.class);
    }

    @Test
    public void testTimeschedulePage() {
        loginPage.ensureIsDisplayed();
        loginPage.ensurePasswordIsDisplayed();
        loginPage.ensureLoginButtonIsDisplayed();
        loginPage.ensureIsClickable();
        loginPage.getLoginbutton().click();
        loginPage.loginAs("aa", "aa");

        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.id("timeSchedule")));

        homePage.busIsDisplayed();
        WebElement timetable = homePage.getTimeScheduleLink();
        timetable.click();

       //get number of elements before adding any
        mainTimeschedulePage.ensureShowButtonVisible();
        mainTimeschedulePage.ensureShowButtonClickable();
        mainTimeschedulePage.getShowTimeScheduleButton().click();

        showTimeSchedulesPage.ensureTableVisible();
        int beforeAdding = showTimeSchedulesPage.getStationsTableSize();
        showTimeSchedulesPage.getDialogCloseButton().click();

        //open add dialog and add new
        mainTimeschedulePage.ensureAddButtonVisible();
        mainTimeschedulePage.getAddTimeScheduleButton().click();

        addTimeSchedulePage.ensureWorkingDayButtonVisible();
        addTimeSchedulePage.getWorkingDayOption().click();
        addTimeSchedulePage.ensureClockSelectVisible();
        addTimeSchedulePage.getDialogHourBox().clear();
        addTimeSchedulePage.getDialogHourBox().sendKeys("22");

        addTimeSchedulePage.ensureClockSelectVisible();
        addTimeSchedulePage.getDialogMinuteBox().clear();
        addTimeSchedulePage.getDialogMinuteBox().sendKeys("35");


        addTimeSchedulePage.getDialogDoneButton().click();

        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.id("timeSchedule")));
        //check number after addition
        mainTimeschedulePage.ensureShowButtonVisible();
        mainTimeschedulePage.ensureShowButtonClickable();
        Actions act = new Actions(browser);
        act.moveToElement(mainTimeschedulePage.getShowTimeScheduleButton()).click().perform();

        showTimeSchedulesPage.ensureTableVisible();
        int afterAdding = showTimeSchedulesPage.getStationsTableSize();

//        assertEquals(beforeAdding+1, afterAdding);


        showTimeSchedulesPage.ensureWorkingDayButtonVisible();
        showTimeSchedulesPage.ensureCancelButtonVisible();
        showTimeSchedulesPage.ensureDeleteVisible();
        showTimeSchedulesPage.getDialogDeleteButton().click();

        WebDriverWait wait = new WebDriverWait(browser, 1);
        showTimeSchedulesPage.getDialogCloseButton().click();

        wait = new WebDriverWait(browser, 1);
        mainTimeschedulePage.ensureShowButtonVisible();
        mainTimeschedulePage.ensureShowButtonClickable();
        mainTimeschedulePage.getShowTimeScheduleButton().click();

        showTimeSchedulesPage.ensureTableVisible();
        int afterDeleting = showTimeSchedulesPage.getStationsTableSize();

        assertEquals(beforeAdding, afterDeleting);

    }



    @After
    public void closeSelenium() {
           browser.quit();
    }
}
