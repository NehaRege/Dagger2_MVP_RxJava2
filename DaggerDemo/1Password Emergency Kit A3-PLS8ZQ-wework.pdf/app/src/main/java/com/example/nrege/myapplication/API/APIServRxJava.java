package com.example.nrege.myapplication.API;

import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nrege on 6/21/17.
 */

public interface APIServRxJava {

    @GET("/users")
    Observable<ArrayList<User>> getUsers();

    @GET("/users/{id}")
    Observable<User> getSingleUser(@Path("id") String id);

}
