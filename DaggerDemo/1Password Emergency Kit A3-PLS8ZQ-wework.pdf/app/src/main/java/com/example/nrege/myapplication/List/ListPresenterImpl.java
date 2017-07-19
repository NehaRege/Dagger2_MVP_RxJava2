package com.example.nrege.myapplication.List;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Repo.Repo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by nrege on 6/7/17.
 */

public class ListPresenterImpl implements ListPresenter {
    private static final String TAG = "ListPresenterImpl";

    private ArrayList<User> allUsersRx = new ArrayList<>();

    Observable<ArrayList<User>> observable;

    private ListView listView;
    Repo repo;

    public ListPresenterImpl(ListView listView, Repo repo) {
        this.listView = listView;
        this.repo = repo;
    }

    @Override
    public void init() {
        observable = repo.getUserList();

        observable.subscribe(new Observer<ArrayList<User>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ArrayList<User> users) {

                allUsersRx = users;

                repo.saveUserListToSharedPrefs(allUsersRx);

                listView.setData(allUsersRx);


            }

            @Override
            public void onError(@NonNull Throwable e) {
                listView.showToast("Network call failed!");

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void handleResponse(ArrayList<User> users) {

        Log.d(TAG, "handleResponse: all users in presenter = " + users);

        allUsersRx = users;

        repo.saveUserListToSharedPrefs(allUsersRx);

        listView.setData(allUsersRx);

    }

    private void handleError(Throwable throwable) {
        listView.showToast("Network call failed!");
    }

    @Override
    public void onListItemClick(int position) {

        repo.saveSingleUserToSharedPrefs(allUsersRx.get(position));

        int pos = position + 1;

        listView.navigateToDetail(Integer.toString(pos));

    }

}
