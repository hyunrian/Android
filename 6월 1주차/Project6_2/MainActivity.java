package com.kh.project6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button btnGo, btnBack;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 웹브라우저");
        setUI();
        setListener();
    }

    private void setListener() {
        btnGo.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void setUI() {
        editText = findViewById(R.id.editText);
        btnGo = findViewById(R.id.btnGo);
        btnBack = findViewById(R.id.btnBack);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebClient());
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View view) {
        if (view == btnGo) {
            String url = editText.getText().toString();
            webView.loadUrl(url);
        } else if (view == btnBack) {
            webView.goBack(); //goForward() : 앞으로가기
        }
    }

    class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}