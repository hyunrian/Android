package com.kh.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Button btnBack;
    int[] tvIds = {
            R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4,
            R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9
    };
    int[] rbIds = {
            R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4,
            R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9
    };
    TextView[] textViews = new TextView[tvIds.length];
    RatingBar[] ratingBars = new RatingBar[rbIds.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnBack = findViewById(R.id.btnBack);

        // MainActivity에서 넘겨받은 intent 얻기
        Intent intent = getIntent();

        // putExtra로 집어넣은 데이터 꺼내기(집어넣은 데이터 타입과 일치해야 함)
        String[] imgNames = intent.getStringArrayExtra("imgNames");
        int[] counts = intent.getIntArrayExtra("counts");

        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = findViewById(tvIds[i]);
            ratingBars[i] = findViewById(rbIds[i]);
            textViews[i].setText(imgNames[i]);
            ratingBars[i].setRating(counts[i]);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}