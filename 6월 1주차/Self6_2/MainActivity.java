package com.kh.self6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnStop;
    ViewFlipper viewFlipper;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        viewFlipper = findViewById(R.id.viewFlipper);
        text = findViewById(R.id.text);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnStart) {
            viewFlipper.setFlipInterval(1000);
            viewFlipper.startFlipping();
            btnStart.setBackgroundTintMode(PorterDuff.Mode.LIGHTEN);
            text.setVisibility(View.VISIBLE);
            text.setText("사진보기 재생중");
        } else if (view == btnStop) {
            viewFlipper.stopFlipping();
            text.setText("사진보기 정지");
        }
    }
}