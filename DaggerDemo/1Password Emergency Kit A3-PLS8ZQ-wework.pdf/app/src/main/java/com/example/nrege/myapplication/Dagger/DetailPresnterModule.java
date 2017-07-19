package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;
import android.net.NetworkInfo;

import com.example.nrege.myapplication.Detail.DetailPresenter;
import com.example.nrege.myapplication.Detail.DetailPresenterImpl;
import com.example.nrege.myapplication.Detail.DetailView;
import com.example.nrege.myapplication.Repo.Repo;
import com.example.nrege.myapplication.Repo.RepoImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/7/17.
 */
@Module
public class DetailPresnterModule {

    DetailView detailView;

    public DetailPresnterModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides
    DetailView providesDetailView() {
        return detailView;
    }

    @Provides
    DetailPresenter provideDetailPresenter(DetailView detailView, Repo repo) {
        return new DetailPresenterImpl(detailView, repo);
    }

}
