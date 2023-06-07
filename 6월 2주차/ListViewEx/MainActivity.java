package com.kh.listviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] data = {
            "가", "나", "다", "라", "마", "바", "사", "아", "자", "차"
    }; // 화면에 다 들어오지 않는 길이면 자동으로 스크롤처리됨
    EditText editText;
    Button button;
    List<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

//        list = Arrays.asList(data); // 배열을 리스트로 변경
        // Arrays.asList()로 만든 List는 요소 추가 불가(read only)!!
        list = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem = editText.getText().toString();
                list.add(newItem);
                adapter.notifyDataSetChanged(); // adapter에게 데이터가 추가되었음을 알림
            }
        });

        adapter = new ArrayAdapter<>(this,
                                android.R.layout.simple_list_item_1, list);
        // simple_list_item_1 : 안드로이드에서 제공하는 기본 리스트 중 하나
        listView.setAdapter(adapter);

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice, data);
        // single_choice : 기본 리스트. 라디오버튼 형태
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 이 설정을 해야 라디오버튼 선택이 활성화됨
        listView.setAdapter(adapter);
         */
        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, data);
        // multiple_choice : 기본 리스트. 체크박스 형태
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
         */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("mytag", "i:" + i + ", l:" + l); // -> i : index, l : 항목 아이디
                Toast.makeText(MainActivity.this, list.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        "선택한 항목이 삭제되었습니다", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}