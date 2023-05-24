package com.kh.project4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit1; EditText edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        setListener();
    }

    private void setListener() {
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnRem.setOnClickListener(this);
    }

    private void setUI() {
        edit1 = findViewById(R.id.edit1); // View로 return되지만 자동형변환됨
        edit2 = findViewById(R.id.edit2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnRem = findViewById(R.id.btnRem);
        textResult = findViewById(R.id.textResult);
    }

    @Override
    public void onClick(View view) {
        try {
            String val1 = edit1.getText().toString().trim();
            String val2 = edit2.getText().toString().trim();
            double num1 = Double.valueOf(val1);
            double num2 = Double.valueOf(val2);
            double result = 0;

            if (view == btnAdd) {
                result = num1 + num2;
            } else if (view == btnSub) {
                result = num1 - num2;
            } else if (view == btnMul) {
                result = num1 * num2;
            } else if (view == btnDiv) {
                if (num2 == 0) {
                    Toast.makeText(getApplicationContext(),
                            "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = (double)num1 / num2;
            } else if (view == btnRem) {
                if (num2 == 0) {
                    Toast.makeText(getApplicationContext(),
                            "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1 % num2;
            }
            result = Math.round(result * 1000) / 1000.0;
            textResult.setText("계산결과: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),
                    "값을 입력하세요.", Toast.LENGTH_SHORT).show();
        }
//        catch (ArithmeticException e) {
//            Toast.makeText(getApplicationContext(),
//                    "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
//            Log.v("mytag", "arithmetic exception");
//        } 0으로 나눴을 때 계산결과가 infinity로 뜸 -> 예외가 발생하는 것이 아니기 때문에 예외처리를 하지 않음
    }
}