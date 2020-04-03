package ru.chibisov.aft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts() {
        this.delegate = new HashSet<>();
    }

    public Contacts(Contacts Contacts) {
        this.delegate = new HashSet<>(Contacts.delegate);
    }

    public Contacts(List<ContactData> contacts) {
        this.delegate = new HashSet<>(contacts);
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdd(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts withOut(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}