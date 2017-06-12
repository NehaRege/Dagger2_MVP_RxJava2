package com.example.nrege.myapplication.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.nrege.myapplication.API.APIService;
import com.example.nrege.myapplication.CustomRecyclerViewAdapter;
import com.example.nrege.myapplication.Dagger.MyApplication;
import com.example.nrege.myapplication.Models.User;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
