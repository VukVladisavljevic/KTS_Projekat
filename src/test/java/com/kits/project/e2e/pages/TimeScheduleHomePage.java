package com.kits.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeScheduleHomePage {

    private WebDriver driver;

    @FindBy(xpath="/html/body/app-root/app-timetable/div/table/tbody/tr[1]/td[2]/button")
    private WebElement addTimeScheduleButton;

    @FindBy(xpath="/html/body/app-root/app-timetable/div/table/tbody/tr[1]/td[3]/button")
    private WebElement showTimeScheduleButton;

    public TimeScheduleHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAddTimeScheduleButton() {
        return addTimeScheduleButton;
    }

    public void setAddTimeScheduleButton(WebElement addTimeScheduleButton) {
        this.addTimeScheduleButton = addTimeScheduleButton;
    }

    public WebElement getShowTimeScheduleButton() {
        return showTimeScheduleButton;
    }

    public void setShowTimeScheduleButton(WebElement showTimeScheduleButton) {
        this.showTimeScheduleButton = showTimeScheduleButton;
    }

    public void ensureAddButtonVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addTimeScheduleButton));
    }

    public void ensureAddButtonClickable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(addTimeScheduleButton));
    }

    public void ensureShowButtonVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(showTimeScheduleButton));
    }

    public void ensureShowButtonClickable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(showTimeScheduleButton));
    }
}
