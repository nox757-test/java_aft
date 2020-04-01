package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContactCreation() {
        List<ContactData> dataListBefore = app.contact().list();
        app.goTo().openCreationContactPage();
        ContactData contactData = new ContactData("f_name", "mid_name", "last_name", "nickname")
                .setGroupName("[none]");
        app.contact().fillForm(contactData, true);
        app.contact().submitCreationContact();

        List<ContactData> dataListAfter = app.contact().list();
        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastName).thenComparing(ContactData::getFirstName);
        dataListBefore.add(contactData);
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }
}
