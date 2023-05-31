package com.kh.fileioex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRead, btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);

        btnRead.setOnClickListener(this);
        btnWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRead) {
            try {
               FileInputStream in = openFileInput("file.txt");
               byte[] bytes = new byte[30];
               in.read(bytes); // 읽어서 bytes에 저장
               String data = new String(bytes);
               Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (view == btnWrite) {
            try {
                FileOutputStream out = openFileOutput("file.txt", MODE_PRIVATE);
                String str = "Hello\n헬로";
                byte[] bytes = str.getBytes();
                out.write(bytes);
                Toast.makeText(this, "쓰기 완료", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}