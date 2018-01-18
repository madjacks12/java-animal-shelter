package DAO;

import models.Animal;
import models.User;

import java.util.List;

/**
 * Created by Guest on 1/17/18.
 */
public interface UserDAO {
    void add(User user);

    List<User> findByBreedPreference(String breed);
}
