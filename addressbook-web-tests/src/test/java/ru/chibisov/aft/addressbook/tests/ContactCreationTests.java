package ru.chibisov.aft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> readContacts() throws IOException {
        try (JsonReader reader = new JsonReader(new FileReader(
                new File("src/test/resources/generated/contacts.json")))) {
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(reader, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream()
                    .map(contactData -> new Object[]{contactData})
                    .collect(Collectors.toList())
                    .iterator();
        }
    }


    @Test(dataProvider = "readContacts")
    public void addNewContactTest(ContactData contactData) {
        Contacts contactsBefore = app.contact().all();
        app.goTo().openCreationContactPage();
        app.contact().fillForm(contactData, true);
        app.contact().submitCreationContact();

        Contacts contactsAfter = app.contact().all();
        int id = contactsAfter.stream().mapToInt(ContactData::getId).max().getAsInt();
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(contactsBefore.withAdd(contactData.setId(id))));
    }
}
