package com.kh.textviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // resource.layout.activity_main
        TextView tv = findViewById(R.id.textView);
        tv.setText("그냥 텍스트뷰 그냥 텍스트뷰 그냥 텍스트뷰 그냥 텍스트뷰 그냥 텍스트뷰 그냥 텍스트뷰 그냥 텍스트뷰");
        tv.setTextColor(0xffff0000);
        tv.setTypeface(Typeface.SERIF);
        tv.setMaxLines(1);

        Button btnClick = findViewById(R.id.btnClick); // alt + enter : import Class
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mytag", "click"); // System.out.println(); // tag: Logcat에서 원하는 메시지만 검색해서 보기 위한 키워드
            }
        });
    }
}