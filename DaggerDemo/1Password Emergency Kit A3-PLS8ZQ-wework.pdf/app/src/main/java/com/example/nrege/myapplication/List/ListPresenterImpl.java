package com.example.nrege.myapplication.List;

import android.util.Log;

import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Repo.Repo;

import java.util.ArrayList;

/**
 * Created by nrege on 6/7/17.
 */

public class ListPresenterImpl implements ListPresenter, Repo.OnCallbackFinished {
    public static final String TAG = "ListPresenterImpl";

    private ArrayList<User> allUsers = new ArrayList<>();

    ListView listView;
    Repo repo;

    public ListPresenterImpl(ListView listView, Repo repo) {
        this.listView = listView;
        this.repo = repo;
    }

    @Override
    public void init() {
        repo.getUsersFromRetrofit(this);
    }

    @Override
    public void onListItemClick(int position) {
        Log.d(TAG, "onListItemClick: position data = "+allUsers.get(position));
        repo.saveUserToSharedPrefs(allUsers.get(position));
        listView.navigateToDetail();
    }

    @Override
    public void onSuccess(ArrayList<User> users) {
        Log.d(TAG, "onSuccess: users = "+users.size());
        allUsers = users;
        listView.setData(users);
    }

    @Override
    public void onFailure(Throwable throwable) {

        listView.showNoInternetToast();



    }
}
