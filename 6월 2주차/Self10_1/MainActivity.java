package com.kh.self10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button btnNew;
    RadioGroup rdGroup;
    final int SECOND = 1;
    final int THIRD = 2;
    int status = SECOND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main");

        btnNew = findViewById(R.id.btnNew);
        rdGroup = findViewById(R.id.rdGroup);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                switch (status) {
                    case SECOND :
                        intent = new Intent(getApplicationContext(), SecondActivity.class);
                        break;
                    case THIRD :
                        intent = new Intent(getApplicationContext(), ThirdActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = rdGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rdSecond:
                        status = SECOND;
                        break;
                    case R.id.rdThird:
                        status = THIRD;
                        break;
                }
            }
        });
    }
}