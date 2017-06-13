package com.example.nrege.myapplication.Dagger.Sources;

import com.example.nrege.myapplication.Dagger.MyApplication;
import com.example.nrege.myapplication.Sources.RemoteSource;
import com.example.nrege.myapplication.Sources.RemoteSourceImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nrege on 6/13/17.
 */
@Module
public class RemoteSourceModule {

    MyApplication myApplication;

    public RemoteSourceModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    RemoteSource providesRemoteSource(Retrofit retrofit) {
        return new RemoteSourceImpl(retrofit);
    }

}
