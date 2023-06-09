package com.kh.threadex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar1, seekBar2;
    Button button;
    Thread thread;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        button = findViewById(R.id.button);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 0.1초 간격으로 값을 각각 2, 1씩 증가
                for (int i = 0; i < 100; i++) {
                    int progress1 = seekBar1.getProgress() + 2;
                    if (progress1 > seekBar1.getMax()) progress1 = seekBar1.getMax();
                    int progress2 = seekBar2.getProgress() + 1;
                    if (progress2 > seekBar2.getMax()) progress2 = seekBar2.getMax();

                    seekBar1.setProgress(progress1);
                    seekBar2.setProgress(progress2);
                    SystemClock.sleep(100);

                    textView1.setText("1번 진행률 : " + progress1 + "%");
                    textView2.setText("2번 진행률 : " + progress2 + "%");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.start();
            }
        });
    }
}