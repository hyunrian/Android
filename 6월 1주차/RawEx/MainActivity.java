package com.kh.rawex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        text = findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream is = getResources().openRawResource(R.raw.raw);
                byte[] bytes = new byte[100];
                try {
                    is.read(bytes);
                    String str = new String(bytes);
                    text.setText(str);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}