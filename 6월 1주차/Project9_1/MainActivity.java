package com.kh.project9_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    int startX, startY, endX, endY, radius;
    MyDrawView drawView; // enum 사용
    ShapeType curMode = ShapeType.LINE;
    ColorType curColor = ColorType.RED;
    Paint paint;
    int userR, userG, userB;
    ArrayList<ShapeDrawing> list = new ArrayList<>();

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
                curMode = ShapeType.LINE;
                break;
            case R.id.itemCircle:
                curMode = ShapeType.CIRCLE;
                break;
            case R.id.itemRect:
                curMode = ShapeType.RECT;
                break;
            case R.id.itemRed:
                curColor = ColorType.RED;
                break;
            case R.id.itemGreen:
                curColor = ColorType.GREEN;
                break;
            case R.id.itemBlue:
                curColor = ColorType.BLUE;
                break;
            case R.id.itemUserVal:
                curColor = ColorType.USERVAL;
                View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("컬러 값을 입력하세요");
                dialog.setView(dialogView);
                EditText txtR = dialogView.findViewById(R.id.userR);
                EditText txtG = dialogView.findViewById(R.id.userG);
                EditText txtB = dialogView.findViewById(R.id.userB);
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userR = Integer.valueOf(txtR.getText().toString());
                        userG = Integer.valueOf(txtG.getText().toString());
                        userB = Integer.valueOf(txtB.getText().toString());
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();
                break;
        }
        return true;
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
                if (curMode == ShapeType.LINE) {
                    list.add(new ShapeDrawing(startX, startY, endX, endY, paint, curMode, curColor));
                } else if (curMode == ShapeType.CIRCLE) {
                    list.add(new ShapeDrawing(startX, startY, radius, paint, curMode, curColor));
                } else if (curMode == ShapeType.RECT) {
                    list.add(new ShapeDrawing(startX, startY, endX, endY, paint, curMode, curColor));
                }
                break;
        }
        return true;
    }

    private class MyDrawView extends View {
//        ColorType prevColor;

        public MyDrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            redraw(canvas);

            paint = new Paint();
            setUserColor(curColor, paint);
            if (curMode == ShapeType.LINE) {
                canvas.drawLine(startX, startY, endX, endY, paint);
            } else if (curMode == ShapeType.CIRCLE) {
                // 반지름 구하기 (a^2 + b^2 = c^2)
                paint.setStyle(Paint.Style.STROKE);
                radius = (int)(Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)));
                canvas.drawCircle(startX, startY, radius, paint);
            } else if (curMode == ShapeType.RECT) {
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawRect(startX, startY, endX, endY, paint);
            }
        }

        private void redraw(Canvas canvas) { // 전에 그렸던 도형 다시 그리기
            for (int i = 0; i < list.size(); i++) {
                ShapeDrawing sd = list.get(i);
                setUserColor(sd.color, sd.paint);
                if (sd.shape == ShapeType.LINE) {
                    canvas.drawLine(sd.startX, sd.startY, sd.endX, sd.endY, sd.paint);
                } else if (sd.shape == ShapeType.CIRCLE) {
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawCircle(sd.startX, sd.startY, sd.radius, sd.paint);
                } else if (sd.shape == ShapeType.RECT) {
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawRect(sd.startX, sd.startY, sd.endX, sd.endY, sd.paint);
                }
//                invalidate();
            }
        }

        private void setUserColor(ColorType curColor, Paint paint) {
            if (curColor == ColorType.RED) {
                paint.setColor(Color.RED);
            } else if (curColor == ColorType.GREEN) {
                paint.setColor(Color.GREEN);
            } else if (curColor == ColorType.BLUE) {
                paint.setColor(Color.BLUE);
            } else if (curColor == ColorType.USERVAL) {
                paint.setColor(Color.rgb(userR, userG, userB));
            }
        }

    }//MyDrawView

    private class ShapeDrawing {
        int startX, startY, endX, endY, radius;
        ShapeType shape;
        ColorType color;
        Paint paint;

        ShapeDrawing(int startX, int startY, int endX, int endY, Paint paint, ShapeType shape, ColorType color) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.paint = paint;
            this.shape = shape;
        }
        ShapeDrawing(int startX, int startY, int radius, Paint paint, ShapeType shape, ColorType color) {
            this.startX = startX;
            this.startY = startY;
            this.radius = radius;
            this.paint = paint;
            this.shape = shape;
        }
    }
}
