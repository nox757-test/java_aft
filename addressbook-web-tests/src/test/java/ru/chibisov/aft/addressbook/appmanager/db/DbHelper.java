package ru.chibisov.aft.addressbook.appmanager.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.annotations.Test;
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
//            groups = session.createCriteria(GroupData.class).list();
//            groups = session.createSQLQuery("select * from group_list", GroupData.class).list();
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
}
