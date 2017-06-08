package com.example.nrege.myapplication.Dagger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    private static String baseUrl = "https://production-jsonplaceholder.typicode.com/";

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

    @Provides
    NetworkInfo provideNetworkInfo(){
        ConnectivityManager connMgr = (ConnectivityManager) myApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo;
    }
}
