package com.example.nrege.myapplication.Dagger;

import android.app.Application;

//import com.example.nrege.myapplication.Dagger.DaggerStorageComponent;
//import com.example.nrege.myapplication.Dagger.StorageComponent;
import com.example.nrege.myapplication.Dagger.Sources.LocalSourceModule;
import com.example.nrege.myapplication.Dagger.Sources.RemoteSourceModule;
import com.example.nrege.myapplication.Detail.DetailView;
import com.example.nrege.myapplication.List.ListView;

/**
 * Created by nrege on 6/1/17.
 */
public class MyApplication extends Application {

    private static String BASEURL = "https://jsonplaceholder.typicode.com/";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ListActivityComponent createListComponent(ListView listView) {
        return DaggerListActivityComponent
                .builder()
                .storageModule(new StorageModule(this))
                .retrofitModule(new RetrofitModule(this, BASEURL))
                .remoteSourceModule(new RemoteSourceModule(this))
                .localSourceModule(new LocalSourceModule(this))
                .repoModule(new RepoModule(this))
                .listPresenterModule(new ListPresenterModule(listView))
                .build();
    }

    public DetailActivityComponent createDetailComponent(DetailView detailView) {
        return DaggerDetailActivityComponent
                .builder()
                .storageModule(new StorageModule(this))
                .remoteSourceModule(new RemoteSourceModule(this))
                .localSourceModule(new LocalSourceModule(this))
                .detailPresnterModule(new DetailPresnterModule(detailView))
                .repoModule(new RepoModule(this))
                .retrofitModule(new RetrofitModule(this, BASEURL))
                .build();
    }

}
