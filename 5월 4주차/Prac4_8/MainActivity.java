package com.kh.prac4_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        text.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
            String str = text.getText().toString();
            Log.d("mytext", "KeyEvent - Action Up");
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
        // 마우스로 폰 키보드 클릭 시 작동 안함. 실제 키보드로 입력해야 작동
        return false;
    }
}