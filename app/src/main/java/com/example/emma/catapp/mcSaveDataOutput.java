package com.example.emma.catapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Emma on 07/12/2016.
 */

//This class handles the output for the save data screen
public class mcSaveDataOutput extends AppCompatActivity implements View.OnClickListener
{
    SharedPreferences mcSharedPrefs;
    Button btnBack;
    TextView catName;
    TextView hairLength;
    TextView characteristics;
    TextView personality;
    List<databaseData> dataList;
    int dataNumber;

    FragmentManager fmAboutDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_display_shared_prefs);

        btnBack = (Button) findViewById(R.id.backBtn);
        btnBack.setOnClickListener(this);

        catName = (TextView) findViewById(R.id.name);
        hairLength = (TextView) findViewById(R.id.hairLength);
        characteristics = (TextView) findViewById(R.id.characteristics);
        personality = (TextView) findViewById(R.id.personality);
        // Load any saved preferences
        mcSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadSavedPreferences();

        fmAboutDialogue = this.getFragmentManager();

        Log.e("n","SDOutput msg");

    }

    //This function gets the saved data and assigns it to the relevant text fields
    private void loadSavedPreferences() {

       catName.setText(mcSharedPrefs.getString("catName","Empty"));
        hairLength.setText(mcSharedPrefs.getString("hairLength", "Empty"));
        characteristics.setText(mcSharedPrefs.getString("characteristics", "Empty"));
        personality.setText(mcSharedPrefs.getString("personality", "Empty"));
    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }


}
