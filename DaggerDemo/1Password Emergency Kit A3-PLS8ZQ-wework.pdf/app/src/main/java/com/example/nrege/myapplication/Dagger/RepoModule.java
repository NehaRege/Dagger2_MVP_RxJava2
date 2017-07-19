package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.nrege.myapplication.Repo.Repo;
import com.example.nrege.myapplication.Repo.RepoImpl;
import com.example.nrege.myapplication.Sources.LocalSource;
import com.example.nrege.myapplication.Sources.LocalSourceImpl;
import com.example.nrege.myapplication.Sources.RemoteSource;
import com.example.nrege.myapplication.Sources.RemoteSourceImpl;

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
    Repo providesRepo(@Nullable NetworkInfo networkInfo, LocalSource localSource, RemoteSource remoteSource) {
        return new RepoImpl(networkInfo, remoteSource, localSource);
    }

}
