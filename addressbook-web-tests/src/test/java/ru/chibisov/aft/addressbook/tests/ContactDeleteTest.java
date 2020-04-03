package ru.chibisov.aft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.Contacts;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void generatePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().createNew();
        }
    }

    @Test
    public void deleteContactTest() {
        generatePreconditions();
        Contacts contactsBefore = app.contact().all();
        ContactData deletedContact = contactsBefore.iterator().next();
        app.contact().delete(deletedContact);

        Contacts contactsAfter = app.contact().all();
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(contactsBefore.withOut(deletedContact)));

    }

    @Test
    public void deleteContactDbTest() {
        generatePreconditions();
        Contacts contactsBefore = new Contacts(app.db().contacts());
        ContactData deletedContact = contactsBefore.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().contactPage();//delay action before db request
        Contacts contactsAfter = new Contacts(app.db().contacts());
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(contactsBefore.withOut(deletedContact)));

    }

}
