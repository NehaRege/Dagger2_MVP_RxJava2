package com.example.nrege.myapplication.Repo;

import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;

/**
 * Created by nrege on 6/9/17.
 */

public interface Repo {

    interface OnCallbackFinished {
        void onSuccess(ArrayList<User> users, String s);

        void onFailure(Throwable throwable);
    }

    interface OnCallbackFinishedForSingleUser {
        void onSuccess(User user, String s);

        void onFailure(Throwable throwable);
    }

    void getUserListFromRetrofit(OnCallbackFinished callbackFinished);

    void getSingleUser(String id, OnCallbackFinishedForSingleUser callbackFinished);

    void saveSingleUserToSharedPrefs(User user);

    void saveUserListToSharedPrefs(ArrayList<User> allUsers);

    void position(int position);

    //todo rename to getuser()


    User getSingleUserFromSharedPrefs();











}
