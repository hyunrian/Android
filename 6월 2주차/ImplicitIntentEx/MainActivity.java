package com.kh.implicitintentex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDial, btnPage, btnMap, btnSearch, btnText, btnPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = findViewById(R.id.btnDial);
        btnPage = findViewById(R.id.btnPage);
        btnMap = findViewById(R.id.btnMap);
        btnSearch = findViewById(R.id.btnSearch);
        btnText = findViewById(R.id.btnText);
        btnPicture = findViewById(R.id.btnPicture);

        setListener();
    }

    private void setListener() {
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:01012345678");
                // uri에 따라 알맞는 (안드로이드에서 제공하는) 기존 응용 프로그램 실행
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                // 실행할 특정 intent를 지정하지 않음 -> 암시적 인텐트(implicit intent)
                startActivity(intent);
            }
        });
        btnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://m.naver.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://maps.google.co.kr/maps?q="
                                    + 37.559133 + "," + 126.927824); // 위도와 경도
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}