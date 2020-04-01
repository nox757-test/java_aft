package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (!app.contact().isThereContact()) {
            app.contact().createNew();
        }
    }

    @Test
    public void deleteFirstContract() {
        generatePreconditions();
        List<ContactData> dataListBefore = app.contact().list();
        int deletedRowIndex = dataListBefore.size() - 1;
        app.contact().delete(deletedRowIndex);

        List<ContactData> dataListAfter = app.contact().list();
        Comparator<ContactData> comparator = Comparator.comparing(ContactData::getLastName).thenComparing(ContactData::getFirstName);
        dataListBefore.remove(deletedRowIndex);
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);

    }

}
