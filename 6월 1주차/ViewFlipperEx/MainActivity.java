package com.kh.viewflipperex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPrev, btnNext;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        setListener();
    }

    private void setListener() {
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void setUI() {
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        viewFlipper = findViewById(R.id.viewFlipper);
    }

    @Override
    public void onClick(View view) {
        if (view == btnPrev) {
            viewFlipper.showPrevious();
        } else if (view == btnNext) {
            viewFlipper.showNext();
        }
    }
}