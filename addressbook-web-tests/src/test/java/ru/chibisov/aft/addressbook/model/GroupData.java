package ru.chibisov.aft.addressbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_list")
public class GroupData {

    @Id
    @Column(name = "group_id")
    private int id;

    @Column(name = "group_header")
    @Type(type = "ru.chibisov.aft.addressbook.appmanager.db.EmptyTextType")
    private String header;
    @Column(name = "group_name")
    private String name;
    @Column(name = "group_footer")
    @Type(type = "ru.chibisov.aft.addressbook.appmanager.db.EmptyTextType")
    private String footer;

    @ManyToMany(mappedBy = "groups")
    private Set<ContactData> contacts = new HashSet<>();

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

    public GroupData setHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData setName(String name) {
        this.name = name;
        return this;
    }

    public GroupData setFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public Set<ContactData> getContacts() {
        return contacts;
    }

    public GroupData setContacts(Set<ContactData> contacts) {
        this.contacts = contacts;
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
        if (header != null ? !header.equals(groupData.header) : groupData.header != null) return false;
        if (name != null ? !name.equals(groupData.name) : groupData.name != null) return false;
        return footer != null ? footer.equals(groupData.footer) : groupData.footer == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        return result;
    }

}
