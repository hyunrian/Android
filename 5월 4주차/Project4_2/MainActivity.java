package com.kh.project4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    CheckBox chkStart;
    LinearLayout innerLayout;
    RadioGroup rGroup;
    Button btnFinish;
    ImageView imgPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("귀여운 동물 보기");
        chkStart = findViewById(R.id.chkStart);
        innerLayout = findViewById(R.id.innerLayout);
        rGroup = findViewById(R.id.rGroup);
        btnFinish = findViewById(R.id.btnFinish);
        imgPet = findViewById(R.id.imgPet);

        chkStart.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    innerLayout.setVisibility(View.VISIBLE);
                } else {
                    innerLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = rGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rdDog:
                        imgPet.setImageResource(R.drawable.dog3);
                        break;
                    case R.id.rdCat:
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rdRabbit:
                        imgPet.setImageResource(R.drawable.rabbit);
                        break;
                    case R.id.rdFox:
                        imgPet.setImageResource(R.drawable.fox2);
                        break;
                }
                imgPet.setVisibility(View.VISIBLE);
            }
        });

    }

}