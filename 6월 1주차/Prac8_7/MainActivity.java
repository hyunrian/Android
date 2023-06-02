package com.kh.prac8_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPrev, btnNext;
    TextView orderCheck;
    PictureView pictureView;
    final String FILEPATH = "/sdcard/Pictures";
    ArrayList<File> list = new ArrayList<>();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        orderCheck = findViewById(R.id.orderCheck);
        pictureView = findViewById(R.id.pictureView);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        setFileList(FILEPATH);
        String imgPath = FILEPATH + "/" + list.get(index).getName();
        pictureView.setImagePath(imgPath); // 프로그램 실행 시 최초로 뜰 이미지 세팅
        orderCheck.setText("1/" + list.size());
    }

    private void setFileList(String path) {
        File file = new File(path);
        File[] files = file.listFiles(); // 해당 경로의 파일 리스트 얻기
        for (File aFile : files) {
            if (isImage(aFile)) {
                list.add(aFile); // 왜 10번 파일부터 들어갈까?
//                Log.d("mytag", list.toString());
            }
        }
        Collections.sort(list); // 오름차순으로 정렬
    }

    private boolean isImage(File file) {
        String filename = file.getName();
        int index = filename.lastIndexOf(".");
        String ext = filename.substring(index + 1);
        if (ext.equalsIgnoreCase("jpg") ||
                ext.equalsIgnoreCase("png") ||
                ext.equalsIgnoreCase("gif")) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        String imgPath = "";
        if (view == btnPrev) {
            if (index == 0) {
                Toast.makeText(this, "첫번째 그림입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            imgPath = list.get(--index).getAbsolutePath();
        } else if (view == btnNext) {
            if (index == (list.size() - 1)) {
                Toast.makeText(this, "마지막 그림입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            imgPath = list.get(++index).getAbsolutePath();
        }
        Log.d("mytag", imgPath);
        orderCheck.setText((index + 1) + "/" + list.size());
        pictureView.setImagePath(imgPath);
        pictureView.invalidate(); // 다시그리기
    }
}