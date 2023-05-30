package com.kh.toastex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseLayout = findViewById(R.id.baseLayout);
    }

    public void showToast(View v) {
        int width = baseLayout.getWidth();
        int height = baseLayout.getHeight();
        Log.d("mytag", "width:" + (int)(Math.random() * width) + ", height:" + (int)(Math.random() * height));

        View toastView = View.inflate(this, R.layout.view_toast, null);

        Toast toast = Toast.makeText(this, "메시지", Toast.LENGTH_SHORT);
        toast.setView(toastView);

        // setGravity() : R(30) 버전 이상 동작 안함. 단, 뷰를 설정하면(사용자 정의 레이아웃 적용) 동작함
        toast.setGravity(Gravity.LEFT|Gravity.TOP,
                (int)(Math.random() * width),
                (int)(Math.random() * height));
        toast.show();
    }
}