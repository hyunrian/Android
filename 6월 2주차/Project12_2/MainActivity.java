package com.kh.project12_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtNumber, edtLeft, edtRight;
    Button btnInit, btnInsert, btnSelect, btnUpdate, btnDelete;
    MyHelper helper;
    GroupDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setListener();
        helper = new MyHelper(getApplicationContext(), "group.db", null, 2);
        dao = new GroupDao(helper);
    }

    private void setListener() {
        btnInit.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void setUI() {
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtLeft = findViewById(R.id.edtLeft);
        edtRight = findViewById(R.id.edtRight);
        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void onClick(View view) {
        if (view == btnInit) {
            helper.getWritableDatabase();
            helper.close();
        } else if (view == btnInsert) {
            try {
                String name = edtName.getText().toString().trim();
                int number = Integer.valueOf(edtNumber.getText().toString().trim());
                GroupVo vo = new GroupVo(name, number);
                boolean result = dao.insert(vo);
                if (result) {
                    Toast.makeText(this, "입력 완료", Toast.LENGTH_SHORT).show();
                    textInit();
                    btnSelect.callOnClick(); // 조회 버튼 클릭하지 않고 즉시 변경 결과 보이게 하기
                }
            } catch (Exception e) {
                showAlert();
            }
//            dao.insert(null);
        } else if (view == btnSelect) {
            edtLeft.setText("그룹명\n------------------------------\n\n");
            edtRight.setText("인원수\n------------------------------\n\n");
            List<GroupVo> list = dao.select();
            for (GroupVo vo : list) {
                String g_name = vo.getG_name();
                int g_number = vo.getG_number();
                edtLeft.append(g_name + "\n");
                edtRight.append(g_number + "\n");
            }
            textInit();
        } else if (view == btnUpdate) {
            try {
                String name = edtName.getText().toString().trim();
                int number = Integer.valueOf(edtNumber.getText().toString().trim());
                boolean result = dao.update(name, number);
                if (result) {
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    textInit();
                    btnSelect.callOnClick();
                }
            } catch (Exception e){
                showAlert();
            }
        } else if (view == btnDelete) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("삭제할 그룹의 이름을 입력하세요");
            View dialogView = View.inflate(this, R.layout.activity_my_dialog, null);
            dialog.setNegativeButton("닫기", null);
            dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText edtDialog = dialogView.findViewById(R.id.edtDialog);
                    String name = edtDialog.getText().toString().trim();
                    if (name == null || name.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "삭제할 그룹의 이름을 입력하세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean result = dao.delete(name);
                    if (result) {
                        Toast.makeText(getApplicationContext(), "삭제 완료", Toast.LENGTH_SHORT).show();
                        textInit();
                        btnSelect.callOnClick();
                    }
                }
            });
            dialog.setView(dialogView);
            dialog.show();

//            String name = edtName.getText().toString().trim();
//            if (name == null || name.equals("")) {
//                Toast.makeText(this,
//                        "삭제할 그룹의 이름을 입력하세요", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            boolean result = dao.delete(name);
//            if (result) {
//                Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
//                textInit();
//                btnSelect.callOnClick();
//            }
        }
    } //OnClick

    private void textInit() {
        edtName.setText("");
        edtNumber.setText("");
    }

    private void showAlert() {
        Toast.makeText(this, "정확한 값을 입력하세요", Toast.LENGTH_SHORT).show();
    }

}