package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nrege on 6/1/17.
 */

@Module
public class StorageModule {

    MyApplication myApplication;

    public StorageModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(myApplication);
    }
}
