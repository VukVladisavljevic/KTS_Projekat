package com.kits.project.e2e.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class MainTicketsPage {

    private WebDriver driver;

    @FindBy(id = "buyButton")
    private WebElement buyButton;

    @FindBy(css = "button.btn.btn-light")
    private WebElement listOwnedButton;

    @FindBy(id = "ticketType")
    private WebElement typeSelect;

    @FindBy(xpath = "//input")
    private WebElement usernameInput;

    @FindBy(css="button.btn.btn-light")
    private WebElement searchUser;

    @FindBy()
    private WebElement foundTicketsTable;

    public WebElement getBuyButton() {
        return buyButton;
    }

    public void setBuyButton(WebElement buyButton) {
        this.buyButton = buyButton;
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(WebElement usernameInput) {
        this.usernameInput = usernameInput;
    }

    public WebElement getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(WebElement searchUser) {
        this.searchUser = searchUser;
    }

    public WebElement getListOwnedButton() {
        return listOwnedButton;
    }

    public void setListOwnedButton(WebElement listOwnedButton) {
        this.listOwnedButton = listOwnedButton;
    }

    public WebElement getTypeSelect() {
        return typeSelect;
    }

    public void setTypeSelect(WebElement typeSelect) {
        this.typeSelect = typeSelect;
    }

    public MainTicketsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsListingClickable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(listOwnedButton));
    }

    public void ensureIsBuyClickable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(buyButton));
    }

    public void ensureIsBuyDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(buyButton));
    }

    public void ensureIsListDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(listOwnedButton));
    }

    public void ensureIsControllerFieldVisible() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(searchUser));
    }

    public enum Type {
        SINGLE, MONTHLY, YEARLY;
    }

    public void initBuying(WebDriver browser, Type ticketType) {
        switch (ticketType) {
            case SINGLE:
                buyTicket(browser, "One-way ticket successfully bought! ", 0 );
                break;
            case MONTHLY:
                buyTicket(browser, "Monthly ticket successfully bought! ", 1 );
                break;
            case YEARLY:
                buyTicket(browser, "Yearly ticket successfully bought! ", 2 );
                break;
        }
    }

    private void buyTicket(WebDriver browser, String message, int index) {
        (new WebDriverWait(browser, 5))
                .until(ExpectedConditions.elementToBeClickable(this.typeSelect));

        Select dropdown = new Select(this.getTypeSelect());

        dropdown.selectByIndex(index);

        this.getBuyButton().click();

        Alert alert = null;

        try {
            alert = waitForAlert(browser);
        } catch (InterruptedException e) {
            e.printStackTrace();
        };

        assertEquals(message, alert.getText());
        alert.accept();
    }

    public Alert waitForAlert(WebDriver driver) throws InterruptedException {
        int i=0;
        while(i++<5)
        {
            try
            {
                Alert alert = driver.switchTo().alert();
                return alert;
            }
            catch(NoAlertPresentException e)
            {
                Thread.sleep(1000);
                continue;
            }
        }
        return null;
    }

    public int numberOfTickets() {
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("tr"))));
        return this.driver.findElements(By.cssSelector("tr")).size();
    }

}