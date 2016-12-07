package com.example.emma.catapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by Emma on 29/11/2016.
 */
public class mcAboutDialogue extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage("This app will blah blah").setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });

        mcAboutDialog.setTitle("About");

        return mcAboutDialog.create();

    }

}
