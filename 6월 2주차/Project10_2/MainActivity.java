package com.kh.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int MAX_COUNT = 5;

    // 그림의 이름을 저장할 배열 만들기
    String[] imgNames = {"독서하는 소녀", "꽃장식 모자 소녀",
            "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매",
            "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

    // imageView id 저장할 배열 만들기
    int[] imgIds = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4,
            R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};

    // 득표수를 저장할 배열 만들기
    int[] counts = new int[imgNames.length]; // 0으로 값 초기화되어 있음

    // imageView 저장할 배열 만들기
    ImageView[] imageViews = new ImageView[imgNames.length];

    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = findViewById(imgIds[i]);
            imageViews[i].setOnClickListener(this);
        }

        btnResult = findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ResultActivity로 이미지 이름과 득표수를 넘겨줌
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                // putExtra(key, value)
                intent.putExtra("imgNames", imgNames);
                intent.putExtra("counts", counts);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < imageViews.length; i++) {
            if (imageViews[i] == view) {
                if (counts[i] < MAX_COUNT) {
                    counts[i]++;
                }
                String msg = imgNames[i] + " - " + counts[i] + "표";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                break;
            }
        }

        //count 확인 코드
//        for (int i = 0; i < counts.length; i++) {
//            Log.d("mytag", i + ":" + counts[i]);
//        }

    }
}