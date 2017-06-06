package com.example.nrege.myapplication;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nrege on 6/5/17.
 */
@Module
public class RetrofitModule {
    private MyApplication myApplication;

    private static String baseUrl = "https://jsonplaceholder.typicode.com/";

    public RetrofitModule(MyApplication myApplication, String baseUrl) {
        this.myApplication = myApplication;
        this.baseUrl = baseUrl;
    }

    @Provides
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
