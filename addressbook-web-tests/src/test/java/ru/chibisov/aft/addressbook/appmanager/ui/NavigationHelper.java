package ru.chibisov.aft.addressbook.appmanager.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void groupPage() {
        clickByElement(By.linkText("groups"));
    }

    public void openCreationContactPage() {
        clickByElement(By.linkText("add new"));
    }

    public void contactPage() {
        clickByElement(By.linkText("home"));
    }

}
