package com.example.weighttrackingoml;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserWeights extends SQLiteOpenHelper {

    public static final String user_weight = "userweights3.db";
    public static final int VERSION = 1;

    public UserWeights(Context context) {
        super(context, user_weight, null, VERSION);
    }


    public static final class WeightTable {
        public static final String TABLE = "weights";
        public static final String COL_ID = "_id";
        public static final String COL_WEIGHT = "weight";
        public static final String COL_DATE = "date";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + WeightTable.TABLE + " (" +
                WeightTable.COL_ID + " integer primary key autoincrement, " +
                WeightTable.COL_WEIGHT + " text, " +
                WeightTable.COL_DATE + " text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WeightTable.TABLE);
        onCreate(db);
    }

    //Add
    public void addWeightsDB(String enteredWeight, String enteredDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(WeightTable.COL_WEIGHT, enteredWeight);
        values.put(WeightTable.COL_DATE, enteredDate);

        db.insert(WeightTable.TABLE, null, values);

    }

    //Update data
    public void updateOneWeight(String enteredWeight, String enteredDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues  value = new ContentValues();

        value.put(WeightTable.COL_WEIGHT, enteredWeight);
        value.put(WeightTable.COL_DATE, enteredDate);

        db.update(WeightTable.TABLE, value, "date=?", new String[] {enteredDate} );
    }

    //Delete weight data based on the date, some weights may be the same
    //Remove data
    public void deleteOne(String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(WeightTable.TABLE, "date=?", new String[] {date});
    }



    public List<WeightModel> getUsersWeight() {
        List<WeightModel> returnList = new ArrayList<>();

        //get data
        String query1 = "SELECT * FROM " + WeightTable.TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query1, null);

        //check to see if things are in database
        if (cursor.moveToFirst()) {
            // loop and place into the list
            do {
                // id at 0, weight at 1, date at 2
                String weight = cursor.getString(1);
                String date = cursor.getString(2);

                WeightModel newLog = new WeightModel(weight,date);
                returnList.add(newLog);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

}
