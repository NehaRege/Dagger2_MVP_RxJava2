package com.example.nrege.myapplication.List;

import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;

/**
 * Created by nrege on 6/9/17.
 */

public interface Repo {

    interface OnCallbackFinished {
        void onSuccess(ArrayList<User> users);

        void onFailure(Throwable throwable);
    }

    void getUsersFromRetrofit(OnCallbackFinished callbackFinished);

    void saveUserToSharedPrefs(int position, User user);

    User getUserFromSharedPrefs(int position);








}
