package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.chibisov.aft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void pressEditButtonInRow(int numRow) {
        clickByElementInTableRow(By.xpath(".//img[@title='Edit']"), numRow);
    }

    public void submitCreationContact() {
        clickByElement(By.name("submit"));
    }

    public void pressUpdateButton() {
        clickByElement(By.xpath(".//*[@value='Update']"));
    }

    public void pressDeleteButton() {
        clickByElement(By.xpath(".//*[@value='Delete']"));
    }

    public void fillCreationContact(ContactData contactData) {
        inputType(By.name("firstname"), contactData.getFirstName());
        inputType(By.name("middlename"), contactData.getMiddleName());
        inputType(By.name("lastname"), contactData.getLastName());
        inputType(By.name("nickname"), contactData.getNickName());
    }

    public void selectContact(int numRow) {
        selectCheckbox(By.name("selected[]"), numRow);
    }

    public void acceptAlterDelete() {
        closeAlert(true);
    }

    public void changeCreationContact(ContactData contactData) {
        if (contactData.getFirstName() != null) {
            inputType(By.name("firstname"), contactData.getFirstName());
        }
        if (contactData.getMiddleName() != null) {
            inputType(By.name("middlename"), contactData.getMiddleName());
        }
        if (contactData.getLastName() != null) {
            inputType(By.name("lastname"), contactData.getLastName());
        }
        if (contactData.getNickName() != null) {
            inputType(By.name("nickname"), contactData.getNickName());
        }
    }
}
