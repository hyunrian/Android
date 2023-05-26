package com.kh.project6_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    Button btnStart, btnFinish;
    RadioButton rdDate, rdTime;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView txtResult;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setListener();
        setInitDate(); // 날짜 변경이 없으면 오늘 날짜로 설정
    }

    private void setInitDate() {
        Calendar cal = Calendar.getInstance();
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH) + 1;
        this.day = cal.get(Calendar.DAY_OF_MONTH);
    }

    private void setListener() {
        btnStart.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
        rdDate.setOnClickListener(this);
        rdTime.setOnClickListener(this);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int year, int month, int day) {
                MainActivity.this.year = year;
                MainActivity.this.month = month + 1;
                MainActivity.this.day = day;
            }
        });
    }

    private void setUI() {
        chronometer = findViewById(R.id.chronometer);
        btnStart = findViewById(R.id.btnStart);
        btnFinish = findViewById(R.id.btnFinish);
        rdDate = findViewById(R.id.rdDate);
        rdTime = findViewById(R.id.rdTime);
        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);
        txtResult = findViewById(R.id.txtResult);
    }

    private String make2digits(int value) {
        if (value < 10) return "0" + value;
        else return value + "";
    }

    private String selectedDate() {
        String result = make2digits(year) + "년 "
                + make2digits(month) + "월 " + make2digits(day) + "일 ";
        return result;
    }

    private String selectedTime() {
        int hour = timePicker.getHour();
        int min = timePicker.getMinute();
        String result = make2digits(hour) + "시 " + make2digits(min) + "분 ";
        return result;
    }

    @Override
    public void onClick(View view) {
        if (view == btnStart) {
            chronometer.setBase(SystemClock.elapsedRealtime()); // 실제 흐른 시간값으로 설정
            chronometer.start();
            chronometer.setTextColor(Color.RED);
        } else if (view == btnFinish) {
            chronometer.stop();
            calendarView.getDate();
            chronometer.setTextColor(Color.BLUE);
            txtResult.setText(selectedDate() + selectedTime() + "예약됨");
        } else if (view == rdDate) {
            calendarView.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
        } else if (view == rdTime) {
            calendarView.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.VISIBLE);
        }
    }
}