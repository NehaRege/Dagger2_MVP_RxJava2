package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.example.nrege.myapplication.Repo.Repo;
import com.example.nrege.myapplication.Repo.RepoImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/12/17.
 */
@Module
public class RepoModule {

    MyApplication myApplication;

    public RepoModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    Repo providesRepo(Retrofit retrofit, SharedPreferences sharedPreferences, NetworkInfo networkInfo) {
        return new RepoImpl(retrofit,sharedPreferences,networkInfo);
    }

}