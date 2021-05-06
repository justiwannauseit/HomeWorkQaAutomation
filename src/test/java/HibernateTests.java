import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.entity.Animal;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.entity.Place;
import ru.fintech.qa.homework.utils.entity.Workman;
import ru.fintech.qa.homework.utils.hibernate.HibernateSessionCreator;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class HibernateTests {

    private static Session session;

    @BeforeAll
    public static void createDataBase() {
        BeforeUtils.createData();
        SessionFactory sessionFactory = HibernateSessionCreator.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Test
    public void testPublicAnimalHave10line() {
        String result = session.createNativeQuery("SELECT count(*) FROM public.animal").uniqueResult().toString();
        Assertions.assertEquals("10", result);
    }

    @Test
    public void testDoNotInsert() {

        Animal animal = new Animal();
        boolean isResultTest;

        for (int i = 1; i <= 10; i++) {
            animal.setId(i);
            isResultTest = false;
            try {
                session.beginTransaction();
                session.save(animal);
                session.getTransaction().commit();
            } catch (PersistenceException exception) {
                System.out.println("В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно");
                isResultTest = true;
            }
            Assertions.assertTrue(isResultTest);
        }
    }

    @Test
    public void testWorkmanName() {
        Workman workman = new Workman();
        boolean isResultTest = false;
        try {
            workman.setName(null);
            session.beginTransaction();
            session.save(workman);
            session.getTransaction().commit();

        } catch (PersistenceException exception) {
            System.out.println("В таблицу public.workman нельзя добавить строку с name = null");
            isResultTest = true;
        }

        Assertions.assertTrue(isResultTest);

    }

    @Test
    public void tesPlaceHas6Lines(){
        Place place = new Place();
        place.setId(6);
        place.setName("TestName");

        session.beginTransaction();
        session.save(place);
        session.getTransaction().commit();

        String result = session.createNativeQuery("SELECT count(*) FROM public.places").uniqueResult().toString();
        Assertions.assertEquals("6", result);
    }

    @Test
    public void zooHas3Line(){
        List<String> expected = new ArrayList<String>(){{
            add("Центральный");
            add("Западный");
            add("Северный");
        }};
        List<String> result = session.createNativeQuery("SELECT \"name\" FROM public.zoo ").getResultList();
        Assertions.assertEquals(3,result.size());

        for (String name: expected){
            Assertions.assertTrue(result.contains(name));
        }
    }

    @AfterAll
    public static void closeSession() {
        session.close();
    }

}
