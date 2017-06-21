package com.example.nrege.myapplication.Repo;

import com.example.nrege.myapplication.Models.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nrege on 6/9/17.
 */

public interface Repo {

    Observable<ArrayList<User>> getUserList();

    Observable<User> getSingleUser(String id);

    void saveUserListToSharedPrefs(ArrayList<User> allUsers);

    void saveSingleUserToSharedPrefs(User user);


}
