package com.omnicoders.omnicoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*
         * TODO enable home back button
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
         * TODO  NOTOE: Receiving the ListView Clicked item value send by previous activity.
         *  and change default actionBar title with selected item name
         */
        //TODO get passed data from JavaFragment
        Intent i = getIntent();
        String content = i.getStringExtra("content");
        String title = getIntent().getStringExtra("title");


        // TODO set the title on the actionbar
        getSupportActionBar().setTitle(title);

        //TODO view the content on Webview
        WebView webView = findViewById(R.id.webDetailView);

        webView.loadData(content, "text/html; charset=UTF-8", null);

    }

    //TODO called the back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
