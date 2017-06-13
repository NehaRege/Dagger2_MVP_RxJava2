package com.example.nrege.myapplication.Repo;

import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Sources.LocalSource;
import com.example.nrege.myapplication.Sources.RemoteSource;
import java.util.ArrayList;

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
    public void getUserList(final OnCallbackFinished<ArrayList<User>> callbackFinished) {

        if (networkInfo != null && networkInfo.isConnected()) {

            remoteSource.getUserListFromRetrofit(new RemoteSource.OnCallbackFinished<ArrayList<User>>() {
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
    public void getSingleUser(String id, final OnCallbackFinished<User> callbackFinished) {

        if (networkInfo != null && networkInfo.isConnected()) {

            remoteSource.getSingleUserFromRetrofit(id, new RemoteSource.OnCallbackFinished<User>() {
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
    public void saveUserListToSharedPrefs(ArrayList<User> allUsers){
        localSource.saveUserListToSharedPrefs(allUsers);
    }

    @Override
    public void saveSingleUserToSharedPrefs(User user) {
        localSource.saveSingleUserToSharedPrefs(user);
    }

}
