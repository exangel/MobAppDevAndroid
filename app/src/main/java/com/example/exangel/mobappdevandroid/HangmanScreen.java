package com.example.exangel.mobappdevandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class HangmanScreen extends Activity implements OnClickListener {

    Galgelogik logic;
    ImageView gallowsImageView;
    EditText guessText;
    Button guessButton;
    Button settingsButton;
    Button resetButton;
    TextView usedLettersTextView;
    TextView wordTextView;
    TextView endTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_screen);

        logic = new Galgelogik();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(pref.getBoolean("drCheckBox",false)) {
            try {
                logic.hentOrdFraDr();
            } catch (Exception e) {
                System.out.println("Couldn't get words from DR: " + e.toString());
            }
        }
        gallowsImageView = (ImageView) findViewById(R.id.gallowsImageView);
        guessText = (EditText) findViewById(R.id.guessEditText);
        guessButton = (Button) findViewById(R.id.guessButton);
        guessButton.setOnClickListener(this);
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(this);
        usedLettersTextView = (TextView) findViewById(R.id.usedLettersTextView);
        wordTextView = (TextView) findViewById(R.id.wordTextView);
        endTextView = (TextView) findViewById(R.id.endTextView);

        wordTextView.setText(logic.getSynligtOrd());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hangman_screen, menu);
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

    public void onClick(View v) {
        if (v == guessButton) {
            endTextView.setText("");
            String entered = guessText.getText().toString().toLowerCase();
            if (!entered.isEmpty()) {
                logic.gætBogstav(entered);
                int wrongs = logic.getAntalForkerteBogstaver();
                int id = 0;
                System.out.println("Wrongs: " + wrongs);
                switch (wrongs) {
                    case 0:
                        id = R.drawable.hangman0;
                        break;
                    case 1:
                        id = R.drawable.hangman1;
                        break;
                    case 2:
                        id = R.drawable.hangman2;
                        break;
                    case 3:
                        id = R.drawable.hangman3;
                        break;
                    case 4:
                        id = R.drawable.hangman4;
                        break;
                    case 5:
                        id = R.drawable.hangman5;
                        break;
                    case 6:
                        id = R.drawable.hangman6;
                        break;
                }
                gallowsImageView.setImageResource(id);
                usedLettersTextView.setText(usedLettersTextView.getText() + entered + " ");
                wordTextView.setText(logic.getSynligtOrd());
                guessText.setText("");
                if (logic.erSpilletTabt()) {
                    System.out.println("Spillet er tabt!");
                    endTextView.setText("You have lost! =(");
                    logic.nulstil();
                    gallowsImageView.setImageResource(R.drawable.hangman0);
                    usedLettersTextView.setText("Words Used: ");
                    wordTextView.setText(logic.getSynligtOrd());
                } else if (logic.erSpilletVundet()) {
                    System.out.println("Spiller er vundet!");
                    endTextView.setText("You have won! =D");
                    logic.nulstil();
                    gallowsImageView.setImageResource(R.drawable.hangman0);
                    usedLettersTextView.setText("Words Used: ");
                    wordTextView.setText(logic.getSynligtOrd());
                }
            }
        } else if (v == resetButton) {
            endTextView.setText("");
            logic.nulstil();
            gallowsImageView.setImageResource(R.drawable.hangman0);
            usedLettersTextView.setText("Words Used: ");
            wordTextView.setText(logic.getSynligtOrd());
        } else if(v == settingsButton) {
            System.out.println("hangmanSettingsButton clicked!");
            Intent intent = new Intent(this, HangmanSettingsScreen.class);
            startActivity(intent);
        }

    }
}
