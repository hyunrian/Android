package com.kh.listdialogex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    String[] data = {"Q(10)", "R(11)", "S(12)"};
    boolean[] checkedItems = {false, false, false};
    int selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =
                        new AlertDialog.Builder(MainActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("좋아하는 버전은?");

                // 목록 다이얼로그
                /*
                dialog.setItems(data, new DialogInterface.OnClickListener() { // CharSequence : String의 상위 타입
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("mytag", i+""); // i : data의 선택된 인덱스(0부터 시작)
                        String text = data[i];
                        button.setText(text);
                    }
                });
                 */

                // 라디오버튼 다이얼로그
                /*
                dialog.setSingleChoiceItems(data, selectedIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String text = data[i];
                        button.setText(text);
                        selectedIndex = i;
                    }
                });
                 */

                // 체크박스 다이얼로그
                dialog.setMultiChoiceItems(data, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        String text = data[i];
                        button.setText(text);
//                        checkedItems[i] = b; // 이 코드가 없이도 선택된 값이 저장되어 있음!
                    }
                });
                dialog.setPositiveButton("닫기", null);
                dialog.show();
            }
        });
    }
}