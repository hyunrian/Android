package com.kh.project8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker date;
    EditText text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.date);
        text = findViewById(R.id.text);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = date.getYear();
                int month = date.getMonth() + 1;
                int day = date.getDayOfMonth();
                String fileName = year + "_" + month + "_" + day + ".txt";
                String str = text.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    bos.write(str.getBytes());
                    Toast.makeText(MainActivity.this, "저장 완료", Toast.LENGTH_SHORT).show();
                    bos.close(); // close를 하지 않으면 내용 저장이 되지 않음
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); // button

//        date.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
//                String fileName = year + "_" + (month + 1) + "_" + day + ".txt";
//                try {
//                    FileInputStream fis = openFileInput(fileName);
//                    BufferedInputStream bis = new BufferedInputStream(fis);
//                    byte[] bytes = new byte[500];
//                    bis.read(bytes); // 파일을 읽어서 bytes에 저장
//                    text.setText(new String(bytes));
//                    bis.close(); // 가장 마지막 스트림만 닫으면 연결되어 있는 것은 자동으로 클로즈됨
//                    button.setText("update");
//                } catch (FileNotFoundException fe) {
//                    text.setText("");
//                    button.setText("save");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }); //date

        // 최초 프로그램 로딩 시 날짜 변경이 없어
        // 리스너 작동이 되지 않아 텍스트 내용이 저장되어 있어도 뜨지 않는 경우 해결

        // 현재 날짜 얻기
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // 현재 날짜의 파일 읽기
        String filename = year + "_" + (month + 1) + "_" + day + ".txt";
        getData(filename);

        // 프로그램 실행 시 현재 날짜로 초기화 & 날짜 변경 시 수행할 작업(리스너)
        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                // 해당 날짜의 파일 읽기
                String filename = year + "_" + (month + 1) + "_" + day + ".txt"; // 파라미터로 받은 날짜값
                getData(filename);
            }
        });

    } // OnCreate

    private void getData(String filename) {
        try {
            FileInputStream fis = openFileInput(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[500];
            bis.read(bytes); // 파일을 읽어서 bytes에 저장
            text.setText(new String(bytes));
            bis.close(); // 가장 마지막 스트림만 닫으면 연결되어 있는 것은 자동으로 클로즈됨
            button.setText("update");
        } catch (FileNotFoundException fe) {
            text.setText("");
            button.setText("save");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}