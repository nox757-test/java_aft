package ru.chibisov.aft.addressbook.appmanager.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void login(String login, String pass) {
        inputType(By.name("user"), login);
        inputType(By.name("pass"), pass);
        clickByElement(By.xpath("//input[@value='Login']"));
    }
}
