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
    // xml에서 읽기 위해서는 attributeset이 있는 생성자를 사용해야 함
    // 자바에서만 사용하려면 context만 있는 생성자를 사용하면 됨

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas); // 반드시 작성되어 있어야 함
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath); // 파일을 이미지(bitmap)로 변환
            float width = getWidth();
            float height = getHeight();
            float imgWidth = bitmap.getWidth();
            float imgHeight = bitmap.getHeight();
            canvas.drawBitmap(bitmap, ((width-imgWidth)/2), ((height-imgHeight)/2), null);
            bitmap.recycle(); // 메모리 확보(필수 작성은 아님)

        }
    }
}
