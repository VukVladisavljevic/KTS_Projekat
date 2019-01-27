package com.kits.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrentPricelistPage {

    private WebDriver driver;
    public CurrentPricelistPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsTableVisible() {
        (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.visibilityOf(this.driver.findElement(By.cssSelector("th"))));

    }
    public int getPricelistTableSize() {
        return this.driver.findElements(By.cssSelector("tr")).size();
    }
}
