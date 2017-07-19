package com.example.nrege.myapplication.Sources;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nrege.myapplication.API.APIServRxJava;
import com.example.nrege.myapplication.API.APIService;
import com.example.nrege.myapplication.List.ListPresenterImpl;
import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/12/17.
 */

public class RemoteSourceImpl implements RemoteSource {
    private static String TAG = "RemoteSourceImpl";

    private Retrofit retrofit;

    public RemoteSourceImpl(Retrofit retrofit ) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<User> getSingleUserFromRetrofit(String id) {

        APIServRxJava apiServRxJava = retrofit.create(APIServRxJava.class);
        return apiServRxJava.getSingleUser(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<ArrayList<User>> getUserListFromRetrofit() {

        APIServRxJava apiServRxJava = retrofit.create(APIServRxJava.class);
        return apiServRxJava.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
