package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupEditTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (!app.group().isThereGroup()) {
            app.group().createNew();
        }
    }

    @Test
    public void editFirstGroupTest() {
        app.goTo().groupPage();
        generatePreconditions();
        List<GroupData> dataListBefore = app.group().list();
        int changedRowIndex = dataListBefore.size() - 1;
        GroupData changedData = new GroupData(null, "new_group_name", null);
        app.group().modify(changedRowIndex, changedData);

        List<GroupData> dataListAfter = app.group().list();
        Comparator<GroupData> comparator = Comparator.comparingInt(GroupData::getId);
        int changedId = dataListBefore.stream().max(comparator).get().getId();
        dataListBefore.remove(changedRowIndex);
        dataListBefore.add(changedData.setId(changedId));
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }

}