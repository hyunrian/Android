package com.kh.prac8_7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class PictureView extends View {
    String imagePath = "";

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            float width = getWidth();
            float height = getHeight();
            float imgWidth = bitmap.getWidth();
            float imgHeight = bitmap.getHeight();
            canvas.drawBitmap(bitmap, ((width-imgWidth)/2), ((height-imgHeight)/2), null);
            bitmap.recycle();

        }
    }
}
