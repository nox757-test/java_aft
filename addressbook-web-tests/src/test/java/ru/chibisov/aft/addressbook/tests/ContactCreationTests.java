package ru.chibisov.aft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.Contacts;

public class ContactCreationTests extends TestBase {

    @Test
    public void addNewContactTest() {
        Contacts contactsBefore = app.contact().all();
        app.goTo().openCreationContactPage();
        ContactData contactData = new ContactData().setFirstName("f_name")
                .setMiddleName("mid_name")
                .setLastName("last_name")
                .setNickName("nickname")
                .setGroupName("[none]");
        app.contact().fillForm(contactData, true);
        app.contact().submitCreationContact();

        Contacts contactsAfter = app.contact().all();
        int id = contactsAfter.stream().mapToInt(ContactData::getId).max().getAsInt();
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(contactsBefore.withAdd(contactData.setId(id))));
    }
}
