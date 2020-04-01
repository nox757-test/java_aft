package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (!app.group().isThereGroup()) {
            app.group().createNew();
        }
    }

    @Test
    public void deleteFirstGroupTest() {
        app.goTo().groupPage();
        generatePreconditions();
        List<GroupData> dataListBefore = app.group().list();
        int deletedRowIndex = dataListBefore.size() - 1;
        app.group().delete(deletedRowIndex);

        List<GroupData> dataListAfter = app.group().list();
        Comparator<GroupData> comparator = Comparator.comparingInt(GroupData::getId);
        dataListBefore.remove(deletedRowIndex);
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }
}
