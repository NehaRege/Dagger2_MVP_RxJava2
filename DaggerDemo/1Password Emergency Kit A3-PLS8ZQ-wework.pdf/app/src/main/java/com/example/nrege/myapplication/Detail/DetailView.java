package com.example.nrege.myapplication.Detail;

/**
 * Created by nrege on 6/7/17.
 */

public interface DetailView {

    void setText(String email, String phone, String name, String add, String company, String website);

    void showToast(String s);

}
