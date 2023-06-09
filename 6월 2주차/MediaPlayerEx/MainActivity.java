package com.kh.mediaplayerex;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch switch1;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = findViewById(R.id.switch1);



        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = switch1.isChecked();
                if (isChecked) {
                    player = MediaPlayer.create(MainActivity.this, R.raw.song1);
                    player.start();
                } else {
                    player.stop();
                }
            }
        });
    }
}