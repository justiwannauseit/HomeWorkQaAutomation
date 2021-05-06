package ru.fintech.qa.homework.utils.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.fintech.qa.homework.utils.entity.BaseEntity;

import javax.persistence.PersistenceException;
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
        return session.createNativeQuery("SELECT count(*) FROM " + tableName).uniqueResult().toString();
    }

    public static void insert(final BaseEntity entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    /**
     * @param entity сущность из таблицы
     * @param size   размер до какого id хоти проверять
     * @return результат проверки
     */
    public static boolean isNotInsertBaseEntityForId(final BaseEntity entity, final int size) {
        int countCheck = 0;
        if (size <= 0) {
            System.out.println("Размер проверяемого индекса меньше или равен 0");
            return false;
        }

        for (int i = 1; i <= size; i++) {
            entity.setId(i);
            try {
                insert(entity);
            } catch (PersistenceException exception) {
                System.out.println("В таблицу нельзя добавить строку с индексом от 1 до " + size + " включительно");
                countCheck++;
            }
        }
        return countCheck == size;
    }

    public static boolean isNameNullNoCommit(final BaseEntity entity) {
        boolean isResultTest = false;
        try {
            insert(entity);
        } catch (PersistenceException exception) {
            System.out.println("В таблицу public.workman нельзя добавить строку с name = null");
            isResultTest = true;
        }
        return isResultTest;
    }

    public static List<String> createListColumnFromTable(final String nameTable, final String nameColumn) {
        return (List<String>) session.createNativeQuery("SELECT " + nameColumn + " FROM " + nameTable).getResultList();
    }
}
