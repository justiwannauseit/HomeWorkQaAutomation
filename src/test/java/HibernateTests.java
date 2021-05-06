import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.entity.Animal;
import ru.fintech.qa.homework.utils.entity.Place;
import ru.fintech.qa.homework.utils.entity.Workman;
import ru.fintech.qa.homework.utils.hibernate.DbClient;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class HibernateTests {

    @BeforeAll
    public static void createDataBase() {
        BeforeUtils.createData();
    }

    @Test
    public void testPublicAnimalHave10line() {
        String result = DbClient.countLineInTable("public.animal");
        Assertions.assertEquals("10", result);
    }

    @Test
    public void testDoNotInsertAnimalTo10() {
        int countCheck = 0;
        Animal animal = new Animal();

        for (int i = 1; i <= 10; i++) {
            animal.setId(i);
            try {
                DbClient.insert(animal);
            } catch (PersistenceException exception) {
                System.out.println("В таблицу нельзя добавить строку с индексом от 1 до 10 включительно");
                countCheck++;
            }
        }
        Assertions.assertEquals(10, countCheck);
    }

    @Test
    public void testWorkmanName() {
        Workman workman = new Workman();
        workman.setName(null);
        boolean isResultTest = false;
        try {
            DbClient.insert(workman);
        } catch (PersistenceException exception) {
            System.out.println("В таблицу public.workman нельзя добавить строку с name = null");
            isResultTest = true;
        }
        Assertions.assertTrue(isResultTest);
    }

    @Test
    public void tesPlaceHas6Lines() {
        Place place = new Place();
        place.setId(6);
        place.setName("TestName");
        DbClient.insert(place);
        String result = DbClient.countLineInTable("public.places");
        Assertions.assertEquals("6", result);
    }

    @Test
    public void zooHas3Line() {
        List<String> expected = new ArrayList<String>() {{
            add("Центральный");
            add("Западный");
            add("Северный");
        }};

        List<String> result = DbClient.createListColumnFromTable("public.zoo", "\"name\"");

        Assertions.assertEquals(3, result.size());

        for (String name : expected) {
            Assertions.assertTrue(result.contains(name));
        }
    }
}
