package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

public class ContactEditTest extends TestBase {

    @Test
    public void updateFirstContract() {
        app.getContactHelper().pressEditButtonInRow(0);
        app.getContactHelper().changeCreationContact(new ContactData(null, "mid_name2", "last_name2", "nickname2"));
        app.getContactHelper().pressUpdateButton();
    }
}
