package com.example.exangel.mobappdevandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


public class WebBrowserScreen extends Activity implements View.OnClickListener {
    Button goButton;
    EditText urlText;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser_menu);

        goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(this);
        urlText = (EditText) findViewById(R.id.editText);
        webView = (WebView) findViewById(R.id.browserWebView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_browser_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        String url = urlText.getText().toString();
        if((!url.startsWith("http://www.") || !url.startsWith("https://www.")) && !url.startsWith("www.")){
            url = "http://www." + url;
        } else if( url.startsWith("www.")){
            url = "http://" + url;
        }
        webView.loadUrl(url);
    }
}
