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

    public void openCreationGroupPage() {
        clickByElement(By.name("new"));
    }

    public void fillCreationGroup(GroupData groupData) {
        inputType(By.name("group_name"), groupData.getName());
        inputType(By.name("group_header"), groupData.getHeader());
        inputType(By.name("group_footer"), groupData.getFooter());
    }
}
