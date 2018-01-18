package DAO;
import models.Animal;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 1/17/18.
 */
public class Sql2oUserDAO implements UserDAO {

    private final Sql2o sql2o;

    public Sql2oUserDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (name, phone, breedPreference, typePreference) VALUES (:name, :phone, :breedPreference, :typePreference)"; //raw sql
        try (Connection con = sql2o.open()) { //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .bind(user)
                    .addParameter("name", user.getName())
                    .addParameter("phone", user.getPhone())
                    .addParameter("breedPreference", user.getBreedPreference())
                    .addParameter("typePreference", user.getTypePreference())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("PHONE", "phone")
                    .addColumnMapping("BREEDPREFERENCE", "breedPreference")
                    .addColumnMapping("TYPEPREFERENCE", "typePreference")
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            user.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<User> findByBreedPreference(String breedPreference) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE breedPreference = :breedPreference")
                    .addParameter("breedPreference", breedPreference)
                    .executeAndFetch(User.class);
        }
    }
}