import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.entity.Animal;
import ru.fintech.qa.homework.utils.entity.Place;
import ru.fintech.qa.homework.utils.entity.Workman;
import ru.fintech.qa.homework.utils.hibernate.DbClient;

import java.util.ArrayList;
import java.util.List;

public class HibernateTests {

    @BeforeAll
    public static void createDataBase() {
        BeforeUtils.createData();
    }

    @Test
    public void testPublicAnimalHave10line() {
        DbClient.createSession();
        String result = DbClient.countLineInTable("public.animal");
        Assertions.assertEquals("10", result);
        DbClient.closeSession();
    }

    @Test
    public void testDoNotInsertAnimalTo10() {
        DbClient.createSession();
        Animal animal = new Animal();
        Assertions.assertTrue(DbClient.isNotInsertBaseEntityForId(animal, 10));
        DbClient.closeSession();
    }

    @Test
    public void testWorkmanName() {
        DbClient.createSession();
        Workman workman = new Workman();
        workman.setName(null);
        Assertions.assertTrue(DbClient.isNameNullNoCommit(workman));
        DbClient.closeSession();
    }

    @Test
    public void tesPlaceHas6Lines() {
        DbClient.createSession();
        Place place = new Place();
        place.setId(6);
        place.setName("TestName");
        DbClient.insert(place);
        String result = DbClient.countLineInTable("public.places");
        Assertions.assertEquals("6", result);
        DbClient.closeSession();
    }

    @Test
    public void zooHas3Line() {
        DbClient.createSession();
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
        DbClient.closeSession();
    }
}
