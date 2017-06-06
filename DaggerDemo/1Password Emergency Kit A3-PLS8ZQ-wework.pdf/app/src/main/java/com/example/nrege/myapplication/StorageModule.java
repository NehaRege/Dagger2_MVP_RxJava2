package com.example.nrege.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nrege on 6/1/17.
 */

@Module
class StorageModule {

    private MyApplication myApplication;

    StorageModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(myApplication);
    }
}
