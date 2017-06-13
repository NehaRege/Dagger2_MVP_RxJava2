package com.example.nrege.myapplication.Repo;

import com.example.nrege.myapplication.Models.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by nrege on 6/9/17.
 */

public interface Repo {

    interface OnCallbackFinished<T> {
        void onSuccess(T data, String s);
        void onFailure(Throwable throwable);
    }

    void getUserList(OnCallbackFinished<ArrayList<User>> callbackFinished);

    void getSingleUser(String id, OnCallbackFinished<User> callbackFinished);

    void saveUserListToSharedPrefs(ArrayList<User> allUsers);

    void saveSingleUserToSharedPrefs(User user);

}
