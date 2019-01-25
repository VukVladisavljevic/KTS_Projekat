package com.kits.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement pass;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginbutton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getPass() {
        return pass;
    }

    public void setPass(WebElement pass) {
        this.pass = pass;
    }

    public WebElement getLoginbutton() {
        return loginbutton;
    }

    public void setLoginbutton(WebElement loginbutton) {
        this.loginbutton = loginbutton;
    }

    public void ensureIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(username));
    }

    public void ensurePasswordIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(pass));
    }

    public void ensureLoginButtonIsDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(loginbutton));
    }

    public void ensureIsClickable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(loginbutton));
    }

    public void loginAs(String user, String password) {
        username.clear();
        pass.clear();
        username.sendKeys(user);
        pass.sendKeys(password);
        loginbutton.click();
    }

}
