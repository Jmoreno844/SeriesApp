package com.example.seriesapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.seriesapp.R;

public class WebviewActivity extends AppCompatActivity {
    private WebView wb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wb1 = (WebView) findViewById(R.id.webview);

        //obtener y cargar URL
        String url = getIntent().getStringExtra("url");
        wb1.setWebViewClient(new WebViewClient());
        wb1.loadUrl(url);
    }
}