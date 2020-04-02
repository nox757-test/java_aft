package ru.chibisov.aft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Count group")
    public int count;
    @Parameter(names = "-f", description = "Path to file")
    public String path;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator dataGenerator = new GroupDataGenerator();
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
        List<GroupData> groups = generateGroups(count);
        saveAsJson(groups, new File(path));
    }

    public List<GroupData> generateGroups(int num) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            groups.add(new GroupData().setName("gname_" + i)
                    .setHeader("header_" + i)
                    .setFooter("footer_" + i)
            );
        }
        return groups;
    }

    public void saveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }

    }
}
