package com.kh.self10_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    Button button;
    int status;
    float result, num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = findViewById(R.id.button);

        Intent intent = getIntent();
        num1 = intent.getFloatExtra("num1", 0);
        num2 = intent.getFloatExtra("num2", 0);
        status = intent.getIntExtra("status", 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                switch (status) {
                    case 0 :
                        result = num1 + num2;
                        break;
                    case 1:
                        result = num1 - num2;
                        break;
                    case 2:
                        result = num1 * num2;
                        break;
                    case 3:
                        result = num1 / num2;
                        break;
                }
                intent.putExtra("result", result);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}