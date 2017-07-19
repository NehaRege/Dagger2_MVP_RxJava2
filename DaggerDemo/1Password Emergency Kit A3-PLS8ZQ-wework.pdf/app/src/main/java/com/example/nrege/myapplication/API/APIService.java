package com.example.nrege.myapplication.API;

import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nrege on 6/2/17.
 */

public interface APIService {

    @GET("/users")
    Call<ArrayList<User>> getUsers();

    @GET("/users/{id}")
    Call<User> getSingleUser(@Path("id") String id);

}
