package com.example.emma.catapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by Emma on 01/12/2016.
 */
public class factDatabase extends Activity
{

    TextView catsName;
    TextView hairLength;
    TextView characteristics;
    TextView personality;
    databaseData data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factdatabase);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        catsName = (TextView) findViewById(R.id.name);
        hairLength = (TextView)findViewById(R.id.hairLength);
        characteristics = (TextView)findViewById(R.id.characteristics);
        personality = (TextView) findViewById(R.id.personality);

        Intent iMainAct = getIntent();
        databaseData DatabaseData = (databaseData) iMainAct.getSerializableExtra("databaseInfo");

        databaseManager dbManager = new databaseManager(this,"catBreeds.s3db",null,1); // Lab 5
        try {
            dbManager.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        catsName.setText(DatabaseData.getCatName());
        hairLength.setText(DatabaseData.getHairLength());
        characteristics.setText(DatabaseData.getCharacteristics());
        personality.setText(DatabaseData.getPersonality());


    }

}
