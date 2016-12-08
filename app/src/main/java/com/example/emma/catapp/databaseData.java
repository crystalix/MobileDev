package com.example.emma.catapp;

import java.io.Serializable;

/**
 * Created by Emma on 04/12/2016.
 */

//This class will hold the database data for the cat facts.
public class databaseData implements Serializable
{
    //variables to be stored
    private String catName;
    private String hairLength;
    private String characteristics;
    private String personality;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {this.catName = catName;}


    public String getHairLength() {
        return hairLength;
    }

    public void setHairLength(String hairLength) {
        this.hairLength= hairLength;
    }


    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }


    @Override
    public String toString() {
        String catData;
        catData = "databaseData [catName=" + catName;
        catData = ", hairLength=" + hairLength;
        catData = ", characteristics=" + characteristics;
        catData = ", personality=" + personality+"]";
        return catData;
    }
}
