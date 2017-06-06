package com.example.nrege.myapplication;

import android.app.Application;

import dagger.Module;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/1/17.
 */
public class MyApplication extends Application {

    private static String BASEURL = "https://jsonplaceholder.typicode.com/";

    StorageComponent componentStorage;


    @Override
    public void onCreate() {
        super.onCreate();

        componentStorage = DaggerStorageComponent
                .builder()
                .storageModule(new StorageModule(this))
                .retrofitModule(new RetrofitModule(this,BASEURL))
                .build();

    }

    public StorageComponent getStorageComponent() {
        return componentStorage;
    }


}
