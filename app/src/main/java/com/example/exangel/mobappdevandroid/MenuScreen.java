package com.example.exangel.mobappdevandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MenuScreen extends Activity implements OnClickListener {

    Button settingsButton;
    Button clickMeButton;
    Button webButton;
    Button vejledningButtonTest;
    TextView menuTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        settingsButton = (Button) findViewById(R.id.settingsButton);
        clickMeButton = (Button) findViewById(R.id.clickMeButton);
        webButton = (Button) findViewById(R.id.webButton);
        vejledningButtonTest = (Button) findViewById(R.id.vejledningButton);
        vejledningButtonTest.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        clickMeButton.setOnClickListener(this);
        webButton.setOnClickListener(this);

        menuTextView = (TextView) findViewById(R.id.menuTextView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen, menu);
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
    @Override
    public void onClick(View v){
        if(v == clickMeButton){
            System.out.println("clickMeButton clicked!");
            menuTextView.setText("Thank you!");
            clickMeButton.setText("Click me again!");
        } else if(v == webButton){
            System.out.println("webButton clicked!");
            Intent intent = new Intent(this, WebBrowserScreen.class);
            startActivity(intent);
        } else if(v == settingsButton) {
            System.out.println("settingsButton clicked!");
            Intent intent = new Intent(this, SettingsScreen.class);
            startActivity(intent);
        } else if(v == vejledningButtonTest){
            System.out.println("vejledningButtonTest clicked!");
            Intent intent = new Intent(this, vejledningScreen.class);
            startActivity(intent);
        } else {
            System.out.println("Something unexpected clicked!");
        }
    }
}
