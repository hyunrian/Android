package com.kh.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResultActivity extends AppCompatActivity {

    Button btnBack;
    int[] tvIds = {
            R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4,
            R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9
    };
    int[] rbIds = {
            R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4,
            R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9
    };
    TextView[] textViews = new TextView[tvIds.length];
    RatingBar[] ratingBars = new RatingBar[rbIds.length];

    int imgIds[] = new int[tvIds.length];

    TextView tvTop;
    ImageView imgTop;

//    HashMap<Integer, Integer> map = new HashMap<>();
    ArrayList<ImageSet> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnBack = findViewById(R.id.btnBack);

        // MainActivity에서 넘겨받은 intent 얻기
        Intent intent = getIntent();

        // putExtra로 집어넣은 데이터 꺼내기(집어넣은 데이터 타입과 일치해야 함)
        String[] imgNames = intent.getStringArrayExtra("imgNames");
        int[] counts = intent.getIntArrayExtra("counts");

        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = findViewById(tvIds[i]);
            ratingBars[i] = findViewById(rbIds[i]);
            textViews[i].setText(imgNames[i]);
            ratingBars[i].setRating(counts[i]);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                list.clear();
            }
        });

        imgTop = findViewById(R.id.imgTop);
        tvTop = findViewById(R.id.tvTop);

        for (int i = 0; i < counts.length; i++) {
            list.add(new ImageSet(imgNames[i], counts[i]));
        }
        Collections.sort(list, new ImageCountComparator());
        Log.d("mytag", list.toString());

        tvTop.setText(list.get(0).name);
        for (int i = 0; i < textViews.length; i++) {
            if (imgNames[i].equals(tvTop.getText())) {
                int id = getResources().getIdentifier("pic" + (i+1), "drawable", getPackageName());
                imgTop.setImageResource(id);
                break;
            }
        }
    }

    private class ImageSet {
        int count;
        String name;

        ImageSet(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    private class ImageCountComparator implements Comparator<ImageSet> {

        @Override
        public int compare(ImageSet is1, ImageSet is2) {
            if (is1.count > is2.count) // reverse를 하지 않고 기본 정렬에서 내림차순하려면 -1 리턴
                return -1;
            else if (is1.count < is2.count)
                return 1;
            else
//                Collections.sort(list, new ImageNameComparator().reversed()); // 이런 식의 n차 정렬은 되지 않음
                return is1.name.compareTo(is2.name);
        }
    }
//    private class ImageNameComparator implements Comparator<ImageSet> {
//
//        @Override
//        public int compare(ImageSet is1, ImageSet is2) {
//            return is1.name.compareTo(is2.name);
//        }
//    }
}