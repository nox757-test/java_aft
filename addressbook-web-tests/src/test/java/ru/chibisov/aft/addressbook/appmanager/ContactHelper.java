package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.chibisov.aft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void openCreationContactPage() {
        clickByElement(By.linkText("add new"));
    }

    public void submitCreationContact() {
        clickByElement(By.name("submit"));
    }

    public void fillCreationContact(ContactData contactData) {
        inputType(By.name("firstname"), contactData.getFirstName());
        inputType(By.name("middlename"), contactData.getMiddleName());
        inputType(By.name("lastname"), contactData.getLastName());
        inputType(By.name("nickname"), contactData.getNickName());
    }

}
