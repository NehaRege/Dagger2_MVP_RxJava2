package com.example.nrege.myapplication.Sources;

import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Repo.Repo;

import java.util.ArrayList;

/**
 * Created by nrege on 6/12/17.
 */

public interface RemoteSource {

    interface OnCallbackFinished<T> {
        void onSuccess(T data, String s);
        void onFailure(Throwable throwable);
    }

    void getUserListFromRetrofit(OnCallbackFinished<ArrayList<User>> callbackFinished);

    void getSingleUserFromRetrofit(String id, OnCallbackFinished<User> callbackFinished);



}
