package com.example.nrege.myapplication.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nrege.myapplication.Dagger.MyApplication;
import com.example.nrege.myapplication.R;

import javax.inject.Inject;

/**
 * Created by nrege on 6/5/17.
 */

public class DetailActivity extends AppCompatActivity implements DetailView {
    TextView txt_email;
    TextView txt_contact;
    TextView txt_name;
    TextView txt_add;
    TextView txt_company;
    TextView txt_website;

    String position;

    @Inject
    DetailPresenter detailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ((MyApplication) getApplication()).createDetailComponent(this).inject(DetailActivity.this);

        Intent intent = getIntent();
        position = intent.getStringExtra("position");

        findViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        detailPresenter.init(position);
    }

    @Override
    public void setText(String email, String phone, String name, String add, String company, String website) {
        txt_add.setText(add);
        txt_company.setText(company);
        txt_contact.setText(phone);
        txt_name.setText(name);
        txt_website.setText(website);
        txt_email.setText(email);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }

    public void findViews() {
        txt_email = (TextView) findViewById(R.id.detail_textView);
        txt_contact = (TextView) findViewById(R.id.detail_contact);
        txt_name = (TextView) findViewById(R.id.detail_name);
        txt_add = (TextView) findViewById(R.id.detail_add);
        txt_company = (TextView) findViewById(R.id.detail_company);
        txt_website = (TextView) findViewById(R.id.detail_website);
    }

}
