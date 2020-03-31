package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

    @Test
    public void deleteFirstGroupTest(){
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().pressDeleteButton();
        app.getGroupHelper().backToGroupPage();
    }
}
