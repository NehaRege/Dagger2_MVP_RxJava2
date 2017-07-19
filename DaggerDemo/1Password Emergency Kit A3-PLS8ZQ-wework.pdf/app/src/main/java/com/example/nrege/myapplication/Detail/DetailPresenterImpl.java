package com.example.nrege.myapplication.Detail;

import android.util.Log;

import com.example.nrege.myapplication.Repo.Repo;
import com.example.nrege.myapplication.Models.User;

import io.reactivex.Observable;

/**
 * Created by nrege on 6/7/17.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private static String TAG = "DetailPresenterImpl";

    DetailView detailView;

    Repo repo;

    public DetailPresenterImpl(DetailView detailView, Repo repo) {
        this.detailView = detailView;
        this.repo = repo;
    }

    @Override
    public void init(String position) {
        Log.d(TAG, "init: ");

        Observable<User> observable = repo.getSingleUser(position);
        observable.subscribe(this::handleResponse, this::handleError);

    }

    private void handleResponse(User user) {

        repo.saveSingleUserToSharedPrefs(user);

        detailView.setText(
                user.getEmail(),
                user.getPhone(),
                user.getName(),
                user.getAddress().getStreet() + ", " + user.getAddress().getCity() + ", " + user.getAddress().getZipcode(),
                user.getCompany().getName(),
                user.getWebsite()
        );

    }

    private void handleError(Throwable throwable) {
        detailView.showToast("Network call failure");
    }

}



