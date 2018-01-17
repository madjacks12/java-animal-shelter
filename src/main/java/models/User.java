package models;

/**
 * Created by Guest on 1/17/18.
 */
public class User {
    private String name;
    private int phone;
    private String typePreference;
    private String breedPreference;
    private int id;

    public User(String name, int phone, String typePreference, String breedPreference) {
        this.name = name;
        this.phone = phone;
        this.typePreference = typePreference;
        this.breedPreference = breedPreference;
    }

}
