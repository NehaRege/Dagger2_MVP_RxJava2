package com.example.nrege.myapplication.Detail;

import android.util.Log;

import com.example.nrege.myapplication.Repo.Repo;
import com.example.nrege.myapplication.Models.User;

/**
 * Created by nrege on 6/7/17.
 */

public class DetailPresenterImpl implements DetailPresenter, Repo.OnCallbackFinishedForSingleUser {

    private static String TAG = "DetailPresenterImpl";


    DetailView detailView;

    Repo repo;

    User user;

    public DetailPresenterImpl(DetailView detailView, Repo repo) {
        this.detailView = detailView;
        this.repo = repo;
    }

    @Override
    public void init(String position){

        Log.d(TAG, "init: ");

        repo.getSingleUser(position,this);


    }

    @Override
    public void onSuccess(User user, String s) {

        if(s.equals("shared_prefs")) {
            detailView.showToast("No internet connection. Retrieving user info from Shared Preferences!");
        } else if(s.equals("retrofit")) {
            detailView.showToast("User info received from Retrofit Call");
        }

        repo.saveSingleUserToSharedPrefs(user);

        detailView.setText(
                user.getEmail(),
                user.getPhone(),
                user.getName(),
                user.getAddress().getStreet()+", "+user.getAddress().getCity()+" "+user.getAddress().getZipcode(),
                user.getCompany().getName(),
                user.getWebsite()
        );

    }

    @Override
    public void onFailure(Throwable throwable) {

        detailView.showToast("Network call failure");

    }
}
