package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() throws Exception {
        app.openCreationContactPage();
        app.fillCreationContact(new ContactData("f_name", "mid_name", "last_name", "nickname"));
        app.submitCreationContact();
    }
}