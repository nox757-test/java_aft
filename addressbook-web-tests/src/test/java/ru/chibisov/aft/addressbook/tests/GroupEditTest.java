package ru.chibisov.aft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;
import ru.chibisov.aft.addressbook.model.Groups;

public class GroupEditTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (!app.group().isThereGroup()) {
            app.group().createNew();
        }
    }

    @Test
    public void editGroupTest() {
        app.goTo().groupPage();
        generatePreconditions();
        Groups groupsBefore = app.group().all();
        GroupData changedData = groupsBefore.iterator().next().setName("new_name_group2");
        app.group().modify(changedData);

        Groups groupsAfter = app.group().all();
        MatcherAssert.assertThat(groupsAfter, CoreMatchers.equalTo(groupsBefore));

    }

}