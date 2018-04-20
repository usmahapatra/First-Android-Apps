package com.example.ujjwalsmahapatra.listviewwebviewurl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Initialization:
        webView=(WebView)findViewById(R.id.webView1);

       Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
       int selection=bundle.getInt("selection");



        switch(selection) {
            case 0:
                webView.loadUrl("https://www.google.co.in/");
                break;
            case 1:
                webView.loadUrl("https://www.facebook.com/");
                break;
            case 2:
                webView.loadUrl("https://twitter.com/");
                break;
            case 3:
                webView.loadUrl("http://techpalle.com/android-online-training.aspx/");
                break;
            case 4:
                webView.loadUrl("https://https://mail.google.com/");
                break;
            default:
                Toast.makeText(this,"hi",Toast.LENGTH_LONG).show();
        }

        webView.setWebViewClient(new WebViewClient());
    }

}
