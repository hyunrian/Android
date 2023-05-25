package com.kh.prac4_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox chkEnabled, chkClickable, chkRotate;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkClickable = findViewById(R.id.chkClickable);
        chkEnabled = findViewById(R.id.chkEnabled);
        chkRotate = findViewById(R.id.chkRotate);
        button = findViewById(R.id.button);

        chkClickable.setOnCheckedChangeListener(this);
        chkRotate.setOnCheckedChangeListener(this);
        chkEnabled.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (chkEnabled.isChecked()) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
        if (chkClickable.isChecked()) {
            button.setClickable(true);
        } else {
            button.setClickable(false);
        }
        if (chkRotate.isChecked()) {
            button.setRotation(45);
        } else {
            button.setRotation(0);
        }
    }
}