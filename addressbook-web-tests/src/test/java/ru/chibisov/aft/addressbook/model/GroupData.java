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
    @Type(type = "text")
    private String header;
    @Column(name = "group_name")
    private String name;
    @Column(name = "group_footer")
    @Type(type = "text")
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
        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
