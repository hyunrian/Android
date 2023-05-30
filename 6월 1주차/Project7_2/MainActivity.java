package com.kh.project7_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnBackground, btnButton;
    LinearLayout red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBackground = findViewById(R.id.btnBackground);
        btnButton = findViewById(R.id.btnButton);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);

        // 위젯을 컨텍스트 메뉴로 등록
        registerForContextMenu(btnBackground);
        registerForContextMenu(btnButton);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        if (v == btnBackground) {
            inflater.inflate(R.menu.mnu_background, menu);
        } else if (v == btnButton) {
            inflater.inflate(R.menu.mnu_button, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemRed:
                red.setVisibility(View.VISIBLE);
                green.setVisibility(View.INVISIBLE);
                blue.setVisibility(View.INVISIBLE);
                break;
            case R.id.itemGreen:
                red.setVisibility(View.INVISIBLE);
                green.setVisibility(View.VISIBLE);
                blue.setVisibility(View.INVISIBLE);
                break;
            case R.id.itemBlue:
                red.setVisibility(View.INVISIBLE);
                green.setVisibility(View.INVISIBLE);
                blue.setVisibility(View.VISIBLE);
                break;
            case R.id.itemRotate:
                btnButton.setRotation(btnButton.getRotation() + 45);
                break;
            case R.id.itemScale:
                btnButton.setScaleX(btnButton.getScaleX() * 2);
                btnButton.setScaleY(btnButton.getScaleY() * 2);
                break;
        }
        return true;
    }
}