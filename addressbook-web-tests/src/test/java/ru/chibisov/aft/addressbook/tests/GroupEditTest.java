package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;

public class GroupEditTest extends TestBase {

    @Test
    public void editFirstGroupTest() {
        app.getNavigationHelper().openGroupPage();
        if (!app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createNewGroup();
        }
        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().pressEditButton();
        app.getGroupHelper().fillGroup(new GroupData(null, "new_group_name", null));
        app.getGroupHelper().pressUpdateButton();
        app.getGroupHelper().backToGroupPage();
    }
}
