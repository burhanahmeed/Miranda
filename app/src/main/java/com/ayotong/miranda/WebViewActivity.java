package com.ayotong.miranda;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by burhan on 19/07/17.
 */

public class WebViewActivity extends AppCompatActivity {

    final Context myApp = this;

    @JavascriptInterface
    public void processHTML(String html) {
        if (html == null)
            return;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_action_toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.icon_menu_about);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Articles");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String url = getIntent().getStringExtra("GET_LINK");
        final WebView wv = (WebView) findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {   super.onPageFinished(view, url);
                StringBuilder builder = new StringBuilder("");
                builder.append("javascript:document.getElementById('searchform').style.display= 'none';void(0);");
                builder.append("javascript:document.getElementsByTagName('header').style.display   = 'none';void(0);");
                wv.loadUrl(builder.toString());
                Log.d("onPageFinished", "The Page has finished loading");
            }
        });

//        final MyWebClient myWebViewClient = new MyWebClient();
//        wv.setWebViewClient(myWebViewClient);
        wv.loadUrl(url);
    }

    private class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            StringBuilder builder = new StringBuilder("");
            builder.append("javascript:document.getElementById('searchform').style.visibility= 'hidden';");
            builder.append("javascript:document.getElementsByClassName('entry-title').style.display   = 'none'  ;");
            builder.append("javascript:document.getElementById('appbar').style.visibility= 'hidden';");
            builder.append("javascript:document.getElementById('appbar').style.display   = 'none'  ;");
            builder.append("javascript:document.getElementById('gf-nav').style.visibility= 'hidden';");
            builder.append("javascript:document.getElementById('gf-nav').style.display   = 'none'  ;");
            view.loadUrl(builder.toString());
        }
    }
}
