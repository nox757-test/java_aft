package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.GroupData;
import ru.chibisov.aft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactWithGroupTests extends TestBase {

    private ContactData contactData;

    @BeforeMethod
    public void generatePreconditions() {
        contactData = new ContactData().setMiddleName("mid_name2")
                .setLastName("last_name2")
                .setNickName("nickname2")
                .setGroupName("[none]")
                .setHomePhone("+7 916 208 102")
                .setMobilePhone("12 22")
                .setWorkPhone("33-33")
                .setEmail1("mail11@m.ru")
                .setEmail3("mail3@m.ru")
                .setPostAddress("jdkfjajdfkjas\r\ndksjafjskjeii2");
        app.contact().createNew(contactData);
        if(app.db().groups().size() == 0) {
            app.group().createNew();
        }
    }

    @Test
    public void editGroupDbTest() {
        app.goTo().groupPage();
        generatePreconditions();
        Groups groupsBefore = new Groups(app.db().groups());
        GroupData group = groupsBefore.iterator().next();
        assertThat(group.getContacts(), not(hasItem(contactData)));
        app.contact().addToGroup(contactData, group.getName());
        app.goTo().contactPage();

        GroupData groupAfter = app.db().groupById(group.getId());
        assertThat(groupAfter.getContacts(), hasItem(contactData));

    }


}
