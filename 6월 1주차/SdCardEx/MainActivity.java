package com.kh.sdcardex;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRead, btnMake, btnRemove, btnFileList;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE); // sd카드 접근을 위한 설정

        btnRead = findViewById(R.id.btnRead);
        btnMake = findViewById(R.id.btnMake);
        btnRemove = findViewById(R.id.btnRemove);
        btnFileList = findViewById(R.id.btnFileList);
        editText = findViewById(R.id.editText);

        btnMake.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnFileList.setOnClickListener(this);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//                File f = new File("/sdcard");
//                String path3 = f.getAbsolutePath();
                try {
                    FileReader fr = new FileReader("/sdcard/sd_test.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String data = "";
                    while (true) {
                        String line = br.readLine(); // Enter 키 전까지 한 줄 단위로 읽기
                        if (line == null) break;
                        data += line + "\n";
                    }
                    editText.setText(data);
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    } //OnCreate

    @Override
    public void onClick(View view) {
        if (view == btnMake) {
            File f = new File("/sdcard/mydir");
            if (!f.exists()) {
                f.mkdir();
                editText.setText("디렉토리 생성 완료");
            }
        } else if (view == btnRemove) {
            File f = new File("/sdcard/mydir");
            if (f.exists()) {
                f.delete();
                editText.setText("디렉토리 삭제 완료");
            }
        } else if (view == btnFileList) {
            File f = new File(Environment.getRootDirectory().getAbsolutePath()); // root 디렉토리 경로 얻기
            File[] files = f.listFiles(); // 파일 정보 리스트 얻기
            editText.setText("");
            for (File aFile : files) {
                String str = "";
                if (aFile.isFile()) str += "<파일> ";
                else str += "<폴더> ";
                str += aFile.getName();
                if (aFile.isFile()) str += " " + aFile.length() + "bytes"; // 파일 크기
                editText.append(str + "\n");
            }
        }
    }
}