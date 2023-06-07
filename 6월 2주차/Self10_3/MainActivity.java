package com.kh.self10_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    RadioGroup rdGroup;
    Button button;
    final public int ADD = 0;
    final public int SUB = 1;
    final public int MUL = 2;
    final public int DIV = 3;
    int status = ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        rdGroup = findViewById(R.id.rdGroup);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float num1 = Float.valueOf(edit1.getText().toString());
                float num2 = Float.valueOf(edit2.getText().toString());
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("num1", num1);
                intent.putExtra("num2", num2);
                intent.putExtra("status", status);
                startActivityForResult(intent, 0);
            }
        });

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = rdGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rdAdd:
                        status = ADD;
                        break;
                    case R.id.rdSub:
                        status = SUB;
                        break;
                    case R.id.rdMul:
                        status = MUL;
                        break;
                    case R.id.rdDiv:
                        status = DIV;
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            float result = intent.getFloatExtra("result", 0);
            Toast.makeText(this, "결과 : " + result, Toast.LENGTH_LONG).show();
        }
    }
}