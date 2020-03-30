package ru.chibisov.aft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testNewGroupCreation() throws Exception {
        openGroupPage();
        openCreationGroupPage();
        fillCreationGroup(new GroupData("group_header", "group_name", "group_footer"));
        submitCreationGroup();
        backToGroupPage();
    }

}
