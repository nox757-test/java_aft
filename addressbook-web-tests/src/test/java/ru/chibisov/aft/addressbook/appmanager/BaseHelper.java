package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
    protected final WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickByElement(By locator) {
        driver.findElement(locator).click();
    }

    protected void inputType(By locator, String data) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(data);
    }
}
