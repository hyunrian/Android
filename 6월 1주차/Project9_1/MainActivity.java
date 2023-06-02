package com.kh.project9_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    int startX, startY, endX, endY;
    MyDrawView drawView;
    final int LINE = 1;
    final int CIRCLE = 2;
    int curMode = LINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new MyDrawView(this);
        setContentView(drawView);
        setTitle("간단 그림판");
        drawView.setOnTouchListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemLine:
                curMode = LINE;
                break;
            case R.id.itemCircle:
                curMode = CIRCLE;
                break;
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = (int)motionEvent.getX();
                startY = (int)motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = (int)motionEvent.getX();
                endY = (int)motionEvent.getY();
                drawView.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private class MyDrawView extends View {
        public MyDrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            if (curMode == LINE) {
                canvas.drawLine(startX, startY, endX, endY, paint);
            } else if (curMode == CIRCLE) {
                // 반지름 구하기 (a^2 + b^2 = c^2)
                paint.setStyle(Paint.Style.STROKE);
                int radius = (int)(Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)));
                canvas.drawCircle(startX, startY, radius, paint);
            }
        }
    }
}
