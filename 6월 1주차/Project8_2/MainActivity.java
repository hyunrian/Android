package com.kh.project8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPrev, btnNext;
    PictureView pictureView;
    ArrayList<File> list = new ArrayList<>();
    final String SDCARD_PIC = "/sdcard/Pictures";
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(); // sd card 접근을 위한 메서드

        setUI();
        setListener();
        setFileList(SDCARD_PIC);
        Log.d("mytag", "list:" + list);
        String imagePath = SDCARD_PIC + File.separator + list.get(index).getName(); // File.separator == "/"
        pictureView.setImagePath(imagePath);

        // Swing의 repaint()
        pictureView.invalidate(); // 무효화 (다시그리기) -> onDraw() 호출
    } //OnCreate

    private void setFileList(String path) {
        File f = new File(path);
        File[] files = f.listFiles();
        for (File aFile : files) {
            if (isImage(aFile)) {
                list.add(aFile);
            }
        }
    }

    private boolean isImage(File f) { // 이미지 파일 여부를 알아내기 위한 메서드
        String fileName = f.getName();
        // 확장자 구하기
        int dotIndex = fileName.lastIndexOf(".");
        String extName = fileName.substring(dotIndex + 1);

        if (extName.equalsIgnoreCase("jpg") ||
                extName.equalsIgnoreCase("png") ||
                extName.equalsIgnoreCase("gif"))
            return true;
        return false;
    }

    private void setListener() {
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void setUI() {
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        pictureView = findViewById(R.id.pictureView);
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        String imagePath = "";
        if (view == btnPrev) {
            if (index == 0) {
                Toast.makeText(this, "첫번째 그림입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            imagePath = list.get(--index).getAbsolutePath();
        } else if (view == btnNext) {
            if (index == list.size() - 1) {
                Toast.makeText(this, "마지막 그림입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            imagePath = list.get(++index).getAbsolutePath();
        }
        pictureView.setImagePath(imagePath);
        pictureView.invalidate();
    }
}