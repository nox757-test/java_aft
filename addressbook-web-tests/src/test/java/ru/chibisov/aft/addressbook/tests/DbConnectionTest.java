package ru.chibisov.aft.addressbook.tests;

import org.testng.annotations.Test;
import ru.chibisov.aft.addressbook.appmanager.db.DbHelper;

import java.io.IOException;

public class DbConnectionTest {

    public static void main(String[] args) throws IOException {

        DbHelper dbHelper = new DbHelper();
        System.out.println(dbHelper.groups());

    }
}