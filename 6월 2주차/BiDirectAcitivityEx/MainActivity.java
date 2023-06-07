package com.kh.bidirectactivityex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edit1.getText().toString();
                String str2 = edit2.getText().toString();
                int num1 = Integer.valueOf(str1);
                int num2 = Integer.valueOf(str2);
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("num1", num1);
                intent.putExtra("num2", num2);
                startActivityForResult(intent, 1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = edit1.getText().toString();
                String str2 = edit2.getText().toString();
                int num1 = Integer.valueOf(str1);
                int num2 = Integer.valueOf(str2);
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("num1", num1);
                intent.putExtra("num2", num2);
                startActivityForResult(intent, 2);
            }
        });

    } // OnCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            String str = "";
            if (requestCode == 1) {
                str = "덧셈결과: ";
            } else if (requestCode == 2) {
                str = "뺄셈결과: ";
            }
            int result = intent.getIntExtra("result", 0);
            /* SecondActivity와 ThirdActivity에서 넘겨주는 값의 타입(int)과 이름("result")이 일치하면
            코드 한줄로 함께 사용 가능 */
            Toast.makeText(this, str + result, Toast.LENGTH_LONG).show();
        }
    }
}