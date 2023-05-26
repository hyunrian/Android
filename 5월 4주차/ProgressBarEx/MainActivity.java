package com.kh.progressbarex;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    SeekBar seekbar;
    RatingBar ratingBar, ratingBarIndi, ratingBarSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressbar);
        seekbar = findViewById(R.id.seekbar);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBarIndi = findViewById(R.id.ratingBarIndi);
        ratingBarSmall = findViewById(R.id.ratingBarSmall);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) { // i 값 : 현재 진행 상태, boolean : 사용자가 변경했는지 여부
                progressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { // 움직이기 시작할 때 호출
                Log.d("mytag", "seekbar moved");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { // 멈출 때 호출
                Log.d("mytag", "seekbar stopped");
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBarIndi.setRating(v);
                ratingBarSmall.setRating(v);

            }
        });
    }
}