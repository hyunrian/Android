package com.kh.checkboxex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox chkAndroid, chkIphone, chkWin;
    Switch swAlarm;
    ToggleButton tbSave;
    RadioGroup rGroup;
    RadioButton rdMale, rdFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("컴파운드 버튼 예제");

        chkAndroid = findViewById(R.id.chkAndroid);
        chkIphone = findViewById(R.id.chkIphone);
        chkWin = findViewById(R.id.chkWin);
        swAlarm = findViewById(R.id.swAlarm);
        tbSave = findViewById(R.id.tbSave);
        rGroup = findViewById(R.id.rGroup);
        rdMale = findViewById(R.id.rdMale);
        rdFemale = findViewById(R.id.rdFemale);


        chkAndroid.setOnCheckedChangeListener(this);
        chkIphone.setOnCheckedChangeListener(this);
        chkWin.setOnCheckedChangeListener(this);
        swAlarm.setOnCheckedChangeListener(this);
        tbSave.setOnCheckedChangeListener(this);
        rdMale.setOnCheckedChangeListener(this);
        rdFemale.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String who = null;
        if (compoundButton == chkAndroid) {
            who = "Android";
        } else if (compoundButton == chkIphone) {
            who = "iPhone";
        } else if (compoundButton == chkWin) {
            who = "WindowPhone";
        } else if (compoundButton == swAlarm) {
            who = "Switch";
        } else if (compoundButton == tbSave) {
            who = "ToggleButton";
        } else if (compoundButton == rdMale) {
            who = "Male";
        } else if (compoundButton == rdFemale) {
            who = "Female";
        }
        String state = "";
        if (b) state = "checked";
        else state = "unchecked";
        Log.d("mytag", who + " " + state);

        // 라디오그룹으로부터 현재 체크된 라디오 버튼 아이디를 얻을 수 있음
        int id = rGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.rdMale:
                Log.d("rdd", "rdGroup: male");
                break;
            case R.id.rdFemale:
                Log.d("rdd", "rdGroup: female");
                break;
        }
    }
}