package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView applogo;
    private TextView appname,developerdetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        applogo=(ImageView)findViewById(R.id.logo);
        appname=(TextView)findViewById(R.id.myappname);
        developerdetails=(TextView)findViewById(R.id.developerdetails);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inte =  new Intent(SplashActivity.this,MainActivity.class);
                startActivity(inte);
                finish();
            }
        },1500);

    }
}
