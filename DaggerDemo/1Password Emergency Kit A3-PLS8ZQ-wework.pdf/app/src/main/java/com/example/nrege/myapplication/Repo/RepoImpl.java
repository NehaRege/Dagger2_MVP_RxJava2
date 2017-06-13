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

public class RepoImpl implements Repo, RemoteSource.OnCallbackFinished, RemoteSource.OnCallbackFinishedForSingleUser {

    private static String TAG = "RepoImpl";

    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;
    @Nullable
    private NetworkInfo networkInfo;

    RemoteSource remoteSource;
    LocalSource localSource;

    private User singleUser;

    String id;

    private ArrayList<User> userList = new ArrayList<>();

    public RepoImpl(@Nullable NetworkInfo networkInfo, RemoteSource remoteSource, LocalSource localSource) {
        this.networkInfo = networkInfo;
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }


    /*
    *
    *       User List
    *
    *
    * */


    @Override
    public void getUserListFromRetrofit(final OnCallbackFinished callbackFinished) {
//        if(userList!=null){
//            callbackFinished.onSuccess(userList);
//        }

        if (networkInfo != null && networkInfo.isConnected()) {

            APIService service = retrofit.create(APIService.class);
            Log.d(TAG, "getData: retrofit = " + retrofit);

            Call<ArrayList<User>> getAllUsers = service.getUsers();

            getAllUsers.enqueue(new Callback<ArrayList<User>>() {

                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    Log.d(TAG, "onResponse: "+response.body());

                    Log.d(TAG, "onResponse: data from retrofit");
                    
                    userList = response.body();

                    saveUserListToSharedPrefs(userList);

                    callbackFinished.onSuccess(userList, "retrofit");

                }

                @Override
                public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                    callbackFinished.onFailure(t);

                    Log.d(TAG, "onFailure: " + t.getMessage());

                }
            });

        } else {

            Log.d(TAG, "getUsersFromRetrofit: No network");

            callbackFinished.onSuccess(getUserListFromSharedPrefs(), "shared_prefs");

        }
    }

    @Override
    public void saveUserListToSharedPrefs(ArrayList<User> allUsers){

        Log.d(TAG, "saveUserListToSharedPrefs: all users = "+allUsers);

        Type listType = new TypeToken<ArrayList<User>>() {}.getType();

        sharedPreferences.edit().putString("user_list", new Gson().toJson(allUsers,listType)).apply();

    }


    private ArrayList<User> getUserListFromSharedPrefs(){

        Log.d(TAG, "getUserListFromSharedPrefs: ");
        String jsonUser = sharedPreferences.getString("user_list",null);

        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> userList = new Gson().fromJson(jsonUser,type);

        Log.d(TAG, "getUserListFromSharedPrefs: userList = "+userList);
        return userList;

    }

    /*
    *
    *       Single User
    *
    * */

    @Override
    public void position(int position) {
        id = Integer.toString(position);
    }

    @Override
    public void getSingleUser(String id, final OnCallbackFinishedForSingleUser callbackFinished) {

        if (networkInfo != null && networkInfo.isConnected()) {

            remoteSource.getSingleUserFromRetrofit(id,this);

        } else {
            callbackFinished.onSuccess(localSource.getSingleUserFromSharedPrefs(),"shared_prefs");
        }

    }

    @Override
    public void saveSingleUserToSharedPrefs(User user) {

        localSource.saveSingleUserToSharedPrefs(user);

    }


//    @Override
//    public User getSingleUserFromSharedPrefs() {
//        String jsonUser = sharedPreferences.getString("user",null);
//        singleUser = new Gson().fromJson(jsonUser,User.class);
//        return singleUser;
//    }


    @Override
    public void onSuccess(ArrayList<User> users, String s) {
        localSource.saveUserListToSharedPrefs(users);
    }

    @Override
    public void onSuccess(User user, String s) {
        localSource.saveSingleUserToSharedPrefs(user);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.d(TAG, "onFailure: ");
    }
}
