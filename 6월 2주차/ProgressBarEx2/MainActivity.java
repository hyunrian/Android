package com.kh.progressbarex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    Button btnInc, btnDec;
    TextView textView;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        btnInc = findViewById(R.id.btnInc);
        btnDec = findViewById(R.id.btnDec);
        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);

        btnInc.setOnClickListener(this);
        btnDec.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                progressBar.setProgress(progress);
                textView.setText("진행률 : " + progress + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int progress = 0;
        int value = 0;
        if (view == btnInc) {
            value = 10;
            progress = progressBar.getProgress() + value;
            int max = progressBar.getMax();
            if (progress > max) progress = max;
        } else if (view == btnDec) {
            value = -10;
            progress = progressBar.getProgress() + value;
            if (progress < 0) progress = 0;
        }
        progressBar.incrementProgressBy(value);
        seekBar.incrementProgressBy(value);
        textView.setText("진행률 : " + progress + " %");
    }
}