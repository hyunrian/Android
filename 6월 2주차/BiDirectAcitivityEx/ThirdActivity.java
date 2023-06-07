package com.kh.bidirectactivityex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    Button button;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // intent를 새로 만들어서 MainActivity로 데이터 전달
                intent.putExtra("result", result);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Intent intent = getIntent(); // MainActivity에서 보내진 intent 받기
        int num1 = intent.getIntExtra("num1", 0);
        // defaultValue : 값이 없을 때 기본으로 설정할 값
        int num2 = intent.getIntExtra("num2", 0);
        result = num1 - num2;
    }
}