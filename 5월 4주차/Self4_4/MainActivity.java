package com.kh.self4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Switch swStart;
    LinearLayout linearLayout;
    RadioGroup rGroup;
    ImageView imgVersion;
    Button btnExit, btnInit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("선호하는 안드로이드 버전");
        swStart = findViewById(R.id.swStart);
        linearLayout = findViewById(R.id.linearLayout);
        rGroup = findViewById(R.id.rGroup);
        imgVersion = findViewById(R.id.imgVersion);
        btnExit = findViewById(R.id.btnExit);
        btnInit = findViewById(R.id.btnInit);

        btnExit.setOnClickListener(this);
        btnInit.setOnClickListener(this);

        swStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swStart.isChecked()) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.INVISIBLE);
                }
                imgVersion.setVisibility(View.INVISIBLE);
            }
        });

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = rGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rdR:
                        imgVersion.setImageResource(R.drawable.r300);
                        break;
                    case R.id.rdS:
                        imgVersion.setImageResource(R.drawable.s300);
                        break;
                    case R.id.rdT:
                        imgVersion.setImageResource(R.drawable.t300);
                        break;
                }
                imgVersion.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnExit) {
            finish();
        } else if (view == btnInit) {
            swStart.setChecked(false);
            imgVersion.setVisibility(View.INVISIBLE);
            int i = rGroup.getCheckedRadioButtonId();
            rGroup.clearCheck();
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }
}