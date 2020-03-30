package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.chibisov.aft.addressbook.model.ContactData;

public class ContactHelper {
    private final WebDriver driver;

    public ContactHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openCreationContactPage() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void submitCreationContact() {
        driver.findElement(By.name("submit")).click();
    }

    public void fillCreationContact(ContactData contactData) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).clear();
        driver.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).clear();
        driver.findElement(By.name("nickname")).sendKeys(contactData.getNickName());
    }
}
