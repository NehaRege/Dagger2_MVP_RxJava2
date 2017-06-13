package com.example.nrege.myapplication.Sources;

import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Repo.Repo;

import java.util.ArrayList;

/**
 * Created by nrege on 6/12/17.
 */

public interface RemoteSource {

    interface OnCallbackFinishedForUserList {

        void onSuccess(ArrayList<User> users, String s);

        void onFailure(Throwable throwable);
    }

    interface OnCallbackFinishedForSingleUser {

        void onSuccess(User user, String s);

        void onFailure(Throwable throwable);

    }

    void getUserListFromRetrofit(OnCallbackFinishedForUserList callbackFinished);

    void getSingleUserFromRetrofit(String id, OnCallbackFinishedForSingleUser callbackFinished);


}
