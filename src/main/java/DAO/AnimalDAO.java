package DAO;

import models.*;

import java.util.List;

/**
 * Created by Guest on 1/17/18.
 */
public interface AnimalDAO {

    void add (Animal animal);

    List<Animal> getAll();

    Animal findById(int id);

    List<Animal> findByBreed(String breed);

    List<Animal> findByType(String type);

    List<Animal> sortByName();

    List<Animal> sortByDateAdmitted();

    void setOwner(int id, int customerId);
}