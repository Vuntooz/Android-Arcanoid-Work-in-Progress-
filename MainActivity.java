package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //setContentView(R.layout.activity_main);
        setContentView(new ProtoView(this));
    }
}