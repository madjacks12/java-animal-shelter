package DAO;

import models.Animal;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import org.sql2o.Connection;

import java.util.List;


/**
 * Created by Guest on 1/17/18.
 */
public class Sql2oAnimalDAO implements AnimalDAO {
    private final Sql2o sql2o;

    public Sql2oAnimalDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Animal animal) {
        String sql = "INSERT INTO animals (name, gender, type, breed, customerId, dateAdmitted) " +
                "VALUES (:name, :gender, :type, :breed, :customerId, :dateAdmitted)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .bind(animal)
                    .addParameter("name", animal.getName())
                    .addParameter("gender", animal.getGender())
                    .addParameter("type", animal.getType())
                    .addParameter("breed", animal.getBreed())
                    .addParameter("customerId", animal.getCustomerId())
                    .addParameter("dateAdmitted", animal.getDateAdmitted())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("GENDER", "gender")
                    .addColumnMapping("TYPE", "type")
                    .addColumnMapping("BREED", "breed")
                    .addColumnMapping("CUSTOMERID", "customerId")
                    .addColumnMapping("DATEADMITTED", "dateAdmitted")
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            animal.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }

    }

    @Override
    public List<Animal> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals")
                    .executeAndFetch(Animal.class);
        }
    }

    @Override
    public Animal findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
        }
    }

    @Override
    public List<Animal> findByBreed(String breed) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals WHERE breed = :breed")
                    .addParameter("breed", breed)
                    .executeAndFetch(Animal.class);
        }
    }
    @Override
    public List<Animal> findByType(String type) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals WHERE type = :type")
                    .addParameter("type", type)
                    .executeAndFetch(Animal.class);
        }
    }
    @Override
    public List<Animal> sortByName() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals ORDER BY name ASC")
                    .executeAndFetch(Animal.class);
        }
    }
    @Override
    public List<Animal> sortByDateAdmitted() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals ORDER BY dateAdmitted ASC")
                    .executeAndFetch(Animal.class);
        }
    }

    @Override
    public void setOwner(int id, int customerId) {
        String sql = "UPDATE animals SET customerId = :customerId WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("customerId", customerId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}

