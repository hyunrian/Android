package com.kh.widgetex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TimePicker timePicker;
    DatePicker datePicker;
    Chronometer chronometer;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setListener();
    }

    private void setListener() {
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int date) { // int : 년월일
                Log.d("mytag", year + "/" + (month  + 1) + "/" + date); // Java처럼 월은 0부터 시작
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                Log.d("mytag", hour + ":" + min);
            }
        });

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    private void setUI() {
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);
        chronometer = findViewById(R.id.chronometer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
    }

    @Override
    public void onClick(View view) {
        if (view == btnStart) {
            chronometer.start();
        } else if (view == btnStop) {
            chronometer.stop();
        }
    }
}