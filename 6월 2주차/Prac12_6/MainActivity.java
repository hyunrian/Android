package com.kh.prac12_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datepicker;
    EditText editText;
    Button btnInsert, btnDelete;
    int year, month, day;
    MyDiaryDao dao;
    MyHelper helper;
    String prevText;
    boolean isThere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datepicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);

        helper = new MyHelper(this, "diary.db", null, 1);
        dao = new MyDiaryDao(helper);

        initCalandar();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = year + "_" + month + "_" + day;
                String text = editText.getText().toString();
                String str = "";
                boolean result = false;
                if (!isThere) {
                    result = dao.insert(new MyDiaryVo(date, text));
                    str = "입력";
                } else {
                    Log.d("mytag", "edit");
                    result = dao.update(new MyDiaryVo(date, text));
                    str = "수정";
                }
                if (result)
                    Toast.makeText(MainActivity.this,
                            str + " 완료", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = year + "_" + month + "_" + day;
                boolean result = dao.delete(date);
                if (result) Toast.makeText(MainActivity.this,
                            "삭제 완료", Toast.LENGTH_SHORT).show();
                editText.setText("");
                btnInsert.setText("입력하기");
            }
        });

        datepicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                MainActivity.this.year = year;
                MainActivity.this.month = month + 1;
                MainActivity.this.day = day;
                String date = year + "_" + (month+1) + "_" + day;
                setPrevText(date);
            }
        });
    }

    private void initCalandar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = year + "_" + month + "_" + day;
        setPrevText(date);
    }

    private void setPrevText(String date) {
        prevText = dao.select(date);
        editText.setText(prevText);
        if (prevText == null || prevText.equals("")) {
            btnInsert.setText("입력하기");
            isThere = false;
        } else {
            btnInsert.setText("수정하기");
            isThere = true;
        }
    }
}