package com.example.randomizationmalinea;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.randomizationmalinea.PatientsContract.PatientEntry.COLUMN_GROUPE_KEY;
import static com.example.randomizationmalinea.PatientsContract.PatientEntry.COLUMN_ID_KEY;
import static com.example.randomizationmalinea.PatientsContract.PatientEntry.COLUMN_LOCATION_KEY;
import static com.example.randomizationmalinea.PatientsContract.PatientEntry.COLUMN_NOM_KEY;
import static com.example.randomizationmalinea.PatientsContract.PatientEntry.COLUMN_PRENOM_KEY;
import static com.example.randomizationmalinea.PatientsContract.PatientEntry.TABLE_NAME;

/**
 * Created by herimanitra on 4/15/16.
 */
public class PatientsDbHelper extends SQLiteOpenHelper {
    //need to change database_version if you want to initialize
    private static final int DATABASE_VERSION=17;
    //the database in itself:
    static final String DATABASE_NAME= "patients.db";
    public  PatientsDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME,factory, DATABASE_VERSION);
    }
    @Override
    /**
     * create a SQLite database to store patient's information
     */
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create a table to hold patients's location, nom and prenom + group
        // they will belong during clinical trial:
        String SQL_CREATE_PATIENTS_TABLE= "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LOCATION_KEY + " TEXT, "
                + COLUMN_NOM_KEY + " TEXT , "
                + COLUMN_PRENOM_KEY + " TEXT , "
                + COLUMN_GROUPE_KEY + " INTEGER DEFAULT 0" + " )";
        //perform actual creation of the database:
        sqLiteDatabase.execSQL(SQL_CREATE_PATIENTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again:
        onCreate(sqLiteDatabase);
    }

    /**
     * add a new row to the database:
     */
     public void addPatientName (String name)
     {
         ContentValues values = new ContentValues();
         values.put(COLUMN_NOM_KEY,name);
         SQLiteDatabase sqLiteDatabase = getWritableDatabase();
         // insert :
         sqLiteDatabase.insert(TABLE_NAME,null,values);

         sqLiteDatabase.close();
     }
    public String databaseToString ()
    {
        String dbstring = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " +  TABLE_NAME ;
        Cursor c = sqLiteDatabase.rawQuery(query ,null);
        // move to first row into your result:
       c.moveToFirst();

        while (!c.isAfterLast()) {
            // 0 for _id ; 1 for location, etc.
            dbstring += c.getString(2);
            dbstring += "\n";
            c.moveToNext();
        }

        sqLiteDatabase.close();
        return dbstring;
    }

}

