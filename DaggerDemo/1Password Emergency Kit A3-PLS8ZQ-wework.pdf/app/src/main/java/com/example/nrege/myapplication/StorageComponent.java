package com.example.nrege.myapplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nrege on 6/1/17.
 */

@Component(modules = {StorageModule.class, RetrofitModule.class})
public interface StorageComponent {

    void inject(MainActivity mainActivity);

    void inject(BActivity bActivity);

    void inject(ListActivity listActivity);

    void inject(DetailActivity detailActivity);
}
