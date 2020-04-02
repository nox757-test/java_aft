package ru.chibisov.aft.addressbook.appmanager.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.chibisov.aft.addressbook.model.GroupData;

import java.io.File;

public class SessionUtil {

    private SessionFactory sessionFactory;
    private final Configuration configuration;

    public SessionUtil() {
        this.configuration = new Configuration().configure(new File("src/test/resources/properties/hibernate.cfg.xml")) ;

    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception ex) {
                throw ex;
            }
        }
        return sessionFactory;
    }

    public void closeSessionFactory() {
        if (sessionFactory == null) {
            return;
        }
        try {
            sessionFactory.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

}
