package com.kh.project5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt1, edt2;
    Button[] numButtons = new Button[10];
    Button btnAdd, btnSub, btnMul, btnDiv, btnDot, btnC;
    TextView txtResult;

    View.OnClickListener numButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            String strNum = button.getText().toString();

            EditText edit = null;
            if (edt1.isFocused()) edit = edt1;
            else if (edt2.isFocused()) edit = edt2;

            String eNum = edit.getText().toString();

            if (eNum.equals("0")) edit.setText("");
            edit.append(strNum);

            if (view == btnC) {
                edt1.setText("");
                edt2.setText("");
                return;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("계산기");
        setContentView(R.layout.activity_main);
        setUI();
        setListener();
    }

    private void setListener() {
        for (Button aButton : numButtons) {
            aButton.setOnClickListener(numButtonListener);
        }
        btnDot.setOnClickListener(numButtonListener);
        btnC.setOnClickListener(numButtonListener);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    private void setUI() {
        for (int i = 0; i < numButtons.length; i++) {
            int id = getResources().getIdentifier(
                        "btn" + i, "id", getPackageName());
            Log.d("mytag", String.valueOf(id));
            numButtons[i] = findViewById(id);
        }
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnDot = findViewById(R.id.btnDot);
        btnC = findViewById(R.id.btnC);
        txtResult = findViewById(R.id.txtResult);
    }

    @Override
    public void onClick(View view) {
        String str1 = "";
        String str2 = "";
        double num1 = 0;
        double num2 = 0;
        double result = 0;
        try{
            str1 = edt1.getText().toString();
            str2 = edt2.getText().toString();
            num1 = Double.valueOf(str1);
            num2 = Double.valueOf(str2);
        } catch (Exception e) {
            Toast.makeText(this, "정확한 값을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (view == btnAdd) {
            result = num1 + num2;
        } else if (view == btnSub) {
            result = num1 - num2;
        } else if (view == btnMul) {
            result = num1 * num2;
        } else if (view == btnDiv) {
            if (num2 == 0) {
                Toast.makeText(
                        this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            result = num1 / num2;
        }
        txtResult.setText("계산결과 : " + result);
    }
}