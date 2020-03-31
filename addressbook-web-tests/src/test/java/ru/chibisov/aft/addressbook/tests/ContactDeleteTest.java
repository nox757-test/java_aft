package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeleteTest extends TestBase {

    @Test
    public void deleteFirstContract() {
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createNewContact();
        }
        List<ContactData> dataListBefore = app.getContactHelper().getContactList();
        int deletedRowIndex = dataListBefore.size() - 1;
        app.getContactHelper().selectContact(deletedRowIndex);
        app.getContactHelper().pressDeleteButton();
        app.getContactHelper().acceptAlterDelete();

        List<ContactData> dataListAfter = app.getContactHelper().getContactList();
        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastName).thenComparing(ContactData::getFirstName);
        dataListBefore.remove(deletedRowIndex);
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);

    }

}
