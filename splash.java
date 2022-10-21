package com.example.greenifylogin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {

    VideoView videoView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btn = findViewById(R.id.button5);
        videoView = findViewById(R.id.video);
        String videopath = new StringBuilder("android.resource://").append(getPackageName()).append("/raw/flashac").toString();

        videoView.setVideoPath(videopath);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        startActivity(new Intent(getApplicationContext(),Register.class));
//                        finish();
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(getApplicationContext(), Register.class));
                                finish();
                            }
                        });

                    }
                },300);
            }
        });

        videoView.start();
    }
}