package ru.chibisov.aft.addressbook.appmanager.db;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.chibisov.aft.addressbook.model.ContactData;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.util.List;

public class DbHelper {

    private SessionFactory sessionFactory;

    public DbHelper() {
        this.sessionFactory = new SessionUtil().getSessionFactory();
    }

    public List<GroupData> groups() {
        Session session = null;
        List<GroupData> groups;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            groups = session.createQuery("from GroupData").list();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return groups;
    }

    public List<ContactData> contacts() {
        Session session = null;
        List<ContactData> contacts;
        try {
            session = sessionFactory.openSession();
            session.setCacheMode(CacheMode.IGNORE);
            session.beginTransaction();
            contacts = session.createQuery("from ContactData").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return contacts;
    }
}
