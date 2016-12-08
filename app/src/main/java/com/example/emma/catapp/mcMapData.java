package com.example.emma.catapp;

import java.io.Serializable;

/**
 * Created by Emma on 06/12/2016.
 */
<<<<<<< HEAD
<<<<<<< HEAD

//This class includes the variables from the location database that are needed for the map
=======
>>>>>>> b1e04e1fba75fc424a5c53657c9ab24683151892
=======
>>>>>>> origin/master
public class mcMapData implements Serializable {

// *********************************************
// Declare variables etc.
// *********************************************

    private String name;
    private float Latitude;
    private float Longitude;

    private static final long serialVersionUID = 0L;

// *********************************************
// Declare getters and setters etc.
// *********************************************


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float Lat) {
        this.Latitude = Lat;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float fLongitude) {
        this.Longitude = fLongitude;
    }

    @Override
    public String toString() {
        String mapData;
        mapData = "mcCatInfo [name=" + name;
        mapData = ", Latitude=" + Latitude;
        mapData = ", Longitude=" + Longitude + "]";
        return mapData;
    }
}
