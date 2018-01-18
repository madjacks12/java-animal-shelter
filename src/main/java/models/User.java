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

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getTypePreference() {
        return typePreference;
    }

    public String getBreedPreference() {
        return breedPreference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (phone != user.phone) return false;
        if (id != user.id) return false;
        if (!name.equals(user.name)) return false;
        if (typePreference != null ? !typePreference.equals(user.typePreference) : user.typePreference != null)
            return false;
        return breedPreference != null ? breedPreference.equals(user.breedPreference) : user.breedPreference == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phone;
        result = 31 * result + (typePreference != null ? typePreference.hashCode() : 0);
        result = 31 * result + (breedPreference != null ? breedPreference.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
