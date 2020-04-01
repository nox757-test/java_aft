package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void pressEditButtonInRow(int numRow) {
        clickByElementInTableRow(By.xpath(".//img[@title='Edit']"), numRow);
    }

    public void pressEditButtonById(int id) {
        clickByElement(By.xpath(".//img[@title='Edit' and ./../self::node()[@href='edit.php?id=" + id + "']]"));
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

    public void selectContactById(int id) {
        clickByElement(By.xpath(".//*[@name='selected[]' and @value='" + id + "']"));
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void acceptAlterDelete() {
        closeAlert(true);
    }

    public void createNew() {
        new NavigationHelper(this.driver).openCreationContactPage();
        fillForm(new ContactData().setFirstName("default_name")
                        .setLastName("default_name")
                        .setMiddleName("default_name")
                        .setNickName("nickname")
                        .setGroupName("[none]"),
                true);
        submitCreationContact();
    }

    public void delete(ContactData deletedRowIndex) {
        selectContactById(deletedRowIndex.getId());
        pressDeleteButton();
        acceptAlterDelete();
    }

    public void modify(ContactData contactData) {
        pressEditButtonById(contactData.getId());
        fillForm(contactData, false);
        pressUpdateButton();
    }

    public Contacts all() {
        List<WebElement> elements = getListElements(By.xpath(".//tr[@name='entry']"));
        Contacts contacts = new Contacts();
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.xpath(".//*[@type='checkbox']")).getAttribute("id"));
            contacts.add(new ContactData().setFirstName(element.findElement(By.xpath(".//td[3]")).getText())
                    .setLastName(element.findElement(By.xpath(".//td[2]")).getText())
                    .setId(id));
        }
        return contacts;
    }
}
