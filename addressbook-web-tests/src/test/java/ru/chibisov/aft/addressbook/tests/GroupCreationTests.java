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
import ru.chibisov.aft.addressbook.model.GroupData;
import ru.chibisov.aft.addressbook.model.GroupData;
import ru.chibisov.aft.addressbook.model.Groups;

import javax.persistence.Id;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> readGroups() throws IOException {
        try (JsonReader reader = new JsonReader(new FileReader(
                new File("src/test/resources/generated/groups.json")))) {
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
            List<GroupData> contacts = gson.fromJson(reader, new TypeToken<List<GroupData>>() {
            }.getType());
            return contacts.stream()
                    .map(contactData -> new Object[]{contactData})
                    .collect(Collectors.toList())
                    .iterator();
        }
    }


    @Test(dataProvider = "readGroups")
    public void addGroupTest(GroupData addedGroup) {
        app.goTo().groupPage();
        Groups groupsBefore = app.group().all();
        app.group().openCreationGroupPage();
        app.group().fillForm(addedGroup);
        app.group().pressSubmitButton();
        app.group().backToGroupPage();

        Groups groupsAfter = app.group().all();
        int id = groupsAfter.stream().mapToInt(GroupData::getId).max().getAsInt();
        GroupData copyToUi = new GroupData().setId(id).setName(addedGroup.getName());
        MatcherAssert.assertThat(groupsAfter, CoreMatchers.equalTo(groupsBefore.withAdd(copyToUi)));

    }


    @Test(dataProvider = "readGroups")
    public void addGroupDbTest(GroupData addedGroup) {
        app.goTo().groupPage();
        Groups groupsBefore = new Groups(app.db().groups());
        app.group().openCreationGroupPage();
        app.group().fillForm(addedGroup);
        app.group().pressSubmitButton();
        app.group().backToGroupPage();

        Groups groupsAfter = new Groups(app.db().groups());
        int id = groupsAfter.stream().mapToInt(GroupData::getId).max().getAsInt();
        MatcherAssert.assertThat(groupsAfter, CoreMatchers.equalTo(groupsBefore.withAdd(addedGroup.setId(id))));

    }

}
