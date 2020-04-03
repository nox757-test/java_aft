package ru.chibisov.aft.addressbook.tests;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.Contacts;

import javax.persistence.Id;
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
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getAnnotation(Id.class) != null;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            }).create();
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
        ContactData copyContactAsUi = new ContactData().setId(id)
                .setFirstName(contactData.getFirstName())
                .setLastName(contactData.getLastName())
                .setPostAddress(contactData.getPostAddress())
                .setEmail1(contactData.getEmail1())
                .setEmail2(contactData.getEmail2())
                .setEmail3(contactData.getEmail3())
                .setHomePhone(contactData.getHomePhone())
                .setWorkPhone(contactData.getWorkPhone())
                .setMobilePhone(contactData.getMobilePhone());
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(contactsBefore.withAdd(copyContactAsUi)));
    }

    @Test(dataProvider = "readContacts")
    public void addNewContactDbTest(ContactData contactData) {
        Contacts contactsBefore = new Contacts(app.db().contacts());
        app.goTo().openCreationContactPage();
        app.contact().fillForm(contactData, true);
        app.contact().submitCreationContact();

        Contacts contactsAfter = new Contacts(app.db().contacts());
        int id = contactsAfter.stream().mapToInt(ContactData::getId).max().getAsInt();
        MatcherAssert.assertThat(contactsAfter, CoreMatchers.equalTo(contactsBefore.withAdd(contactData.setId(id))));
    }
}
