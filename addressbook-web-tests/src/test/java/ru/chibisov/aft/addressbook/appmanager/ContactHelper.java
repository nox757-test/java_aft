package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void fillForm(ContactData contactData, boolean isCreate) {
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

    public void createNew() {
        new NavigationHelper(this.driver).openCreationContactPage();
        fillForm(new ContactData("default_name", "default_name", "default_name", "nickname")
                        .setGroupName("[none]"),
                true);
        submitCreationContact();
    }

    public List<ContactData> list() {
        List<WebElement> elements = getListElements(By.xpath(".//tr[@name='entry']"));
        List<ContactData> contactData = new ArrayList<>();
        for (WebElement element : elements) {
            contactData.add(
                    new ContactData(element.findElement(By.xpath(".//td[3]")).getText(),
                            null,
                            element.findElement(By.xpath(".//td[2]")).getText(),
                            null)
            );
        }
        return contactData;
    }

    public void delete(int deletedRowIndex) {
        selectContact(deletedRowIndex);
        pressDeleteButton();
        acceptAlterDelete();
    }

    public void modify(int changedRowIndex, ContactData contactData) {
        pressEditButtonInRow(changedRowIndex);
        fillForm(contactData, false);
        pressUpdateButton();
    }
}
