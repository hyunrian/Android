package com.kh.prac5_6;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static FrameLayout large, medium, small, main;
    FrameLayout layout = null;
//    MyThread th = new MyThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 5-6");

        large = findViewById(R.id.large);
        medium = findViewById(R.id.medium);
        small = findViewById(R.id.small);
        main = findViewById(R.id.main);

        large.setOnClickListener(this);
        medium.setOnClickListener(this);
        small.setOnClickListener(this);
        main.setOnClickListener(this);
    }

    @Override
    public synchronized void onClick(View view) {
//        if (view == large) layout = large;
//        else if (view == medium) layout = medium;
//        else if (view == small) layout = small;
//        else if (view == main) layout = main;
//        th.start();
        Toast.makeText(this,
                "선택한 레이아웃의 너비는 " + view.getWidth() + ",\n높이는 " + view.getHeight() + "입니다.",
                Toast.LENGTH_SHORT).show();
    }

//    class MyThread extends Thread {
//        @Override
//        public void run() {
//            Log.d("mytag", "Thread running");
//            for (int i = 1; i < 0; i--) {
//                if (i == 0) return;
//                layout.setAlpha(i);
//                try {sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
//            }
//            for (int i = 0; i > 1; i++) {
//                if (i == 1) return;
//                layout.setAlpha(i);
//                try {sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
//            }
//
//        }
//    } 안드로이드로 스레드를 구현하려면 Java에서 하던 것과 다른 방식으로 해야 함(UI 변경이 막혀있음)
}
