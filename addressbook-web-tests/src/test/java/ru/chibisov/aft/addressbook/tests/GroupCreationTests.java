package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testNewGroupCreation() throws Exception {
        app.openGroupPage();
        app.openCreationGroupPage();
        app.fillCreationGroup(new GroupData("group_header", "group_name", "group_footer"));
        app.submitCreationGroup();
        app.backToGroupPage();
    }

}
