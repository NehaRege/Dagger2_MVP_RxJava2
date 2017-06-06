package com.example.nrege.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.nrege.myapplication.Models.AllUsers;
import com.example.nrege.myapplication.Models.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nrege on 6/2/17.
 */

public class ListActivity extends AppCompatActivity implements CustomRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private static String TAG = "User";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    private ArrayList<User> allUsers = new ArrayList<>();

    @Inject
    Retrofit retrofit;

    @Inject
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        ((MyApplication)getApplication()).getStorageComponent().inject(ListActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        loadUserData();

        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

    }

    @Override
    public void onItemClick(int position) {

        sharedPref.edit().putString("user", new Gson().toJson(allUsers.get(position))).apply();

        Intent i = new Intent(ListActivity.this, DetailActivity.class);
        startActivity(i);
    }

    public void loadUserData() {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            APIService service = retrofit.create(APIService.class);
            Call<ArrayList<User>> getAllUsers = service.getUsers();

            getAllUsers.enqueue(new Callback<ArrayList<User>>() {
                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    allUsers = response.body();

                    Log.d(TAG, "onResponse: all users = " +allUsers);
                    rvAdapter = new CustomRecyclerViewAdapter(allUsers,ListActivity.this);
                    recyclerView.setAdapter(rvAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                    Toast.makeText(ListActivity.this, "No network connection", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }


}
