package com.kh.tabhostex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TabHost
        TabHost tabhost = getTabHost();

        // TabWidget
        TabHost.TabSpec tabspecSong = tabhost.newTabSpec("tag1").setIndicator("음악별");
        tabspecSong.setContent(R.id.contentSong);
        tabhost.addTab(tabspecSong);
        TabHost.TabSpec tabspecArtist = tabhost.newTabSpec("tag2").setIndicator("가수별");
        tabspecArtist.setContent(R.id.contentArtist);
        tabhost.addTab(tabspecArtist);
        TabHost.TabSpec tabspecAlbum = tabhost.newTabSpec("tag3").setIndicator("앨범별");
        tabspecAlbum.setContent(R.id.contentAlbum);
        tabhost.addTab(tabspecAlbum);

        tabhost.setCurrentTab(1); // 인덱스는 0부터 시작(가수별로 시작)
    }
}