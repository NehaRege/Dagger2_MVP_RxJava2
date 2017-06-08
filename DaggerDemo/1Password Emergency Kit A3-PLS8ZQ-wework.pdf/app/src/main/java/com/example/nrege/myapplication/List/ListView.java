package com.example.nrege.myapplication.List;

import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nrege on 6/7/17.
 */

public interface ListView {

    void navigateToDetail();

    void setData(ArrayList<User> userList);

    void showNoInternetToast();

    void showRetrofitFailureToast();

//    void init();

}
