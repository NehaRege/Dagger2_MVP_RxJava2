package com.example.nrege.myapplication.Dagger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nrege on 6/5/17.
 */
@Module
public class RetrofitModule {
    MyApplication myApplication;

    private static String baseUrl = "https://staging-jsonplaceholder.typicode.com/";

    public RetrofitModule(MyApplication myApplication, String baseUrl) {
        this.myApplication = myApplication;
        this.baseUrl = baseUrl;
    }

    @Provides
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

    @Provides
    @Nullable
    NetworkInfo provideNetworkInfo(){
        ConnectivityManager connMgr = (ConnectivityManager) myApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo;
    }
}
