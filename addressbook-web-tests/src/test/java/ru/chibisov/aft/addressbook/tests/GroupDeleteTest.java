package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeleteTest extends TestBase {

    @Test
    public void deleteFirstGroupTest() {
        app.getNavigationHelper().openGroupPage();
        if (!app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createNewGroup();
        }
        List<GroupData> dataListBefore = app.getGroupHelper().getGroupList();
        int deletedRowIndex = dataListBefore.size() - 1;
        app.getGroupHelper().selectGroup(deletedRowIndex);
        app.getGroupHelper().pressDeleteButton();
        app.getGroupHelper().backToGroupPage();

        List<GroupData> dataListAfter = app.getGroupHelper().getGroupList();
        Comparator<GroupData> comparator = Comparator.comparingInt(GroupData::getId);
        dataListBefore.remove(deletedRowIndex);
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }
}
