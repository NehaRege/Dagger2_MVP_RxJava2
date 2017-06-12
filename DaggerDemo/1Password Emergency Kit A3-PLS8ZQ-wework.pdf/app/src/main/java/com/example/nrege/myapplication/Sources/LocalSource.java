package com.example.nrege.myapplication.Sources;

import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Repo.Repo;

import java.util.ArrayList;

/**
 * Created by nrege on 6/12/17.
 */

public interface LocalSource {

    void saveUserListToSharedPrefs(ArrayList<User> allUsers);

    void saveSingleUserToSharedPrefs(User user);

    ArrayList<User> getUserListFromSharedPrefs();

    User getSingleUserFromSharedPrefs();

}
