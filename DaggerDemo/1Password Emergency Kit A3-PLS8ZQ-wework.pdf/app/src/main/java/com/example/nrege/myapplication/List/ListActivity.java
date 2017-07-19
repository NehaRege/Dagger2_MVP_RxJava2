package com.example.nrege.myapplication.List;

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

import com.example.nrege.myapplication.API.APIService;
import com.example.nrege.myapplication.CustomRecyclerViewAdapter;
import com.example.nrege.myapplication.Detail.DetailActivity;
import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Dagger.MyApplication;
import com.example.nrege.myapplication.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/2/17.
 */

public class ListActivity extends AppCompatActivity implements CustomRecyclerViewAdapter.OnRecyclerViewItemClickListener, ListView {

    private static String TAG = "ListActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Inject
    ListPresenter listPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ((MyApplication) getApplication()).createListComponent(this).inject(ListActivity.this);

        initializeViews();
        setLayoutManager();

    }

    @Override
    protected void onResume() {
        super.onResume();
        listPresenter.init();
    }

    @Override
    public void onItemClick(int position) {
        listPresenter.onListItemClick(position);
    }

    @Override
    public void navigateToDetail(String position) {
        Intent i = new Intent(ListActivity.this, DetailActivity.class);
        Log.d(TAG, "navigateToDetail: pos = " + position);
        i.putExtra("position", position);
        startActivity(i);
    }

    @Override
    public void setData(ArrayList<User> userList) {
        rvAdapter = new CustomRecyclerViewAdapter(userList, ListActivity.this);
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRetrofitFailureToast() {

        Toast.makeText(this, "Retrofit Call Failure", Toast.LENGTH_LONG).show();

    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_LONG).show();
    }

    public void initializeViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

    }

    public void setLayoutManager() {

        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

    }
}
