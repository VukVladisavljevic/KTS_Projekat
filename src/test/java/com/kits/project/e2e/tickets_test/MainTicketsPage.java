package com.kits.project.e2e.tickets_test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertEquals;

public class MainTicketsPage {

    private WebDriver driver;

    @FindBy(id = "buyButton")
    private WebElement buyButton;

    @FindBy(css = "button.btn.btn-light")
    private WebElement listOwnedButton;

    @FindBy(id = "ticketType")
    private WebElement typeSelect;

    public WebElement getBuyButton() {
        return buyButton;
    }

    public void setBuyButton(WebElement buyButton) {
        this.buyButton = buyButton;
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

    enum Type {
        SINGLE, MONTHLY, YEARLY;
    }

    public void initBuying(WebDriver browser, Type ticketType) {
        System.out.println("KUPUJE");
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

    private Alert waitForAlert(WebDriver driver) throws InterruptedException {
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
}