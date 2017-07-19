//package com.example.nrege.myapplication;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.nrege.myapplication.Dagger.MyApplication;
//
//import javax.inject.Inject;
//
//public class MainActivity extends AppCompatActivity {
//
//    String text;
//    TextView textViewName;
//
//    @Inject
//    SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ((MyApplication)getApplication()).getStorageComponent().inject(this);
//
//        Button button = (Button) findViewById(R.id.activity_main_button_save);
//        textViewName = (TextView) findViewById(R.id.activity_main_txt);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                text = textViewName.getText().toString().trim();
//
//                sharedPreferences.edit().putString("text",text).apply();
//
//                Intent intent = new Intent(MainActivity.this,BActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//    }
//}
