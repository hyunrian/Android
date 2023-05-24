package com.kh.checkboxex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox chkAndroid, chkIphone, chkWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkAndroid = findViewById(R.id.chkAndroid);
        chkIphone = findViewById(R.id.chkIphone);
        chkWin = findViewById(R.id.chkWin);

        chkAndroid.setOnCheckedChangeListener(this);
        chkIphone.setOnCheckedChangeListener(this);
        chkWin.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String who = null;
        if (compoundButton == chkAndroid) {
            who = "Android";
        } else if (compoundButton == chkIphone) {
            who = "Iphone";
        } else if (compoundButton == chkWin) {
            who = "Win";
        }
        String state = "";
        if (b) state = "checked";
        else state = "unchecked";
        Log.d("mytag", who + " " + state);
    }
}