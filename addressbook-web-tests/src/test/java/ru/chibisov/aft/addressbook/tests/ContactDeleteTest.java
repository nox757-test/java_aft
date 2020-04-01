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
        if (!app.contact().isThereContact()) {
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

}
