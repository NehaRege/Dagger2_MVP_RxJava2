package com.example.nrege.myapplication.Dagger;

import com.example.nrege.myapplication.Detail.DetailActivity;

import dagger.Component;

/**
 * Created by nrege on 6/7/17.
 */
@Component(modules = {StorageModule.class, RetrofitModule.class, DetailPresnterModule.class, RepoModule.class})
public interface DetailActivityComponent {
    void inject(DetailActivity detailActivity);
}

