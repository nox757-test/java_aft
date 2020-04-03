package ru.chibisov.aft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.Contacts;

public class ContactEditTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().createNew();
        }
    }

    @Test
    public void updateContactTest() {
        generatePreconditions();
        Contacts dataBefore = app.contact().all();
        ContactData contactData = dataBefore.iterator().next();
        contactData = contactData.setMiddleName("mid_name2")
                .setLastName("last_name2")
                .setNickName("nickname2");
        app.contact().modify(contactData);

        Contacts contactsAfter = app.contact().all();
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(dataBefore.withAdd(contactData)));

    }

    @Test
    public void updateContactDbTest() {
        generatePreconditions();
        Contacts dataBefore = new Contacts(app.db().contacts());
        ContactData contactData = dataBefore.iterator().next();
        contactData = contactData.setMiddleName("mid_name2")
                .setLastName("last_name2")
                .setNickName("nickname2");
        app.contact().modify(contactData);

        Contacts contactsAfter = new Contacts(app.db().contacts());
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(dataBefore.withAdd(contactData)));

    }

}
