package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (!app.contact().isThereContact()) {
            app.contact().createNew();
        }
    }

    @Test
    public void updateFirstContract() {
        generatePreconditions();
        List<ContactData> dataListBefore = app.contact().list();
        int changedRowIndex = dataListBefore.size() - 1;
        ContactData contactData = new ContactData(null, "mid_name2", "last_name2", "nickname2");
        app.contact().modify(changedRowIndex, contactData);

        List<ContactData> dataListAfter = app.contact().list();
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
