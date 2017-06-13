package com.example.nrege.myapplication.Sources;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.nrege.myapplication.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by nrege on 6/12/17.
 */

public class LocalSourceImpl implements LocalSource {

    private static String TAG = "LocalSourceImpl";

    SharedPreferences sharedPreferences;

    private User singleUser;

    public LocalSourceImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveUserListToSharedPrefs(ArrayList<User> allUsers) {
        Log.d(TAG, "saveUserListToSharedPrefs: all users = "+allUsers);

        Type listType = new TypeToken<ArrayList<User>>() {}.getType();

        sharedPreferences.edit().putString("user_list", new Gson().toJson(allUsers,listType)).apply();

    }

    @Override
    public void saveSingleUserToSharedPrefs(User user) {
        sharedPreferences.edit().putString("user", new Gson().toJson(user)).apply();
    }

    @Override
    public ArrayList<User> getUserListFromSharedPrefs() {
        Log.d(TAG, "getUserListFromSharedPrefs: ");
        String jsonUser = sharedPreferences.getString("user_list",null);

        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> userList = new Gson().fromJson(jsonUser,type);

        Log.d(TAG, "getUserListFromSharedPrefs: userList = "+userList);
        return userList;
    }

    @Override
    public User getSingleUserFromSharedPrefs() {
        String jsonUser = sharedPreferences.getString("user",null);
        singleUser = new Gson().fromJson(jsonUser,User.class);
        return singleUser;
    }
}
