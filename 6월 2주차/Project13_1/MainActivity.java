package com.kh.project13_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    Button btnPlay, btnStop, btnPause;
    TextView textView;
    ProgressBar progressBar;
    List<String> list = new ArrayList<>();
    String selectedFile = list.get(0);
    MediaPlayer player = new MediaPlayer();
    boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // external storage 읽기를 위한 코드
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_MEDIA_AUDIO}, MODE_PRIVATE);

        setUI();
        setListener();
        setList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_single_choice, list);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setItemChecked(0, true);

    }

    private void setList() { // sd카드에 있는 mp3 파일명을 list에 담기
//        String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/Music";
        String mp3Path = "/sdcard/Music";
        File file = new File(mp3Path);
        File[] files = file.listFiles(); // 해당 경로에 있는 파일 전체 가져오기
        for (File aFile : files) {
            String filename = aFile.getName();
            if (checkExt(filename)) list.add(filename);
        }
        Collections.sort(list);
//        Log.d("mytag", list.toString());
    }

    private boolean checkExt(String filename) {
        int extIndex = filename.lastIndexOf(".");
        String ext = filename.substring(extIndex + 1);
        if (ext.equals("mp3")) return true;
        return false;
    }

    private void setListener() {
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("mytag", "i:" + i + ", l: " + l);
                selectedFile = list.get(i);
            }
        });
    }

    private void setUI() {
        listView = findViewById(R.id.listView);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnPause = findViewById(R.id.btnPause);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        if (view == btnPlay) {
            try {
                player.setDataSource("/sdcard/Music/" + selectedFile);
                player.prepare(); // 파일을 읽을 준비(메모리에 올림)
                player.start();
                progressBar.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            textView.setText("실행중인 음악 : " + selectedFile);
        } else if (view == btnStop) {
            player.stop();
            player.reset(); // 파일을 메모리에서 제거
            progressBar.setVisibility(View.INVISIBLE);
            textView.setText("현재 재생중이 아님");
        } else if (view == btnPause) {
            if (isPause) {
                player.start();
                btnPause.setText("일시정지");
                isPause = false;
                progressBar.setVisibility(View.VISIBLE);
            }
            else {
                player.pause();
                btnPause.setText("다시 재생");
                isPause = true;
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }
}