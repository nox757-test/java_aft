package ru.chibisov.aft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() throws Exception {
        openCreationContactPage();
        fillCreationContact(new ContactData("f_name", "mid_name", "last_name", "nickname"));
        submitCreationContact();
    }
}
