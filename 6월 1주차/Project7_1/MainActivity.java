package com.kh.project7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = findViewById(R.id.baseLayout);
        button = findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.rgb(192, 57, 43));
                break;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.rgb(22, 160, 133));
                break;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.rgb(41, 128, 185));
                break;
            case R.id.itemRotate:
                button.setRotation(button.getRotation() + 45);
                break;
            case R.id.itemScale:
                button.setScaleX(button.getScaleX() * 2);
                button.setScaleY(button.getScaleY() * 2);
                break;
        }
        return true;
    }
}