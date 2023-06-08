package com.kh.gridviewex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyAdapter adapter;
    List<MovieDto> list = new ArrayList<>();
    String[] titles = {"토이스토리4", "호빗", "제이슨본", "반지의 제왕",
                        "정직한 후보", "나쁜녀석들", "겨울왕국",
                        "알라딘", "극한직업", "스파이더맨"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);

        for (int i = 0; i < 100; i++) {
            int drawable = getResources().getIdentifier("mov" + ((i%10)+1),
                                                "drawable", getPackageName());
            MovieDto dto = new MovieDto(drawable, titles[i%10]);
            list.add(dto);
        }
//        Log.d("mytag", list.toString());
        adapter = new MyAdapter(this, R.layout.view_cell, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 클릭했을 때 뜰 다이얼로그 설정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieDto dto = list.get(i);
                int drawable = dto.getDrawable();
                String title = dto.getTitle();

                View dialogView = View.inflate(MainActivity.this, R.layout.view_dialog, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle(title);
                ImageView imageView = dialogView.findViewById(R.id.imageView);
                imageView.setImageResource(drawable);

                dialog.setView(dialogView);
                dialog.setPositiveButton("닫기", null);
                dialog.show();
            }
        });
    }
}