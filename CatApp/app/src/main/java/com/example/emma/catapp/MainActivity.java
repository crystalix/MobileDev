package com.example.emma.catapp;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.app.FragmentManager;
//import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.DialogFragment;
import android.content.Intent;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    //@Override
    //android.app.FragmentManager fmAboutDialogue;
    FragmentManager fmAboutDialogue;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        fmAboutDialogue = this.getFragmentManager();

        android.support.v7.app.ActionBar ccActionBar = getSupportActionBar();
        if(ccActionBar!= null)
        {
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setLogo(R.drawable.ic_action_important);
            ccActionBar.setDisplayUseLogoEnabled(true);
        }

    }

    public void onClick(View view)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.mc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.mQuit:
            finish();
            return true;

            case R.id.mAbout:
                DialogFragment mcAboutDlg = new mcAboutDialogue();
                mcAboutDlg.show(fmAboutDialogue, "mc_About_Dlg");
                return true;
            case R.id.mRSS:
                Intent mcRSS = new Intent(this, imageRSS.class);
                this.startActivity(mcRSS);
                return true;
            case R.id.mDatabase:
                Intent mDatabase = new Intent(this,factDatabase.class);
                this.startActivity(mDatabase);
                return true;
            case R.id.mMap:
                Intent mMap = new Intent(this,Map.class);
                this.startActivity(mMap);
                return true;
            case R.id.mBio:
                Intent mBio = new Intent(this, mcBioActivity.class);
                this.startActivity(mBio);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
