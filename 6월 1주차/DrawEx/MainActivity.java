package com.kh.drawex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Canvas (도화지 역할)  /  Paint (붓 역할)
            Paint paint = new Paint();
            paint.setAntiAlias(true); // 계단 현상 제거(거의 필수로 사용함. 그러나 차이가 없어 보임..!)
            paint.setColor(Color.DKGRAY);
            canvas.drawLine(20, 20, 600, 20, paint);

            // 선
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10); // 붓의 굵기. 0은 기본값 1픽셀
            canvas.drawLine(20, 60, 600, 60, paint);


            // 사각형
            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            paint.setStyle(Paint.Style.STROKE); // 테두리 그리기 (fill도 이 메서드로 설정)
            canvas.drawRect(20, 100, 20 + 200, 100 + 200, paint); // 가로 세로 200짜리 사각형

            paint.setStyle(Paint.Style.FILL); // 채우기
            canvas.drawRect(260, 100, 260 + 200, 100 + 200, paint);

            // 둥근사각형
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(480, 100,
                    480 + 200, 100 + 200, 50, 50, paint);

            // 원
            canvas.drawCircle(120, 420, 100, paint); // 중심점 위치, 반지름 크기

            // 경로 그리기
            Path path = new Path();
            path.moveTo(20, 540);
            path.lineTo(20 + 100, 540 + 100);
            path.lineTo(20 + 200, 540);
            path.lineTo(20 + 300, 540 + 100);
            path.lineTo(20 + 400, 540);
            canvas.drawPath(path, paint);

        }
    }
}