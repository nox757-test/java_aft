package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    public void fillContact(ContactData contactData, boolean isCreate) {
        inputType(By.name("firstname"), contactData.getFirstName());
        inputType(By.name("middlename"), contactData.getMiddleName());
        inputType(By.name("lastname"), contactData.getLastName());
        inputType(By.name("nickname"), contactData.getNickName());

        By groupLocator = By.name("new_group");
        if (isCreate) {
            if (!isElementPresent(groupLocator)) {
                throw new NoSuchElementException("No found element " + groupLocator.toString());
            }
            selectDropDown(groupLocator, contactData.getGroupName());
        } else {
            if (isElementPresent(By.name("new_group"))) {
                throw new RuntimeException("Element doesn't be here " + groupLocator.toString());
            }
        }
    }

    public void selectContact(int numRow) {
        selectCheckbox(By.name("selected[]"), numRow);
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void acceptAlterDelete() {
        closeAlert(true);
    }

    public void createNewContact() {
        new NavigationHelper(this.driver).openCreationContactPage();
        fillContact(new ContactData("default_name", "default_name", "default_name", "nickname")
                        .setGroupName("[none]"),
                true);
        submitCreationContact();
    }

}
