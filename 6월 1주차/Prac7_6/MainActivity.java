package com.kh.prac7_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup group;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("귀여운 동물 사진");
        setUI();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                View dialogView = View.inflate(MainActivity.this, R.layout.view_dialog, null);
                dialog.setView(dialogView);

                dialog.setIcon(null);
                int id = group.getCheckedRadioButtonId();
                RadioButton selectedBtn = findViewById(id); // 다이얼로그 타이틀 설정값 얻기
                String txt = selectedBtn.getText().toString();
                dialog.setTitle(txt);
                ImageView image = dialogView.findViewById(R.id.image);

                switch (id) {
                    case R.id.btnDog:
                        image.setImageResource(R.drawable.dog);
                        break;
                    case R.id.btnCat:
                        image.setImageResource(R.drawable.cat);
                        break;
                    case R.id.btnRabbit:
                        image.setImageResource(R.drawable.rabbit);
                        break;
                    case R.id.btnFox:
                        image.setImageResource(R.drawable.fox);
                        break;
                }
                dialog.setPositiveButton("닫기", null);
                dialog.show();
            }
        });
    } // onCreate

    private void setUI() {
        button = findViewById(R.id.button);
        group = findViewById(R.id.group);
    }
}