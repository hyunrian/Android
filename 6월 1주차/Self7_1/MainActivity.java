package com.kh.self7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView, alert;
    EditText editText;
    ImageView image;
    Button button;
    int init = 1;
    MenuItem item;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        alert = findViewById(R.id.alert);
        image = findViewById(R.id.image);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.cat);
                image.setRotation(0);
                editText.setText("");
                init = 0;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.item = item;
        init = 1;
        alert.setVisibility(View.INVISIBLE);
        switch(item.getItemId()) {
            case R.id.itemRotate:
                try {
                    String str = editText.getText().toString();
                    image.setRotation(image.getRotation() + Integer.valueOf(str));
                } catch (Exception e) {
                    alert.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.itemCat:
                image.setImageResource(R.drawable.cat);
                break;
            case R.id.itemDog:
                image.setImageResource(R.drawable.dog);
                break;
            case R.id.itemFox:
                image.setImageResource(R.drawable.fox);
                break;
        }
        item.setChecked(true);
        return true;
    }
}