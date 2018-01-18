package models;

/**
 * Created by Guest on 1/17/18.
 */

import java.time.LocalDateTime;
import java.util.*;

public class Animal {
    private String name;
    private String gender;
    private String dateAdmitted;
    private String type;
    private String breed;
    private int id;
    private int customerId;


    public Animal(String name, String gender, String type, String breed, String dateAdmitted) {
        this.name = name;
        this.gender = gender;
        this.dateAdmitted = dateAdmitted;
        this.type = type;
        this.breed = breed;

    }

    // GETTER ------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateAdmitted() {
        return dateAdmitted;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    // SETTER ------------------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateAdmitted(String dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setId(int id) {
        this.id = id;

    }

    // EUQALS & HASH CODE ------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (id != animal.id) return false;
        if (customerId != animal.customerId) return false;
        if (!name.equals(animal.name)) return false;
        if (!gender.equals(animal.gender)) return false;
        if (!dateAdmitted.equals(animal.dateAdmitted)) return false;
        if (!type.equals(animal.type)) return false;
        return breed.equals(animal.breed);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + dateAdmitted.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + breed.hashCode();
        result = 31 * result + id;
        result = 31 * result + customerId;
        return result;
    }

}




