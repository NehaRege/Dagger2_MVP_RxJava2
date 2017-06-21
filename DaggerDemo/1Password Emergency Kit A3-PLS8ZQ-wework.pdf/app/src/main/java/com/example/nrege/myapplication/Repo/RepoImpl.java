package com.example.nrege.myapplication.Repo;

import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.nrege.myapplication.Models.User;
import com.example.nrege.myapplication.Sources.LocalSource;
import com.example.nrege.myapplication.Sources.RemoteSource;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nrege on 6/9/17.
 */

public class RepoImpl implements Repo {

    private static String TAG = "RepoImpl";

    @Nullable
    private NetworkInfo networkInfo;

    RemoteSource remoteSource;

    LocalSource localSource;

    ArrayList<User> allUsers = new ArrayList<>();

    User user;

    public RepoImpl(@Nullable NetworkInfo networkInfo, RemoteSource remoteSource, LocalSource localSource) {
        this.networkInfo = networkInfo;
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    @Override
    public Observable<ArrayList<User>> getUserList() {

        if (networkInfo != null && networkInfo.isConnected()) {

            return remoteSource.getUserListFromRetrofit();

        } else {

            allUsers = localSource.getUserListFromSharedPrefs();

            return getObservable_list()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

    }

    private Observable<ArrayList<User>> getObservable_list() {
        return Observable.fromArray(allUsers);
//        return Observable.create(new ObservableOnSubscribe<ArrayList<User>>() {
//            @Override
//            public void subscribe(ObservableEmitter<ArrayList<User>> e) throws Exception {
//                if(!e.isDisposed()) {
//                    e.onNext(allUsers);
//                    e.onComplete();
//                }
//            }
//        });
    }

    private Observable<User> getObservable_user() {
        return Observable.just(user);
//        return Observable.create(new ObservableOnSubscribe<User>() {
//            @Override
//            public void subscribe(ObservableEmitter<User> e) throws Exception {
//                if(!e.isDisposed()) {
//                    e.onNext(user);
//                    e.onComplete();
//                }
//            }
//        });
    }


    @Override
    public Observable<User> getSingleUser(String id) {
        if(networkInfo != null && networkInfo.isConnected()) {

            return remoteSource.getSingleUserFromRetrofit(id);

        } else {

            user = localSource.getSingleUserFromSharedPrefs();

            return getObservable_user()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    @Override
    public void saveUserListToSharedPrefs(ArrayList<User> allUsers){
        localSource.saveUserListToSharedPrefs(allUsers);
    }

    @Override
    public void saveSingleUserToSharedPrefs(User user) {
        localSource.saveSingleUserToSharedPrefs(user);
    }

}
