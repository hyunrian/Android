package com.kh.project8_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PictureView extends View {
    private String imagePath;

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Swing의 paintComponent(Graphics g) 역할
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath); // imagePath에 있는 파일을 bitmap으로 변환
            canvas.drawBitmap(bitmap, 0, 0, null); // paint : 컬러 관련
            bitmap.recycle(); // 메모리 확보를 위함(필수 작성은 아님)
        }
    }
}
