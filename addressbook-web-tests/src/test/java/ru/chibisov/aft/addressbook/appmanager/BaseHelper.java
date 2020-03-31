package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BaseHelper {
    protected final WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickByElement(By locator) {
        driver.findElement(locator).click();
    }

    protected void clickByElementInTableRow(By locator, int row) {
        List<WebElement> elements = driver.findElements(locator);
        elements.get(row).click();
    }

    protected void inputType(By locator, String data) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(data);
    }

    protected void selectCheckbox(By locator, int row) {
        List<WebElement> elements = driver.findElements(locator);
        elements.get(row).click();
    }

    protected String closeAlert(boolean action) {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (action) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } catch (NoAlertPresentException ignored) {

        }
        return "";
    }

}
