package com.example.nrege.myapplication.Dagger;

//import com.example.nrege.myapplication.BActivity;
import com.example.nrege.myapplication.Detail.DetailActivity;
import com.example.nrege.myapplication.Detail.DetailPresenterImpl;
import com.example.nrege.myapplication.List.ListActivity;
import com.example.nrege.myapplication.List.ListPresenterImpl;
//import com.example.nrege.myapplication.MainActivity;

import dagger.Component;

/**
 * Created by nrege on 6/1/17.
 */

@Component(modules = {StorageModule.class, RetrofitModule.class})
public interface StorageComponent {

//    void inject(MainActivity mainActivity);
//
//    void inject(BActivity bActivity);

    void inject(ListActivity listActivity);

    void inject(DetailActivity detailActivity);

    void inject(DetailPresenterImpl detailPresenterImpl);

    void inject(ListPresenterImpl listPresenterImpl);
}
