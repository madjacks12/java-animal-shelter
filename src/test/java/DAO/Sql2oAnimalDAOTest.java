package DAO;

import models.Animal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Guest on 1/17/18.
 */
public class Sql2oAnimalDAOTest {

    private Sql2oAnimalDAO animalDAO; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        animalDAO = new Sql2oAnimalDAO(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Animal testAnimal = new Animal("Dave", "Male", "Dog", "Husky", 1, "01/17/2018");
        int originalAnimalId = testAnimal.getId();
        animalDAO.add(testAnimal);
        assertNotEquals(originalAnimalId, testAnimal.getId());
    }

    @Test
    public void existingAnimalsCanBeFoundById() throws Exception {
        Animal animal = new Animal("Dave", "Male", "Dog", "Husky", 1, "01/17/2018");
        animalDAO.add(animal);
        Animal foundAnimal = animalDAO.findById(animal.getId());
        assertEquals(animal, foundAnimal);
    }

    @Test
    public void addedAnimalsCanBeFoundByGetAll() throws Exception {
        Animal animal = new Animal("Dave", "Male", "Dog", "Husky", 1, "01/17/2018");
        animalDAO.add(animal);
        assertEquals(1, animalDAO.getAll().size());
    }

    @Test
    public void canFindByBreed() throws Exception {
        Animal animalOne = new Animal("Dave", "Male", "Dog", "Husky", 1, "01/17/2018");
        Animal animalTwo = new Animal("Jeff", "Female", "Cat", "Calico", 1, "06/09/1969");
        animalDAO.add(animalOne);
        animalDAO.add(animalTwo);
        assertEquals(1, animalDAO.findByBreed("Husky").size());
    }

    @Test
    public void canFindByType() throws Exception {
        Animal animalOne = new Animal("Dave", "Male", "Dog", "Husky", 1, "01/17/2018");
        Animal animalTwo = new Animal("Jeff", "Female", "Cat", "Calico", 1, "06/09/1969");
        animalDAO.add(animalOne);
        animalDAO.add(animalTwo);
        assertEquals(1, animalDAO.findByType("Cat").size());
    }

    @Test
    public void sortByName() throws Exception {
        Animal animalOne = new Animal("Dave", "Male", "Dog", "Husky", 1, "01/17/2018");
        Animal animalTwo = new Animal("Jeff", "Female", "Cat", "Calico", 1, "06/09/1969");
        Animal animalThree = new Animal("Amanda", "Female", "Cat", "Calico", 1, "06/09/1969");
        animalDAO.add(animalOne);
        animalDAO.add(animalTwo);
        animalDAO.add(animalThree);
        assertEquals("Amanda", animalDAO.sortByName().get(0).getName());
        assertEquals("Dave", animalDAO.sortByName().get(1).getName());
        assertEquals("Jeff", animalDAO.sortByName().get(2).getName());
    }

    @Test
    public void sortByDateAdmitted() throws Exception {
        Animal animalOne = new Animal("Dave", "Male", "Dog", "Husky", 1, "2018-04-20");
        Animal animalTwo = new Animal("Jeff", "Female", "Cat", "Calico", 1, "1969-06-09");
        Animal animalThree = new Animal("Amanda", "Female", "Cat", "Calico", 1, "2001-06-09");
        animalDAO.add(animalOne);
        animalDAO.add(animalTwo);
        animalDAO.add(animalThree);
        assertEquals("Jeff", animalDAO.sortByDateAdmitted().get(0).getName());
        assertEquals("Amanda", animalDAO.sortByDateAdmitted().get(1).getName());
        assertEquals("Dave", animalDAO.sortByDateAdmitted().get(2).getName());
    }

}
