package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditTest extends TestBase {

    @Test
    public void updateFirstContract() {
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createNewContact();
        }
        List<ContactData> dataListBefore = app.getContactHelper().getContactList();
        int changedRowIndex = dataListBefore.size() - 1;
        app.getContactHelper().pressEditButtonInRow(changedRowIndex);
        ContactData contactData = new ContactData(null, "mid_name2", "last_name2", "nickname2");
        app.getContactHelper().fillContact(contactData, false);
        app.getContactHelper().pressUpdateButton();

        List<ContactData> dataListAfter = app.getContactHelper().getContactList();
        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastName).thenComparing(ContactData::getFirstName);
        dataListBefore.add(combineNewContactData(dataListBefore.get(changedRowIndex), contactData));
        dataListBefore.remove(changedRowIndex);
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }

    private ContactData combineNewContactData(ContactData oldData, ContactData newData) {

        return new ContactData(
                newData.getFirstName() != null ? newData.getFirstName() : oldData.getFirstName(),
                newData.getMiddleName() != null ? newData.getMiddleName() : oldData.getMiddleName(),
                newData.getLastName() != null ? newData.getLastName() : oldData.getLastName(),
                newData.getNickName() != null ? newData.getNickName() : oldData.getNickName()
        );
    }
}
