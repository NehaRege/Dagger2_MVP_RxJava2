//package com.example.nrege.myapplication;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.nrege.myapplication.Dagger.MyApplication;
//
//import javax.inject.Inject;
//
///**
// * Created by nrege on 6/1/17.
// */
//
//public class BActivity extends AppCompatActivity {
//
//    String text;
//    TextView textViewName;
//    Button button;
//
//    @Inject
//    SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_b);
//
//        ((MyApplication)getApplication()).getStorageComponent().inject(this);
//
//        button = (Button) findViewById(R.id.activity_b_button_done);
//        textViewName = (TextView) findViewById(R.id.activity_b_txt);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                text = sharedPreferences.getString("text",null);
//
//                textViewName.setText(text);
//
//
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//}
