package com.kh.self6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    RadioButton rdDate, rdTime;
    DatePicker datePicker;
    TimePicker timePicker;
    TextView txtResult;
    LinearLayout layout;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");
        setUI();
        setListener();
        initDate();
    }

    private void initDate() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

    private void setListener() {
        rdDate.setOnClickListener(this);
        rdTime.setOnClickListener(this);
        chronometer.setOnClickListener(this);
        txtResult.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                chronometer.stop();
                layout.setVisibility(View.INVISIBLE);
                txtResult.setText(setDateAndTime());
                rdDate.setChecked(false);
                rdTime.setChecked(false);
                chronometer.setTextColor(Color.rgb(31, 97, 141 ));
                return false;
            }
        });
    }

    private void setUI() {
        chronometer = findViewById(R.id.chronometer);
        rdDate = findViewById(R.id.rdDate);
        rdTime = findViewById(R.id.rdTime);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        txtResult = findViewById(R.id.txtResult);
        layout = findViewById(R.id.layout);
    }

    private String make2digits(int num) {
        String result = num < 10 ? "0" + num : num + "";
        return result;
    }

    private String setDateAndTime() {
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int hour = timePicker.getHour();
        int min = timePicker.getMinute();
        return make2digits(year) + "년 " + make2digits(month) + "월 "
                + make2digits(day) + "일 " + make2digits(hour) + "시 "
                + make2digits(min) + "분 예약됨";
    }

    @Override
    public void onClick(View view) {
        if (view == chronometer) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            layout.setVisibility(View.VISIBLE);
            chronometer.setTextColor(Color.rgb(192, 57, 43));
        } else if (view == rdDate) {
            datePicker.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
        } else if (view == rdTime) {
            datePicker.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.VISIBLE);
        }
    }
}