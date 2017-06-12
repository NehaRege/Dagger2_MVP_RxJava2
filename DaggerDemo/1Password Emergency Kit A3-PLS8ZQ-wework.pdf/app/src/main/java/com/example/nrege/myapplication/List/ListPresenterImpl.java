package com.example.nrege.myapplication.List;

import android.support.annotation.Nullable;
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
        repo.getUserListFromRetrofit(this);
    }

    @Override
    public void onListItemClick(int position) {
        Log.d(TAG, "onListItemClick: position data = "+allUsers.get(position));
        repo.saveSingleUserToSharedPrefs(allUsers.get(position));

        Log.d(TAG, "onListItemClick: position = "+position);

        int pos = position+1;

        Log.d(TAG, "onListItemClick: pos = "+pos);

        repo.position(pos);




        listView.navigateToDetail(Integer.toString(pos));
    }

    @Override
    public void onSuccess(ArrayList<User> users, String s) {

        if(s.equals("shared_prefs")) {
            listView.showToast("No internet connection. Retrieving User list from Shared Preferences!");
        } else if(s.equals("retrofit")) {
            listView.showToast("User list received from Retrofit Call");
        }

        allUsers = users;

        repo.saveUserListToSharedPrefs(allUsers);

        listView.setData(allUsers);

    }

    @Override
    public void onFailure(Throwable throwable) {
        listView.showToast("Network call failed!");
    }

}
