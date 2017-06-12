package com.example.nrege.myapplication.List;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.nrege.myapplication.API.APIService;
import com.example.nrege.myapplication.Models.User;
import com.google.gson.Gson;

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

    Retrofit retrofit;
    SharedPreferences sharedPreferences;
    NetworkInfo networkInfo;
    
    User user;

    private ArrayList<User> allUsers = new ArrayList<>();

    public RepoImpl(Retrofit retrofit, SharedPreferences sharedPreferences, NetworkInfo networkInfo) {
        this.retrofit = retrofit;
        this.sharedPreferences = sharedPreferences;
        this.networkInfo = networkInfo;
    }

    @Override
    public void getUsersFromRetrofit(final OnCallbackFinished callbackFinished) {
        if (networkInfo != null && networkInfo.isConnected()) {
            APIService service = retrofit.create(APIService.class);
            Log.d(TAG, "getData: retrofit = " + retrofit);

            Call<ArrayList<User>> getAllUsers = service.getUsers();

            getAllUsers.enqueue(new Callback<ArrayList<User>>() {

                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    Log.d(TAG, "onResponse: "+response.body());
                    
                    allUsers = response.body();


                    callbackFinished.onSuccess(allUsers);

//                    listView.setData(allUsers);
                }

                @Override
                public void onFailure(Call<ArrayList<User>> call, Throwable t) {
//                    listView.showRetrofitFailureToast();

                    callbackFinished.onFailure(t);
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

        } else {
            Log.d(TAG, "getUsersFromRetrofit: No network");
        }
        
    }

    @Override
    public void saveUserToSharedPrefs(User user) {
        sharedPreferences.edit().putString("user", new Gson().toJson(user)).apply();
    }

    @Override
    public User getUserFromSharedPrefs() {
        
        String jsonUser = sharedPreferences.getString("user",null);
        user = new Gson().fromJson(jsonUser,User.class);
        
        return user;
        
    }

}
