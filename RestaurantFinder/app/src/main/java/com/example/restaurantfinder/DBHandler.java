package com.example.restaurantfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "data";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "MAD-FInal RestaruantSpreadsheet_Sheet1";

    // below variable is for our distance category
    private static final String DIST_COL = "Mile Category <X";

    // below variable is for our cuisine type
    private static final String CUISINE_COL = "Cuisine";

    // below variable is for our Restaurant Name
    private static final String NAME_COL = "Restaurant Name";

    // below variable id for our specific distance
    private static final String MILE_COL = "Specific Distance from Duques";

    // below variable for our On campus
    private static final String ONCAMP_COL = "On campus";

    // below variable is for our Gworld
    private static final String GWORLD_COL = "Gworld";

    // below variable is for our Menu Link
    private static final String MENU_COL = "Menu Link";

    // below variable is for our Restaurant Hours
    private static final String HRS_COL = "Restaurant Hours";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + DIST_COL + " INTEGER, "
                +CUISINE_COL +"TEXT,"
                + NAME_COL + " TEXT,"
                + MILE_COL + " DOUBLE,"
                + ONCAMP_COL + " TEXT,"
                + GWORLD_COL + " TEXT,"
                +MENU_COL + "TEXT,"
                +HRS_COL + "TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    /*
    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    } */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

