package DAO;

import models.Animal;
import models.User;
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
public class Sql2oUserDAOTest {

    private Sql2oUserDAO animalDAO; //ignore me for now. We'll create this soon.
    private Sql2oUserDAO userDAO; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDAO = new Sql2oUserDAO(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingUsersWorks() throws Exception {
        User testUser = new User("Bruce Lee", 1234567, "dog", "pitbull");
        int originalId = testUser.getId();
        userDAO.add(testUser);
        assertNotEquals(originalId, testUser.getId());
    }

    @Test
    public void canFindByBreedPreference() throws Exception {
        User testUserOne = new User("Bruce Lee", 1234567, "dog", "pitbull");
        User testUserTwo = new User("Bruce Lee", 1234567, "dog", "beagle");
        userDAO.add(testUserOne);
        userDAO.add(testUserTwo);
        assertEquals(1, userDAO.findByBreedPreference("pitbull").size());
    }
}