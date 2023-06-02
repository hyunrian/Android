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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

//                try {
//                    FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
//                    String str = text.getText().toString();
//                    byte[] bytes = str.getBytes();
//                    fos.write(bytes);
//                    Toast.makeText(MainActivity.this, "저장 완료", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                try {
                    File file = new File(("/sdcard/mydiary/" + filename));
                    FileOutputStream fos = new FileOutputStream(file);
                    String str = text.getText().toString();
                    fos.write(str.getBytes());
                    Log.d("mytag",text.getText().toString());
                    Toast.makeText(MainActivity.this, "저장 완료", Toast.LENGTH_SHORT).show();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                String filename = year + "_" + (month + 1) + "_" + day + ".txt";

//                try {
//                    FileInputStream fis = openFileInput(filename);
//                    BufferedInputStream bis = new BufferedInputStream(fis);
//                    byte[] bytes = new byte[300];
//                    bis.read(bytes);
//                    text.setText(new String(bytes));
//                    bis.close();
//                    Toast.makeText(MainActivity.this, "로딩 완료", Toast.LENGTH_SHORT).show();
//                    button.setText("update");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    button.setText("save");
//                } // 실행 제대로 안됨
                    try {
                        File file = new File(("/sdcard/mydiary/"+filename));
                        FileInputStream fis = new FileInputStream(file);
                        byte[] bytes = new byte[300];
                        fis.read(bytes);
                        text.setText(new String(bytes));
                        fis.close();
                        button.setText("update");
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        button.setText("save");
                        text.setText("");
                    }
                    // 처음 로딩했을 때 등록된 내용 읽어오는 기능 추가 필요
            }
        });

    } //setListener()

    private void makeDirectory(String dirName) {
        File f = new File("/sdcard/" + dirName);
        if (!f.exists()) f.mkdir();
    }
}