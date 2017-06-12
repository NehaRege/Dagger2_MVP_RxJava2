package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;
import android.net.NetworkInfo;

import com.example.nrege.myapplication.Detail.DetailPresenter;
import com.example.nrege.myapplication.Detail.DetailPresenterImpl;
import com.example.nrege.myapplication.Detail.DetailView;
import com.example.nrege.myapplication.List.Repo;
import com.example.nrege.myapplication.List.RepoImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/7/17.
 */
@Module
public class DetailPresnterModule {

    DetailView detailView;
//    Repo repo;

    public DetailPresnterModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides
    DetailView providesDetailView() {
        return detailView;
    }

    @Provides
    Repo providesRepo(Retrofit retrofit, SharedPreferences sharedPreferences, NetworkInfo networkInfo) {
        return new RepoImpl(retrofit,sharedPreferences,networkInfo);
    }

    @Provides
    DetailPresenter provideDetailPresenter(DetailView detailView, Repo repo) {
        return new DetailPresenterImpl(detailView,repo);
    }


}
