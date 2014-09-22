package com.example.exangel.mobappdevandroid;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.CheckBox;


public class HangmanSettingsScreen extends Activity implements OnClickListener {
    PreferenceManager prefMan;
    CheckBox drCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_settings_screen);

        drCheckBox = (CheckBox) findViewById(R.id.drCheckBox);

        SharedPreferences pref = prefMan.getDefaultSharedPreferences(this);
        drCheckBox.setChecked(pref.getBoolean("drCheckBox",false));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hangman_settings_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClick(View v){
        if(v == drCheckBox){
            if(drCheckBox.isChecked()) {
                drCheckBox.setChecked(false);
                SharedPreferences pref = prefMan.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("drCheckBox", false);
                editor.apply();
            } else if(!drCheckBox.isChecked()){
                drCheckBox.setChecked(true);
                SharedPreferences pref = prefMan.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("drCheckBox", true);
                editor.apply();
            }
        }
    }
}
