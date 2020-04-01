package ru.chibisov.aft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;
import ru.chibisov.aft.addressbook.model.Groups;

public class GroupCreationTests extends TestBase {

    @Test
    public void addGroupTest() {
        app.goTo().groupPage();
        Groups groupsBefore = app.group().all();
        app.group().openCreationGroupPage();
        GroupData addedGroup = new GroupData().setId(-1)
                .setHeader("group_header")
                .setName("group_name")
                .setFooter("group_footer");
        app.group().fillForm(addedGroup);
        app.group().pressSubmitButton();
        app.group().backToGroupPage();

        Groups groupsAfter = app.group().all();
        int id = groupsAfter.stream().mapToInt(GroupData::getId).max().getAsInt();
        MatcherAssert.assertThat(groupsAfter, CoreMatchers.equalTo(groupsBefore.withAdd(addedGroup.setId(id))));

    }

}
