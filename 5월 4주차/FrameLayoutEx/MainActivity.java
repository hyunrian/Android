package com.kh.framelayoutex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRed, btnGreen, btnBlue, red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setListener();
    }

    private void setListener() {
        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
    }

    private void setUI() {
        btnRed = findViewById(R.id.btnRed);
        btnGreen = findViewById(R.id.btnGreen);
        btnBlue = findViewById(R.id.btnBlue);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
    }

    private void hideAll() {
        red.setVisibility(View.INVISIBLE);
        green.setVisibility(View.INVISIBLE);
        blue.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        hideAll();
        if (view == btnRed) {
            red.setVisibility(View.VISIBLE);
        } else if (view == btnGreen) {
            green.setVisibility(View.VISIBLE);
        } else if (view == btnBlue) {
            blue.setVisibility(View.VISIBLE);
        }
    }
}