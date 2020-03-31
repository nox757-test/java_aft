package ru.chibisov.aft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testNewGroupCreation() throws Exception {
        app.getNavigationHelper().openGroupPage();
        List<GroupData> dataListBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().openCreationGroupPage();
        GroupData addedGroup = new GroupData("group_header", "group_name", "group_footer");
        app.getGroupHelper().fillGroup(addedGroup);
        app.getGroupHelper().submitCreationGroup();
        app.getGroupHelper().backToGroupPage();

        List<GroupData> dataListAfter = app.getGroupHelper().getGroupList();
        int id = dataListAfter.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId();
        Comparator<GroupData> comparator = Comparator.comparing(GroupData::getName);
        dataListBefore.add(addedGroup.setId(id));
        dataListAfter.sort(comparator);
        dataListBefore.sort(comparator);
        Assert.assertEquals(dataListAfter, dataListBefore);
    }

}
