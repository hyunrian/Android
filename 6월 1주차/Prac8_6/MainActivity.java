package com.kh.prac8_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        datePicker = findViewById(R.id.datePicker);
        text = findViewById(R.id.text);
        button = findViewById(R.id.button);

        setListener();
        initData();
    }

    private void initData() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String filename = year + "_" + month + "_" + day + ".txt";
        getData(filename);
    }

    private void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makeDirectory("mydiary"); // OnCreate가 끝난 이후에 폴더 생성이 가능(권한문제로 인함)

                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                String filename = year + "_" + (month + 1) + "_" + day + ".txt";

                button.setText("save");

                try { // 문자 데이터이므로 FileWriter/Reader 사용
                    FileWriter fw = new FileWriter("/sdcard/mydiary/" + filename);
                    BufferedWriter bw = new BufferedWriter(fw); // Buffer : 성능 업그레이드
                    String str = text.getText().toString();
                    bw.write(str);
                    Toast.makeText(MainActivity.this, "저장 완료", Toast.LENGTH_SHORT).show();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                String filename = year + "_" + (month + 1) + "_" + day + ".txt";
                getData(filename);
            }
        });

    } //setListener()

    private void makeDirectory(String dirName) {
        File f = new File("/sdcard/" + dirName);
        if (!f.exists()) f.mkdir();
    }

    private void getData(String filename) {
        try {
            FileReader fr = new FileReader("/sdcard/mydiary/"+filename);
            BufferedReader br = new BufferedReader(fr);
//            char[] chars = new char[300];
//            br.read(chars);
//            text.setText(String.valueOf(chars)); // 아래 코드와 동일
            text.setText("");
            while (true) {
                String str = "";
                String line = br.readLine(); // 한 줄씩 읽기
                if (line == null || line.equals("")) break;
                text.append(line + "\n");
            }
            br.close();
            button.setText("update");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            button.setText("save");
            text.setText("");
        }
    }
}