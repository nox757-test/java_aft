package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.chibisov.aft.addressbook.model.GroupData;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void backToGroupPage() {
        clickByElement(By.linkText("group page"));
    }

    public void submitCreationGroup() {
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

    public void fillCreationGroup(GroupData groupData) {
        inputType(By.name("group_name"), groupData.getName());
        inputType(By.name("group_header"), groupData.getHeader());
        inputType(By.name("group_footer"), groupData.getFooter());
    }

    public void fillChangeGroup(GroupData groupData) {
        if (groupData.getName() != null) {
            inputType(By.name("group_name"), groupData.getName());
        }
        if (groupData.getHeader() != null) {
            inputType(By.name("group_header"), groupData.getHeader());
        }
        if (groupData.getFooter() != null) {
            inputType(By.name("group_footer"), groupData.getFooter());
        }
    }

    public void selectGroup(int numRow) {
        selectCheckbox(By.name("selected[]"), numRow);
    }
}
