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

public class ListPresenterImpl implements ListPresenter {
    public static final String TAG = "ListPresenterImpl";

    private ArrayList<User> allUsers = new ArrayList<>();

    ListView listView;
    Retrofit retrofit;
    SharedPreferences sharedPreferences;

    NetworkInfo networkInfo;

    public ListPresenterImpl(ListView listView, Retrofit r, SharedPreferences s, NetworkInfo networkInfo) {
        this.listView = listView;
        this.retrofit = r;
        this.sharedPreferences = s;
        this.networkInfo = networkInfo;
    }


    @Override
    public void init() {
        getData();
    }

    @Override
    public void getData() {
        if (networkInfo != null && networkInfo.isConnected()) {
            APIService service = retrofit.create(APIService.class);
            Log.d(TAG, "getData: retrofit = " + retrofit);

            Call<ArrayList<User>> getAllUsers = service.getUsers();

            getAllUsers.enqueue(new Callback<ArrayList<User>>() {

                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    allUsers = response.body();
                    listView.setData(allUsers);
                }

                @Override
                public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                    //todo show error
//                    Toast.makeText(ListPresenterImpl.this, "OnFailure", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: " + t);
                }
            });

        } else {
            listView.showNoInternetToast();
        }
    }

    @Override
    public void onListItemClick(int position) {
        sharedPreferences.edit().putString("user", new Gson().toJson(allUsers.get(position))).apply();
        listView.navigateToDetail();

    }
}
