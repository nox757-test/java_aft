package ru.chibisov.aft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.util.ArrayList;
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

    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createNew() {
        openCreationGroupPage();
        fillForm(new GroupData("default_header", "default_name", "default_footer"));
        pressSubmitButton();
        backToGroupPage();
    }

    public List<GroupData> list() {
        List<WebElement> groupList = getListElements(By.xpath(".//span[@class='group']"));
        List<GroupData> groupDataList = new ArrayList<>();
        for (WebElement element : groupList) {
            groupDataList.add(
                    new GroupData(Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value")),
                            null,
                            element.getText().trim(),
                            null)
            );
        }
        return groupDataList;
    }

    public void delete(int deletedRowIndex) {
        selectGroup(deletedRowIndex);
        pressDeleteButton();
        backToGroupPage();
    }

    public void modify(int changedRowIndex, GroupData changedData) {
        selectGroup(changedRowIndex);
        pressEditButton();
        fillForm(changedData);
        pressUpdateButton();
        backToGroupPage();
    }
}
