package ru.fintech.qa.homework.utils.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.fintech.qa.homework.utils.entity.Animal;
import ru.fintech.qa.homework.utils.entity.Place;
import ru.fintech.qa.homework.utils.entity.Workman;

public class HibernateSessionCreator {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }

        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {

        return new Configuration()
                .configure()
                .addAnnotatedClass(Animal.class)
                .addAnnotatedClass(Workman.class)
                .addAnnotatedClass(Place.class)
                .buildSessionFactory();
    }

}
