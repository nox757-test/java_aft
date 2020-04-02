package ru.chibisov.aft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.chibisov.aft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Count contact")
    public int count;
    @Parameter(names = "-f", description = "Path to file")
    public String path;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator dataGenerator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(dataGenerator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        dataGenerator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateGroups(count);
        saveAsJson(contacts, new File(path));
    }

    public List<ContactData> generateGroups(int num) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            contacts.add(new ContactData().setFirstName("f_name")
                    .setMiddleName("mid_name")
                    .setLastName("last_name")
                    .setNickName("nickname")
                    .setGroupName("[none]"));
        }
        return contacts;
    }

    public void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }

    }
}
