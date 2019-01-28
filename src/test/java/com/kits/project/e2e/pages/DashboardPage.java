package com.kits.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Register new')]")
    private WebElement registerButton;

    @FindBy(xpath = "//a[contains(text(),'See all')]")
    private WebElement seeAllButton;

    @FindBy(xpath = "//a[contains(text(),'Show reports')]")
    private WebElement showReportsButton;

    public DashboardPage (WebDriver driver) { this.driver = driver; }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(WebElement registerButton) {
        this.registerButton = registerButton;
    }

    public WebElement getSeeAllButton() {
        return seeAllButton;
    }

    public void setSeeAllButton(WebElement seeAllButton) {
        this.seeAllButton = seeAllButton;
    }

    public WebElement getShowReportsButton() {
        return showReportsButton;
    }

    public void setShowReportsButton(WebElement showReportsButton) {
        this.showReportsButton = showReportsButton;
    }

    public void ensureRegisterButtonIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(registerButton));
    }

    public void ensureSeeAllButtonIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(seeAllButton));
    }

    public void ensureShowReportsButtonIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(showReportsButton));
    }



}
