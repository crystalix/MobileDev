package com.example.emma.catapp;

/**
 * Created by Emma on 04/12/2016.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;

public class RSSAsync extends AsyncTask<String, Integer,RSSData>
{
    private Context appContext;
    private String urlRSSToParse;

    public RSSAsync(Context currentAppContext, String urlRSS)
    {
        appContext = currentAppContext;
        urlRSSToParse = urlRSS;
    }

    // A callback method executed on UI thread on starting the task
    @Override
    protected void onPreExecute() {
        // Message to indicate start of parsing
        Toast.makeText(appContext,"Parsing started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected RSSData doInBackground(String... params)
    {
        RSSData parsedData;
        RSSParser rssParser = new RSSParser();
        try {
            rssParser.parseRSSData(urlRSSToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        parsedData = rssParser.getRSSDataItem();

        return parsedData;
    }

    // A callback method executed on UI thread, invoked after the completion of the task
    // When doInbackground has completed, the return value from that method is passed into this event
    // handler.
    @Override
    protected void onPostExecute(RSSData result) {
        // Message to indicate end of parsing
        Toast.makeText(appContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
