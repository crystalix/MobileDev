package com.example.emma.catapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.util.Log;

/**
 * Created by Emma on 07/12/2016.
 */

//This class handles the save data
public class mcSaveData extends Activity
{
    //variables to be stored
    private String catName;
    private String hairLength;
    private String characteristics;
    private String personality;

    SharedPreferences mcSharedPrefs;

    private void setCatName(String catName)
    {
        this.catName = catName;
    }

    public String getCatName()
    {
        return catName;
    }


    private void setHairLength(String hairLength)
    {
        this.hairLength = hairLength;
    }
    public String getHairLength() {return hairLength;}


    private void setCharacteristics(String characteristics)
    {
        this.characteristics = characteristics;
    }
    public String getCharacteristics() {return characteristics;}


    private void setPersonality(String personality)
    {
        this.personality = personality;
    }
    public String getPersonality() {return personality;}


    public mcSaveData(SharedPreferences sharedPrefs)
    {
        setCatName("");
        setHairLength("");
        setCharacteristics("");
        setPersonality("");

        try {
            this.mcSharedPrefs = sharedPrefs;
        }
        catch (Exception e)
        {
            Log.e("n","Pref Manager is NULL" );
        }

        setDefaultPrefs();
    }

    //these functions save the preferences, depending on what type the variable is, a different function is called
    public void savePreferences(String key, boolean value)
    {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePreferences(String key, String value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void savePreferences(String key, int value) {
        SharedPreferences.Editor editor = mcSharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //Default preferences
    public void setDefaultPrefs()
    {
        savePreferences("catName", "Empty");
        savePreferences("hairLength", "Empty");
        savePreferences("characteristics", "Empty");
        savePreferences("personality", "Empty");
    }
}
