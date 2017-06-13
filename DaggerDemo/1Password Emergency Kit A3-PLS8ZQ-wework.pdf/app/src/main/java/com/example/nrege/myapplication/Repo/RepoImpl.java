package com.example.nrege.myapplication.Repo;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nrege.myapplication.API.APIService;
import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Sources.LocalSource;
import com.example.nrege.myapplication.Sources.RemoteSource;
import com.example.nrege.myapplication.Sources.RemoteSourceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/9/17.
 */

public class RepoImpl implements Repo {

    private static String TAG = "RepoImpl";

    @Nullable
    private NetworkInfo networkInfo;

    RemoteSource remoteSource;
    LocalSource localSource;

    String id;

    public RepoImpl(@Nullable NetworkInfo networkInfo, RemoteSource remoteSource, LocalSource localSource) {
        this.networkInfo = networkInfo;
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }


    @Override
    public void getUserList(final OnCallbackFinishedForUserList callbackFinished) {

        if (networkInfo != null && networkInfo.isConnected()) {

            remoteSource.getUserListFromRetrofit(new RemoteSource.OnCallbackFinishedForUserList() {
                @Override
                public void onSuccess(ArrayList<User> users, String s) {
                    localSource.saveUserListToSharedPrefs(users);
                    callbackFinished.onSuccess(users,s);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    callbackFinished.onFailure(throwable);
                }
            });

        } else {
            callbackFinished.onSuccess(localSource.getUserListFromSharedPrefs(),"shared_prefs");
        }

    }

    @Override
    public void saveUserListToSharedPrefs(ArrayList<User> allUsers){
        localSource.saveUserListToSharedPrefs(allUsers);
    }

    @Override
    public void getSingleUser(String id, final OnCallbackFinishedForSingleUser callbackFinished) {

        if (networkInfo != null && networkInfo.isConnected()) {
            
            remoteSource.getSingleUserFromRetrofit(id, new RemoteSource.OnCallbackFinishedForSingleUser() {
                @Override
                public void onSuccess(User user, String s) {
                    localSource.saveSingleUserToSharedPrefs(user);
                    callbackFinished.onSuccess(user,s);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    callbackFinished.onFailure(throwable);
                }
            });

        } else {
            callbackFinished.onSuccess(localSource.getSingleUserFromSharedPrefs(),"shared_prefs");
        }
    }

    @Override
    public void saveSingleUserToSharedPrefs(User user) {
        localSource.saveSingleUserToSharedPrefs(user);
    }

}
