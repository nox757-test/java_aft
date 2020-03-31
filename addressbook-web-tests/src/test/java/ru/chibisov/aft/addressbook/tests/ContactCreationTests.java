package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() {
        List<ContactData> dataListBefore = app.getContactHelper().getContactList();
        app.getNavigationHelper().openCreationContactPage();
        ContactData contactData = new ContactData("f_name", "mid_name", "last_name", "nickname")
                .setGroupName("[none]");
        app.getContactHelper().fillContact(contactData, true);
        app.getContactHelper().submitCreationContact();

        List<ContactData> dataListAfter = app.getContactHelper().getContactList();
        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastName).thenComparing(ContactData::getFirstName);
        dataListBefore.add(contactData);
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }
}
