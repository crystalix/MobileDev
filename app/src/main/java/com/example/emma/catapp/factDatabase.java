package com.example.emma.catapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emma on 01/12/2016.
 */

//This class handles the output for the database window
public class factDatabase extends AppCompatActivity implements View.OnClickListener
{

    TextView catsName;
    TextView hairLength;
    TextView characteristics;
    TextView personality;
    databaseData data;
    Button homeButton;
    Button nextButton;
    Button previousButton;
    Button saveDataBtn;
    List<databaseData> dataList;
    int dataNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factdatabase);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //textboxes that will show the database info
        catsName = (TextView) findViewById(R.id.name);
        hairLength = (TextView)findViewById(R.id.hairLength);
        characteristics = (TextView)findViewById(R.id.characteristics);
        personality = (TextView) findViewById(R.id.personality);

        //setting up shared preferences to be able to save the current database entry
        final mcSaveData mcSDPrefs;
        SharedPreferences mySharedPrefs;

        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mcSDPrefs = new mcSaveData(mySharedPrefs);
        mcSDPrefs.setDefaultPrefs();

        //list that contains the database info stored in the databaseData class
        //This will be used when going between entries
        dataList = new ArrayList<databaseData>();

        Intent iMainAct = getIntent();
        final databaseData DatabaseData = (databaseData) iMainAct.getSerializableExtra("databaseInfo");

        //creating database
        databaseManager dbManager = new databaseManager(this,"catBreeds.s3db",null,1); // Lab 5
        try {
            dbManager.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //filling the list with the database data
        dataList = dbManager.alldatabaseData();

        //the first cat data entry is set on create
        catsName.setText(dbManager.findCat("Bombay").getCatName());
        hairLength.setText(dbManager.findCat("Bombay").getHairLength());
        characteristics.setText(dbManager.findCat("Bombay").getCharacteristics());
        personality.setText(dbManager.findCat("Bombay").getPersonality());

        //button that takes the user to the home page
        homeButton = (Button)findViewById(R.id.backBtn);
        homeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);

            }
        });

        //button that takes the user to the saved data screen, saving their current cat entry
        saveDataBtn = (Button)findViewById(R.id.saveData);
        saveDataBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                databaseData DatabaseData;

                DatabaseData = dataList.get(dataNumber);
                mcSDPrefs.savePreferences("catName",DatabaseData.getCatName());
                mcSDPrefs.savePreferences("hairLength",DatabaseData.getHairLength());
                mcSDPrefs.savePreferences("characteristics",DatabaseData.getCharacteristics());
                mcSDPrefs.savePreferences("personality",DatabaseData.getPersonality());

                Intent savedata = new Intent(getApplicationContext(), mcSaveDataOutput.class);
                startActivity(savedata);

            }
        });

        //shows the user the next cat entry
        //the variable dataNumber is used to keep track of what entry is to be displayed
        nextButton = (Button) findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(dataNumber<5)
                {
                    dataNumber++;
                }
                databaseData DatabaseData;

                DatabaseData = dataList.get(dataNumber);
                catsName.setText(DatabaseData.getCatName());
                hairLength.setText(DatabaseData.getHairLength());
                characteristics.setText(DatabaseData.getCharacteristics());
                personality.setText(DatabaseData.getPersonality());

            }
        });

        //shows the previous cat entry
        previousButton = (Button) findViewById(R.id.previousBtn);
        previousButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(dataNumber>0)
                {
                    dataNumber--;
                }
                databaseData DatabaseData;

                DatabaseData = dataList.get(dataNumber);
                catsName.setText(DatabaseData.getCatName());
                hairLength.setText(DatabaseData.getHairLength());
                characteristics.setText(DatabaseData.getCharacteristics());
                personality.setText(DatabaseData.getPersonality());
            }
        });


    }

    public void onClick(View view)
    {

    }
}
