package ru.fintech.qa.homework.utils.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DbClient {

    private static Session session;

    public static void createSession() {
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void closeSession() {
        session.close();
    }

    public static String countLineInTable(final String tableName) {
        createSession();
        String result = session.createNativeQuery("SELECT count(*) FROM " + tableName).uniqueResult().toString();
        closeSession();
        return result;
    }

    public static <T> void insert(final T entity) {
        createSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        closeSession();
    }

    public static List<String> createListColumnFromTable(final String nameTable, final String nameColumn) {
        createSession();
        List<String> result = session.createNativeQuery("SELECT " + nameColumn + " FROM " + nameTable).getResultList();
        closeSession();
        return result;
    }
}
