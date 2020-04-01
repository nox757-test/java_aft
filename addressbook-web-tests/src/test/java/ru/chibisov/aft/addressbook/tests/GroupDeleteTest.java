package ru.chibisov.aft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.GroupData;
import ru.chibisov.aft.addressbook.model.Groups;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (!app.group().isThereGroup()) {
            app.group().createNew();
        }
    }

    @Test
    public void deleteGroupTest() {
        app.goTo().groupPage();
        generatePreconditions();
        Groups contactBefore = app.group().all();
        GroupData deleteGroup = contactBefore.iterator().next();
        app.group().delete(deleteGroup);

        Groups contactAfter = app.group().all();
        MatcherAssert.assertThat(contactAfter, CoreMatchers.equalTo(contactBefore.withOut(deleteGroup)));

    }
}
