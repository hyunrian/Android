package com.kh.prac11_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyAdapter adapter;
    ArrayList<Integer> list = new ArrayList<>();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        button = findViewById(R.id.button);

        for (int i = 0; i < 10; i++) {
            int drawable = getResources().getIdentifier("pic" + (i+1), "drawable", getPackageName());
            list.add(drawable);
        }

        adapter = new MyAdapter(this, R.layout.view_cell, list);
        gridView.setAdapter(adapter);

    }
}