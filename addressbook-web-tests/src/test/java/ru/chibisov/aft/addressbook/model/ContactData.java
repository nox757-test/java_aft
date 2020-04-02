package ru.chibisov.aft.addressbook.model;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;

    @Transient
    private String groupName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Column(name = "address")
    @Type(type = "text")
    private String postAddress;
    @Column(name = "email")
    @Type(type = "text")
    private String email1;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;

    @Transient
    private List<String> allEmails = null;

    public ContactData() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGroupName() {
        return groupName;
    }

    public ContactData setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public ContactData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContactData setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail1() {
        return email1;
    }

    public ContactData setEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData setEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData setEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public List<String> getAllEmails() {
        if (allEmails == null || allEmails.isEmpty()) {
            allEmails = new ArrayList<String>() {
                {
                    if (email1 != null && !email1.isEmpty()) add(email1);
                    if (email2 != null && !email2.isEmpty()) add(email2);
                    if (email3 != null && !email3.isEmpty()) add(email3);
                }
            };
        }
        return allEmails;
    }

    public ContactData setAllEmails(List<String> allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public ContactData setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public ContactData setHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData setAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public ContactData setPostAddress(String postAddress) {
        this.postAddress = postAddress;
        return this;
    }

    public Set<GroupData> getGroups() {
        return groups;
    }

    public ContactData setGroups(Set<GroupData> groups) {
        this.groups = groups;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

}
