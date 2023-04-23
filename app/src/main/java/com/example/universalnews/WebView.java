package com.example.universalnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {
    android.webkit.WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=findViewById(R.id.web_view);
        String url = getIntent().getStringExtra("url");

        // Load the url into the webview
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}