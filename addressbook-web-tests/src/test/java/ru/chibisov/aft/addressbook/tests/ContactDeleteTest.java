package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase {

    @Test
    public void deleteFirstContract() {
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createNewContact();
        }
        app.getContactHelper().selectContact(0);
        app.getContactHelper().pressDeleteButton();
        app.getContactHelper().acceptAlterDelete();
    }

}
