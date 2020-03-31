package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

public class ContactEditTest extends TestBase {

    @Test
    public void updateFirstContract() {
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createNewContact();
        }
        app.getContactHelper().pressEditButtonInRow(0);
        app.getContactHelper()
                .fillContact(new ContactData(null, "mid_name2", "last_name2", "nickname2"),
                        false
                );
        app.getContactHelper().pressUpdateButton();
    }
}
