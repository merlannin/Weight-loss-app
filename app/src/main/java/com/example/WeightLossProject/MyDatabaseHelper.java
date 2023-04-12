package com.example.WeightLossProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "WeightLoss.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "WeightLoss";
    private static final String RECORD_ID = "record_id";
    //private static final Date CURRENT_DATETIME = Calendar.getInstance().getTime();
    private static final String CURRENT_DATETIME = "datetime";
    private static final String WEIGHT_IN_POUNDS = "weight_in_pounds";
    private static final String WEIGHT_DIFF = "weight_diff";
    private static final String TREND_DAYS = "trend_days";




   public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery =
                "CREATE TABLE " + TABLE_NAME +
                        " (" +
                        RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CURRENT_DATETIME + " DEFAULT CURRENT_TIMESTAMP, " +
                        WEIGHT_IN_POUNDS + " DOUBLE); ";

//        String sqlQuery =
//                "CREATE TABLE " + TABLE_NAME +
//                        " (" +
//                        RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        CURRENT_DATETIME + " TEXT, " +
//                        WEIGHT_IN_POUNDS + " DOUBLE, " +
//                        TREND_DAYS + " TEXT, " +
//                        WEIGHT_DIFF + " DOUBLE" +
//                        "); ";

        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addWeight(double weight_in_pounds){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(WEIGHT_IN_POUNDS, weight_in_pounds);
        //cv.put(CURRENT_DATETIME, datetime);
//        cv.put(WEIGHT_DIFF, weight_diff);
//        cv.put(TREND_DAYS, trend_days);

        long result = db.insert(TABLE_NAME, null, cv);
            if (result == -1) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Successfully added weight to table!!!", Toast.LENGTH_SHORT).show();
            }
        }

        /* returns a cursor object to read all data from db
        https://stackoverflow.com/questions/9938471/what-is-use-of-cursor-in-android-development
        Cursor is the Interface which represents a 2 dimensional table of any database.
        When you try to retrieve some data using SELECT statement, then the database
        will first create a CURSOR object and return its reference to you.
         */
        Cursor readAllData () {
            String query = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();


            Cursor cursor = null;
            if(db!= null) {
                // the cursor will contain all data from the DB when returned
                cursor = db.rawQuery(query, null);
            }
            return cursor;

        }

        void updateData(String record_id, String weight_in_pounds, String datetime){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(RECORD_ID, record_id);
            cv.put(WEIGHT_IN_POUNDS, weight_in_pounds);
            cv.put(CURRENT_DATETIME, datetime);

            long result = db.update(TABLE_NAME, cv, "record_id =?", new String[] {record_id});
            if(result == -1){
                Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Updated successfully!", Toast.LENGTH_SHORT).show();
            }
        }
}
