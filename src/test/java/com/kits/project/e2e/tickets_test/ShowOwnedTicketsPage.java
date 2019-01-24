package com.kits.project.e2e.tickets_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowOwnedTicketsPage {

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

}
