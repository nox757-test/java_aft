package ru.chibisov.aft.addressbook.appmanager.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.chibisov.aft.addressbook.model.GroupData;
import ru.chibisov.aft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void backToGroupPage() {
        clickByElement(By.linkText("group page"));
    }

    public void pressSubmitButton() {
        clickByElement(By.name("submit"));
    }

    public void pressUpdateButton() {
        clickByElement(By.name("update"));
    }

    public void pressDeleteButton() {
        clickByElement(By.name("delete"));
    }

    public void pressEditButton() {
        clickByElement(By.name("edit"));
    }

    public void openCreationGroupPage() {
        clickByElement(By.name("new"));
    }

    public void fillForm(GroupData groupData) {
        inputType(By.name("group_name"), groupData.getName());
        inputType(By.name("group_header"), groupData.getHeader());
        inputType(By.name("group_footer"), groupData.getFooter());
    }

    public void selectGroup(int numRow) {
        selectCheckbox(By.name("selected[]"), numRow);
    }

    public void selectGroupById(int id) {
        clickByElement(By.xpath(".//*[@name='selected[]' and @value='" + id + "']"));
    }

    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createNew() {
        openCreationGroupPage();
        fillForm(new GroupData().setHeader("default_header")
                .setName("default_name")
                .setFooter("default_footer"));
        pressSubmitButton();
        backToGroupPage();
    }

    public void delete(GroupData groupData) {
        selectGroupById(groupData.getId());
        pressDeleteButton();
        backToGroupPage();
    }

    public void modify(GroupData changedData) {
        selectGroupById(changedData.getId());
        pressEditButton();
        fillForm(changedData);
        pressUpdateButton();
        backToGroupPage();
    }

    public Groups all() {
        List<WebElement> groupList = getListElements(By.xpath(".//span[@class='group']"));
        Groups groups = new Groups();
        for (WebElement element : groupList) {
            groups.add(new GroupData().setId(Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value")))
                    .setName(element.getText().trim()));
        }
        return groups;
    }
}
