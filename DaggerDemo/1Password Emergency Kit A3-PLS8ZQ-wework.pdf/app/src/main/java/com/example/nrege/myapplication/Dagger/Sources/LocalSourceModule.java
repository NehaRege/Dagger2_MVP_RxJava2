package com.example.nrege.myapplication.Dagger.Sources;

import android.content.SharedPreferences;

import com.example.nrege.myapplication.Dagger.MyApplication;
import com.example.nrege.myapplication.Sources.LocalSource;
import com.example.nrege.myapplication.Sources.LocalSourceImpl;
import com.example.nrege.myapplication.Sources.RemoteSource;
import com.example.nrege.myapplication.Sources.RemoteSourceImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/13/17.
 */
@Module
public class LocalSourceModule {

    MyApplication myApplication;

    public LocalSourceModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    LocalSource providesLocalSource(SharedPreferences sharedPreferences) {
        return new LocalSourceImpl(sharedPreferences);
    }




}
