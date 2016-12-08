package com.example.emma.catapp;

/**
 * Created by Emma on 04/12/2016.
 */

//This class creates the database for the cat facts

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class databaseManager extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_PATH = "data/data/com.example.emma.catapp/databases/";
    private static final String DB_NAME = "catBreeds.s3db";

    private static final String TBL_CAT_INFO = "catBreeds";

    //database table fields
    public static final String COL_CATNAME = "Name";
    public static final String COL_HAIRLENGTH = "HairLength";
    public static final String COL_CHARACTERISTICS = "Characteristics";
    public static final String COL_PERSONALITY= "Personality";

    private final Context appContext;

    public databaseManager (Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
    }

    //making a database table using the info from the database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CATINFO_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_CAT_INFO + "("
                + COL_CATNAME + " TEXT," + COL_HAIRLENGTH
                + " TEXT," + COL_CHARACTERISTICS + " TEXT," + COL_PERSONALITY + " TEXT" + ")";
        db.execSQL(CREATE_CATINFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_CAT_INFO);
            onCreate(db);
        }
    }

    // ================================================================================
    // Creates a empty database on the system and rewrites it with your own database.
    // ================================================================================
    public void dbCreate() throws IOException {

        boolean dbExist = dbCheck();

        if(!dbExist){
            //By calling this method an empty database will be created into the default system path
            //of your application so we can overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDBFromAssets();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    // ============================================================================================
    // Check if the database already exist to avoid re-copying the file each time you open the application.
    // @return true if it exists, false if it doesn't
    // ============================================================================================
    private boolean dbCheck(){

        SQLiteDatabase db = null;

        try{
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);

        }catch(SQLiteException e){

            Log.e("SQLHelper","Database not Found!");

        }

        if(db != null){

            db.close();

        }

        return db != null ? true : false;
    }

    // ============================================================================================
    // Copies your database from your local assets-folder to the just created empty database in the
    // system folder, from where it can be accessed and handled.
    // This is done by transfering bytestream.
    // ============================================================================================
    private void copyDBFromAssets() throws IOException{

        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;

        try {

            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);
            //transfer bytes from the dbInput to the dbOutput
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }

            //Close the streams
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch (IOException e)
        {
            throw new Error("Problems copying DB!");
        }
    }


    public void addCatInfo(databaseData catInfo) {

        ContentValues values = new ContentValues();
        values.put(COL_CATNAME, catInfo.getCatName());
        values.put(COL_HAIRLENGTH, catInfo.getHairLength());
        values.put(COL_CHARACTERISTICS, catInfo.getCharacteristics());
        values.put(COL_PERSONALITY, catInfo.getPersonality());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_CAT_INFO, null, values);
        db.close();
    }

    public databaseData findCat(String catName) {
        String query = "Select * FROM " + TBL_CAT_INFO + " WHERE " + COL_CATNAME + " =  \"" + catName + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        databaseData DatabaseInfo = new databaseData();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            DatabaseInfo.setCatName(cursor.getString(0));
            DatabaseInfo.setHairLength(cursor.getString(1));
            DatabaseInfo.setCharacteristics(cursor.getString(2));
            DatabaseInfo.setPersonality(cursor.getString(3));
            cursor.close();
        } else {
            DatabaseInfo = null;
        }
        db.close();
        return DatabaseInfo;
    }

    public boolean removeCat(String catName) {

        boolean result = false;

        String query = "Select * FROM " + TBL_CAT_INFO + " WHERE " + COL_CATNAME + " =  \"" + catName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        databaseData DatabaseInfo = new databaseData();

        if (cursor.moveToFirst()) {
            DatabaseInfo.setCatName(cursor.getString(0));
            db.delete(TBL_CAT_INFO, COL_CATNAME + " = ?",
                    new String[] { String.valueOf(DatabaseInfo.getCatName()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    //this method adds the database data to a list to be used in other classes
    public List<databaseData> alldatabaseData()
    {
        String query = "Select * FROM " + TBL_CAT_INFO;
        List<databaseData> DatabaseList = new ArrayList<databaseData>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                databaseData databaseData = new databaseData();
                databaseData.setCatName(cursor.getString(0));
                databaseData.setHairLength(cursor.getString(1));
                databaseData.setCharacteristics(cursor.getString(2));
                DatabaseList.add(databaseData);
                cursor.moveToNext();
            }
        } else {
            DatabaseList.add(null);
        }
        db.close();
        return DatabaseList;
    }
}



