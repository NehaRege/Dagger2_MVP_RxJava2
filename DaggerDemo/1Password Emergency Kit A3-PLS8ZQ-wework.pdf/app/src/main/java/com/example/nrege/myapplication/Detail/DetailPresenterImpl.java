package com.example.nrege.myapplication.Detail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nrege.myapplication.Dagger.MyApplication;
import com.example.nrege.myapplication.List.ListPresenterImpl;
import com.example.nrege.myapplication.List.Repo;
import com.example.nrege.myapplication.Models.User;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by nrege on 6/7/17.
 */

public class DetailPresenterImpl implements DetailPresenter {

    DetailView detailView;
    Repo repo;

    User user;

    public DetailPresenterImpl(DetailView detailView, Repo repo) {
        this.detailView = detailView;
        this.repo = repo;

//        this.sharedPreferences = sharedPreferences;

    }

    @Override
    public void init(){

        getDataFromSharedPref();

    }

    @Override
    public void getDataFromSharedPref() {

        user = repo.getUserFromSharedPrefs();

        detailView.setText(
                user.getEmail(),
                user.getPhone(),
                user.getName(),
                user.getAddress().getStreet()+", "+user.getAddress().getCity()+" "+user.getAddress().getZipcode(),
                user.getCompany().getName(),
                user.getWebsite()
        );

    }

}
