package ru.chibisov.aft.addressbook.model;

public class GroupData {
    private final String header;
    private final String name;
    private final String footer;

    private int id;

    public GroupData(String header, String name, String footer) {
        this(-1, header, name, footer);
    }

    public GroupData(int id, String header, String name, String footer) {
        this.id = id;
        this.header = header;
        this.name = name;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public String getName() {
        return name;
    }

    public String getFooter() {
        return footer;
    }

    public int getId() {
        return id;
    }

    public GroupData setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "header='" + header + '\'' +
                ", name='" + name + '\'' +
                ", footer='" + footer + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
