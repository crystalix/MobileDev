package com.example.emma.catapp;

/**
 * Created by Emma on 06/12/2016.
 */

import android.Manifest;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.app.DialogFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
//This class handles the output for the map screen, making a map and adding markers to it
//The map function does not work
public class mcMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    FragmentManager fmAboutDialogue; // Lab 3 Dialogue fragment
    List<String> names;
    List<Float> latitude;
    List<Float> longitude;
=======
public class mcMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    FragmentManager fmAboutDialogue; // Lab 3 Dialogue fragment
    List<mcMapData> mapDataLst;
>>>>>>> b1e04e1fba75fc424a5c53657c9ab24683151892
    private Marker[] mapDataMarkerList = new Marker[5];
    private GoogleMap mapCatShelters;        //Google Map variable
    private float markerColours[] = {210.0f, 120.0f, 300.0f, 330.0f, 270.0f};

<<<<<<< HEAD

=======
>>>>>>> b1e04e1fba75fc424a5c53657c9ab24683151892
    private static final LatLng latlangGlasgowCentre = new LatLng(55.8651, -4.258);    //The Latitude and Longitude for the centre of East Kilbride

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_map_view); // app main UI screen

<<<<<<< HEAD
=======
        // Action Bar
        android.support.v7.app.ActionBar ccActionBar = getSupportActionBar();
        if (ccActionBar != null) {
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setLogo(R.drawable.ic_action_important);
            ccActionBar.setDisplayUseLogoEnabled(true);
        }

>>>>>>> b1e04e1fba75fc424a5c53657c9ab24683151892
        // Lab 3 Dialogue fragment
        fmAboutDialogue = this.getFragmentManager();


<<<<<<< HEAD
        //mapDataLst = new ArrayList<mcMapData>();
        names = new ArrayList<String>();
        longitude = new ArrayList<Float>();
        latitude = new ArrayList<Float>();

        names.add("Cats Protection Glasgow Adoption Centre");
        names.add("Catflap Pet Rescue");
        names.add("Scottish SPCA Animal Rescue and Rehoming");

        latitude.add(55.9027f);
        latitude.add(55.9289f);
        latitude.add(55.8389f);

        longitude.add(4.1619f);
        longitude.add(4.3196f);
        longitude.add(4.3405f);


       // mapDataLst = mapDB.allMapData();
=======
        mapDataLst = new ArrayList<mcMapData>();
        mcMapDataDBMgr mapDB = new mcMapDataDBMgr(this, "catLocations.s3db", null, 1);
        try {
            mapDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapDataLst = mapDB.allMapData();
>>>>>>> b1e04e1fba75fc424a5c53657c9ab24683151892
        SetUpMap();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapCatShelters = googleMap;
        if (mapCatShelters != null) {
            mapCatShelters.moveCamera(CameraUpdateFactory.newLatLngZoom(latlangGlasgowCentre, 12));        //Set the default position to EK
            mapCatShelters.getUiSettings().setCompassEnabled(true);                                   //Turn on the Compass
            mapCatShelters.getUiSettings().setMyLocationButtonEnabled(true);                          //Turn on the Location Buttons Functionality
            mapCatShelters.getUiSettings().setRotateGesturesEnabled(true);
            AddMarkers();
        }
    }

    public void SetUpMap() {
        MapFragment mapCatLocations = (MapFragment) getFragmentManager().findFragmentById(R.id.mMap);
        mapCatLocations.getMapAsync(this);    //Create the map and apply to the variable
    }

    public void AddMarkers() {
        MarkerOptions marker;
<<<<<<< HEAD
        //mcMapData mapData;
        String mrkTitle;
        String mrkText;


    	/* For all the marker options in dbList list */
        for (int i = 0; i < 2; i++)
        {
            mrkTitle = names.get(i);
            mrkText = names.get(i);

            marker = SetMarker(mrkTitle, mrkText, new LatLng(latitude.get(i), longitude.get(i)), markerColours[i], true);
=======
        mcMapData mapData;
        String mrkTitle;
        String mrkText;

    	/* For all the marker options in dbList list */
        for (int i = 0; i < mapDataLst.size(); i++) {
            mapData = mapDataLst.get(i);
            mrkTitle = mapData.getName();
            mrkText = mapData.getName();
            marker = SetMarker(mrkTitle, mrkText, new LatLng(mapData.getLatitude(), mapData.getLongitude()), markerColours[i], true);
>>>>>>> b1e04e1fba75fc424a5c53657c9ab24683151892
            mapDataMarkerList[i] = mapCatShelters.addMarker(marker);    //create a maker and add to the venue markers list
        }
    }

    public MarkerOptions SetMarker(String title, String snippet, LatLng position, float markerColour, boolean centreAnchor) {
        float anchorX;    //Create anchorX
        float anchorY;    //Create anchorY

    	/* On the condition the anchor is to be centred */
        if (centreAnchor) {
            anchorX = 0.5f;    //Centre X
            anchorY = 0.5f;    //Centre Y
        } else {
            anchorX = 0.5f;    //Centre X
            anchorY = 1f;    //Bottom Y
        }

    	/* create marker options from the input variables */
        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX, anchorY).position(position);

        return marker;    //Return marker
    }





}