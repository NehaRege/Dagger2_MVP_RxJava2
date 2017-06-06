package com.example.nrege.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.nrege.myapplication.Models.User;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import javax.inject.Inject;

/**
 * Created by nrege on 6/5/17.
 */

public class DetailActivity extends AppCompatActivity {
    private static String TAG = "DetailActivity";

    @Inject
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ((MyApplication)getApplication()).getStorageComponent().inject(DetailActivity.this);

        TextView txt_email =(TextView) findViewById(R.id.detail_textView);
        TextView txt_contact =(TextView) findViewById(R.id.detail_contact);
        TextView txt_name =(TextView) findViewById(R.id.detail_name);
        TextView txt_add =(TextView) findViewById(R.id.detail_add);
        TextView txt_company =(TextView) findViewById(R.id.detail_company);
        TextView txt_website =(TextView) findViewById(R.id.detail_website);

        String jsonUser = sharedPreferences.getString("user",null);
        User user = new Gson().fromJson(jsonUser,User.class);

        txt_email.setText(user.getEmail());
        txt_contact.setText(user.getPhone());
        txt_name.setText(user.getName());
        txt_add.setText(user.getAddress().getStreet()+", "+user.getAddress().getCity()+" "+user.getAddress().getZipcode());
        txt_company.setText(user.getCompany().getName());
        txt_website.setText(user.getWebsite());





    }
}
