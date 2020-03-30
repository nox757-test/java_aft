package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void openGroupPage() {
        clickByElement(By.linkText("groups"));
    }
}
