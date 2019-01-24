package com.kits.project.e2e.tickets_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainTicketsPage {

    private WebDriver driver;

    @FindBy(id = "buyButton")
    private WebElement buyButton;

    @FindBy(id = "listingButton")
    private WebElement listOwnedButton;

    @FindBy(name = "submit_search")
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
}