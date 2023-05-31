package com.kh.project7_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("사용자 정보 입력");
                // 뷰 작업
                View dialogView = View.inflate(MainActivity.this, R.layout.view_dialog, null);
                dialog.setView(dialogView);

                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editName = dialogView.findViewById(R.id.editName);
                        EditText editEmail = dialogView.findViewById(R.id.editEmail);
                        String name = editName.getText().toString();
                        String email = editEmail.getText().toString();
                        tvName.setText(name);
                        tvEmail.setText(email);
                        Toast.makeText(MainActivity.this, "사용자 " + name + "님의 정보가 등록되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(MainActivity.this, null, Toast.LENGTH_SHORT);
                        // toast view 작업
                        View toastView = View.inflate(MainActivity.this, R.layout.view_toast, null);
                        toast.setView(toastView);

                        toast.show();
                    }
                });
                dialog.show();
            }
        });
    }
}