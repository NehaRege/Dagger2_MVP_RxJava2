package com.example.nrege.myapplication.Dagger;

import android.content.SharedPreferences;

import com.example.nrege.myapplication.Detail.DetailPresenter;
import com.example.nrege.myapplication.Detail.DetailPresenterImpl;
import com.example.nrege.myapplication.Detail.DetailView;

import dagger.Module;
import dagger.Provides;

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
    DetailPresenter provideDetailPresenter(DetailView detailView, SharedPreferences sharedPreferences) {
        return new DetailPresenterImpl(detailView,sharedPreferences);
    }


}
