package com.kh.listviewex2;

import static com.kh.listviewex2.R.layout.view_cell;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<NationDto> list = new ArrayList<>();
    String[] nations = {"대한민국", "몰디브", "싱가포르", "태국",
                        "가나", "이집트", "그리스", "네덜란드",
                        "모나코", "스위스", "미국", "캐나다", "호주"};
    String[] capitals = {"서울", "말레", "싱가포르", "방콕",
                        "아크라", "카이로", "아테네", "암스테르담",
                        "모나코", "베른", "워싱턴", "오타와", "캔버라"};
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        for (int i = 0; i < nations.length; i++) {
            int drawable = getResources().getIdentifier("flag" + (i+1),
                                                "drawable", getPackageName());
            String nation = nations[i];
            String capital = capitals[i];
            NationDto dto = new NationDto(drawable, nation, capital);
            list.add(dto);
        }
        adapter = new MyAdapter(getApplicationContext(), R.layout.view_cell, list);
        listView.setAdapter(adapter);
    }

}