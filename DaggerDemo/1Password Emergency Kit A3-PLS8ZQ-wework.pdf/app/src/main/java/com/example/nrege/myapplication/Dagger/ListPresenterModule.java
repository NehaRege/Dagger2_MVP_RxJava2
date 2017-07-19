package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.example.nrege.myapplication.List.ListPresenter;
import com.example.nrege.myapplication.List.ListPresenterImpl;
import com.example.nrege.myapplication.List.ListView;
import com.example.nrege.myapplication.Repo.Repo;
import com.example.nrege.myapplication.Repo.RepoImpl;

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
    ListView providesListView() {
        return listView;
    }

    @Provides
    ListPresenter provideListPresenter(ListView listView, Repo repo) {
        return new ListPresenterImpl(listView, repo);
    }
}
