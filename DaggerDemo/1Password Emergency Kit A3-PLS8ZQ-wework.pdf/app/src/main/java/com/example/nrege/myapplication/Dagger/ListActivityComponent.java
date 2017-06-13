package com.example.nrege.myapplication.Dagger;

import com.example.nrege.myapplication.Dagger.Sources.LocalSourceModule;
import com.example.nrege.myapplication.Dagger.Sources.RemoteSourceModule;
import com.example.nrege.myapplication.List.ListActivity;

import dagger.Component;

/**
 * Created by nrege on 6/7/17.
 */
@Component(modules = {StorageModule.class, RetrofitModule.class,
        RemoteSourceModule.class, LocalSourceModule.class, RepoModule.class, ListPresenterModule.class})
public interface ListActivityComponent {
    void inject(ListActivity activity);
}
