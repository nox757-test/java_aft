package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupEditTest extends TestBase {

    @Test
    public void editFirstGroupTest() {
        app.getNavigationHelper().openGroupPage();
        if (!app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createNewGroup();
        }
        List<GroupData> dataListBefore = app.getGroupHelper().getGroupList();
        int changedRowIndex = dataListBefore.size() - 1;
        app.getGroupHelper().selectGroup(changedRowIndex);
        app.getGroupHelper().pressEditButton();
        GroupData addedGroup = new GroupData(null, "new_group_name", null);
        app.getGroupHelper().fillGroup(addedGroup);
        app.getGroupHelper().pressUpdateButton();
        app.getGroupHelper().backToGroupPage();

        List<GroupData> dataListAfter = app.getGroupHelper().getGroupList();
        Comparator<GroupData> comparator = Comparator.comparingInt(GroupData::getId);
        int changedId = dataListBefore.stream().max(comparator).get().getId();
        dataListBefore.remove(changedRowIndex);
        dataListBefore.add(addedGroup.setId(changedId));
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }

}