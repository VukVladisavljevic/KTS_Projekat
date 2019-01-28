package com.kits.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

public class ReportsPage {
    private WebDriver driver;

    @FindBy(css = "tr:nth-child(2) > th:nth-child(1) > ngb-datepicker")
    private WebElement startDate;

    @FindBy(css = "tr:nth-child(2) > th:nth-child(2) > ngb-datepicker")
    private WebElement endDate;

    @FindBy(xpath = "//button[contains(text(),'Refresh')]")
    private WebElement submitButton;

    @FindBy(css = "th:nth-child(1) .custom-select:nth-child(2)")
    private WebElement startDateYear;

    @FindBy(css = "th:nth-child(2) .custom-select:nth-child(2)")
    private WebElement endDateYear;

    @FindBy(css = "tr:nth-child(2) > th:nth-child(1)  > ngb-datepicker > .ngb-dp-months  > .ngb-dp-month > ngb-datepicker-month-view > div:nth-of-type(2) > div")
    private WebElement startDateDay;

    @FindBy(css = "tr:nth-child(2) > th:nth-child(2)  > ngb-datepicker > .ngb-dp-months  > .ngb-dp-month > ngb-datepicker-month-view > div:nth-of-type(2) > div")
    private WebElement endDateDay;

    @FindBy(css = "th:nth-child(1) .custom-select:nth-child(1)")
    private WebElement startDateMonth;

    @FindBy(css = "th:nth-child(2) .custom-select:nth-child(1)")
    private WebElement endDateMonth;

    @FindBy(css = "tr:nth-child(3) > th:nth-child(2)")
    private WebElement total;

    public ReportsPage(WebDriver driver) { this.driver = driver; }

    public WebElement getStartDate() {
        return startDate;
    }

    public void setStartDate(WebElement startDate) {
        this.startDate = startDate;
    }

    public WebElement getEndDate() {
        return endDate;
    }

    public void setEndDate(WebElement endDate) {
        this.endDate = endDate;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    public WebElement getStartDateYear() {
        return startDateYear;
    }

    public void setStartDateYear(WebElement startDateYear) {
        this.startDateYear = startDateYear;
    }

    public WebElement getEndDateYear() {
        return endDateYear;
    }

    public void setEndDateYear(WebElement endDateYear) {
        this.endDateYear = endDateYear;
    }

    public WebElement getStartDateDay() {
        return startDateDay;
    }

    public void setStartDateDay(WebElement startDateDay) {
        this.startDateDay = startDateDay;
    }

    public WebElement getEndDateDay() {
        return endDateDay;
    }

    public void setEndDateDay(WebElement endDateDay) {
        this.endDateDay = endDateDay;
    }

    public WebElement getStartDateMonth() {
        return startDateMonth;
    }

    public void setStartDateMonth(WebElement startDateMonth) {
        this.startDateMonth = startDateMonth;
    }

    public WebElement getEndDateMonth() {
        return endDateMonth;
    }

    public void setEndDateMonth(WebElement endDateMonth) {
        this.endDateMonth = endDateMonth;
    }

    public WebElement getTotal() {
        return total;
    }

    public void setTotal(WebElement total) {
        this.total = total;
    }

    public void ensureStartDateIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(startDate));
    }

    public void ensureEndDateIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(endDate));
    }

    public void ensureSubmitButtonIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(submitButton));
    }

    public void getReports(Date startDate, Date endDate) {
        this.getStartDateYear().sendKeys("2018");
        this.getEndDateYear().sendKeys("2018");
        this.getStartDateMonth().sendKeys("Jan");
        this.getEndDateMonth().sendKeys("Dec");
        this.getStartDateDay().click();
        this.getEndDateDay().click();
    }
}
