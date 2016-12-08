package com.example.emma.catapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * Created by Emma on 01/12/2016.
 */

//This class handles the output for the RSS feed activity
public class imageRSS extends Activity implements View.OnClickListener
{
    TextView rssInfo;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rssfeed);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        homeButton = (Button)findViewById(R.id.back);
        homeButton.setOnClickListener(this);

        RSSData feedInfo = new RSSData();

        //feed that will be used
        String RSSFeedURL = "http://www.webvet.com/taxonomy/term/Cats/all/feed";
        RSSAsync rssAsyncParser = new RSSAsync(this, RSSFeedURL);

        try {
            feedInfo = rssAsyncParser.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //sets the text displayed to the rss contents
        rssInfo = (TextView) findViewById(R.id.mRSS);
        rssInfo.setText(feedInfo.getItemDesc());
    }

        //takes the user back to home page
        @Override
        public void onClick(View view)
        {
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
        }





}
