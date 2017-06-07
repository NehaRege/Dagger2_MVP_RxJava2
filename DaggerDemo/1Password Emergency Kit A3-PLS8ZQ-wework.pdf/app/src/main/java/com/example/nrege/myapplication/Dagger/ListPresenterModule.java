package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;
import android.net.NetworkInfo;

import com.example.nrege.myapplication.List.ListPresenter;
import com.example.nrege.myapplication.List.ListPresenterImpl;
import com.example.nrege.myapplication.List.ListView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/7/17.
 */
@Module
public class ListPresenterModule {
    ListView listView;

    public ListPresenterModule(ListView listView) {
        this.listView = listView;
    }

    @Provides
    ListView providesListView(){
        return listView;
    }

    @Provides
    ListPresenter provideListPresenter(ListView listView, Retrofit r, SharedPreferences s, NetworkInfo networkInfo) {
        return new ListPresenterImpl(listView, r, s, networkInfo);
    }
}