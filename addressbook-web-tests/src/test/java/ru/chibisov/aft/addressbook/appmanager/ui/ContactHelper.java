package ru.chibisov.aft.addressbook.appmanager.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.chibisov.aft.addressbook.appmanager.db.DbHelper;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.Contacts;

import java.util.Arrays;
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

    public void pressAddToButton() {
        clickByElement(By.xpath(".//*[@value='Add to']"));
    }

    public void pressDeleteButton() {
        clickByElement(By.xpath(".//*[@value='Delete']"));
    }

    public void fillForm(ContactData contactData, boolean isCreate) {
        inputType(By.name("firstname"), contactData.getFirstName());
        inputType(By.name("middlename"), contactData.getMiddleName());
        inputType(By.name("lastname"), contactData.getLastName());
        inputType(By.name("nickname"), contactData.getNickName());
        inputType(By.name("address"), contactData.getPostAddress());
        inputType(By.name("email"), contactData.getEmail1());
        inputType(By.name("email2"), contactData.getEmail2());
        inputType(By.name("email3"), contactData.getEmail3());
        inputType(By.name("mobile"), contactData.getMobilePhone());
        inputType(By.name("home"), contactData.getHomePhone());
        inputType(By.name("work"), contactData.getWorkPhone());

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

    public void createNew(ContactData contactData) {
        new NavigationHelper(this.driver).openCreationContactPage();
        fillForm(contactData,
                true);
        submitCreationContact();
        contactData.setId(new DbHelper().contacts().stream().mapToInt(ContactData::getId).max().getAsInt());
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

    public void addToGroup(ContactData contactData, String groupName) {
        selectContactById(contactData.getId());
        selectDropDown(By.name("to_group"), groupName);
        pressAddToButton();
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

    public ContactData getRowById(int id) {
        WebElement element = driver.findElement(By.xpath(".//tr[@name='entry' and .//input[@id='" + id + "']]"));
        String emailCellText = element.findElement(By.xpath(".//td[5]")).getText();
        List<String> allEmails = (emailCellText != null && !emailCellText.isEmpty()) ? Arrays.asList(emailCellText.split("\n")) : null;
        return new ContactData().setFirstName(element.findElement(By.xpath(".//td[3]")).getText())
                .setLastName(element.findElement(By.xpath(".//td[2]")).getText())
                .setPostAddress(element.findElement(By.xpath(".//td[4]")).getText())
                .setAllPhones(element.findElement(By.xpath(".//td[6]")).getText())
                .setAllEmails(allEmails)
                .setId(id);
    }

    public ContactData infoFromEditForm(ContactData contactData) {
        pressEditButtonById(contactData.getId());
        String lastName = driver.findElement(By.xpath(".//*[@name='lastname']")).getAttribute("value");
        String firstName = driver.findElement(By.xpath(".//*[@name='firstname']")).getAttribute("value");
        String postAddress = driver.findElement(By.xpath(".//*[@name='address']")).getText();
        String mobilePhone = driver.findElement(By.xpath(".//*[@name='mobile']")).getAttribute("value");
        String homePhone = driver.findElement(By.xpath(".//*[@name='home']")).getAttribute("value");
        String workPhone = driver.findElement(By.xpath(".//*[@name='work']")).getAttribute("value");
        String email1 = driver.findElement(By.xpath(".//*[@name='email']")).getAttribute("value");
        String email2 = driver.findElement(By.xpath(".//*[@name='email2']")).getAttribute("value");
        String email3 = driver.findElement(By.xpath(".//*[@name='email3']")).getAttribute("value");

        return new ContactData().setId(contactData.getId())
                .setLastName(lastName)
                .setFirstName(firstName)
                .setPostAddress(postAddress)
                .setEmail1(email1)
                .setEmail2(email2)
                .setEmail2(email3)
                .setMobilePhone(mobilePhone)
                .setHomePhone(homePhone)
                .setWorkPhone(workPhone);
    }

}
