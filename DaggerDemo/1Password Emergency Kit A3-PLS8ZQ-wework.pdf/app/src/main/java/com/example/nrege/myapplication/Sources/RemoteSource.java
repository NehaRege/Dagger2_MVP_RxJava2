package com.example.nrege.myapplication.Sources;

import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Repo.Repo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nrege on 6/12/17.
 */

public interface RemoteSource {

    Observable<ArrayList<User>> getUserListFromRetrofit();

    Observable<User> getSingleUserFromRetrofit(String id);


}
