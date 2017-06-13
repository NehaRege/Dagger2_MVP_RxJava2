package com.example.nrege.myapplication.Sources;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nrege.myapplication.API.APIService;
import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/12/17.
 */

public class RemoteSourceImpl implements RemoteSource {
    private static String TAG = "RemoteSourceImpl";

    private ArrayList<User> userList = new ArrayList<>();

    private User singleUser;

    String id;

    private Retrofit retrofit;

    public RemoteSourceImpl(Retrofit retrofit ) {
        this.retrofit = retrofit;
    }

    @Override
    public void getUserListFromRetrofit(final OnCallbackFinished callbackFinished) {

            APIService service = retrofit.create(APIService.class);

            Call<ArrayList<User>> getAllUsers = service.getUsers();

            getAllUsers.enqueue(new Callback<ArrayList<User>>() {

                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                    userList = response.body();

//                    localSource.saveUserListToSharedPrefs(userList);

                    callbackFinished.onSuccess(userList, "retrofit");

                }

                @Override
                public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                    callbackFinished.onFailure(t);

                }
            });
    }

    @Override
    public void getSingleUserFromRetrofit(String id, final OnCallbackFinishedForSingleUser callbackFinished) {

        APIService service = retrofit.create(APIService.class);

        Call<User> getSingleUser = service.getSingleUser(id);

        getSingleUser.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                singleUser = response.body();

//                localSource.saveSingleUserToSharedPrefs(singleUser);

                callbackFinished.onSuccess(singleUser,"retrofit");

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                callbackFinished.onFailure(t);

            }
        });

    }

}
