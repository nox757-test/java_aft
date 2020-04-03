package ru.chibisov.aft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups() {
        this.delegate = new HashSet<>();
    }

    public Groups(Groups groups) {
        this.delegate = new HashSet<>(groups.delegate);
    }

    public Groups(List<GroupData> groups) {
        this.delegate = new HashSet<>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdd(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups withOut(GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
