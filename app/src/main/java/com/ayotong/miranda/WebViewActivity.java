package com.ayotong.miranda;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by burhan on 19/07/17.
 */

public class WebViewActivity extends AppCompatActivity {

    final Context myApp = this;
    Handler uiHandler = new Handler();
    WebView wv;
    String mimeType = "text/html";
    String encoding = "UTF-8";
    String url = "";
    String judul = "";
    String html = "";

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

        url = getIntent().getStringExtra("GET_LINK");
        judul = getIntent().getStringExtra("GET_TITLE");
        wv = (WebView) findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);

//                try {
//                    Document doc = Jsoup.connect(url).get();
//                    Elements tanggal = doc.select("div.date");
//                    Elements isiD = doc.select("div.text_detail");
//                    String isi = isiD.toString();
//                    String date = tanggal.toString();
//                    final String html = "<html>\n" +
//                            "  <h3>" + judul + "</h3>\n" +
//                            "<h6>" + date + "</h6>" +
//                            "  <div>" + isi + "</div>\n" +
//                            "</html>";
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                    wv.loadData(html, mimeType, encoding);
//                        }});
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
        wv.setWebViewClient(new MyWebViewClient());

        new BackgroundWorker().execute();
    }

//        wv.loadUrl(url)
// load links in WebView instead of default browser
private class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return false;
    }

    @RequiresApi(21)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return false;
    }
}

    private class BackgroundWorker extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            getDarewod();
            return null;
        }

        public void getDarewod(){

            try {
                Document doc = Jsoup.connect(url).get();
                Elements tanggal = doc.select("div.date");
                Elements isiD = doc.select("div.text_detail");
                String isi = isiD.toString();
                String date = tanggal.toString();
                html = "<html>\n" +
                        "  <h3>" + judul + "</h3>\n" +
                        "<h6>" + date + "</h6>" +
                        "  <div>" + isi + "</div>\n" +
                        "</html>";
//                wv.loadData(html, mimeType, encoding);

                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        wv.loadDataWithBaseURL(null,html, "text/html", "UTF-8",null);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

