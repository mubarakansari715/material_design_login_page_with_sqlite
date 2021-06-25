package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Splash_screen extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        WindowInsetsController insetsController = getWindow().getInsetsController();
        insetsController.hide(WindowInsets.Type.statusBars());
        mediaPlayer = MediaPlayer.create(Splash_screen.this,R.raw.jannat);
        mediaPlayer.start();
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
        finish();
    }
}