package com.example.nrege.myapplication.Dagger;

import android.app.Application;

//import com.example.nrege.myapplication.Dagger.DaggerStorageComponent;
//import com.example.nrege.myapplication.Dagger.StorageComponent;
import com.example.nrege.myapplication.Detail.DetailView;
import com.example.nrege.myapplication.List.ListView;

/**
 * Created by nrege on 6/1/17.
 */
public class MyApplication extends Application {

    private static String BASEURL = "https://jsonplaceholder.typicode.com/";

//    public StorageComponent componentStorage;


    @Override
    public void onCreate() {
        super.onCreate();

//        componentStorage = DaggerStorageComponent
//                .builder()
//                .storageModule(new StorageModule(this))
//                .retrofitModule(new RetrofitModule(this, BASEURL))
//                .build();
    }

//    public StorageComponent getStorageComponent() {
//        return componentStorage;
//    }

    public ListActivityComponent createListComponent(ListView listView){
        return DaggerListActivityComponent
                .builder()
                .storageModule(new StorageModule(this))
                .retrofitModule(new RetrofitModule(this, BASEURL))
                .listPresenterModule(new ListPresenterModule(listView))
                .build();
    }

    public DetailActivityComponent createDetailComponent(DetailView detailView) {
        return DaggerDetailActivityComponent
                .builder()
                .storageModule(new StorageModule(this))
                .detailPresnterModule(new DetailPresnterModule(detailView))
                .build();
    }


}
