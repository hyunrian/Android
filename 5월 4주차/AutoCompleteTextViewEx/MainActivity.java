package com.kh.autocompletetextviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView auto;
    MultiAutoCompleteTextView multi;
    String[] data = {
            "가나다", "가나다라", "가위",
            "나다라", "나다라마", "나비"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auto = findViewById(R.id.auto);
        multi = findViewById(R.id.multi);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, //context
                android.R.layout.simple_dropdown_item_1line, //view. 안드로이드가 제공하는 기본 레이아웃 사용
                data //data
        );
        auto.setAdapter(adapter);

        MultiAutoCompleteTextView.CommaTokenizer tokenizer =
                new MultiAutoCompleteTextView.CommaTokenizer();
        multi.setTokenizer(tokenizer);
        multi.setAdapter(adapter);
    }
}