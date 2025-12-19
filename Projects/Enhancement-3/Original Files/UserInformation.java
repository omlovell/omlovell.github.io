package com.example.weighttrackingoml;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserInformation extends SQLiteOpenHelper {


    public UserInformation(Context context) {
        super(context, "userinfo3.db", null, 1);
    }

    public static final class UserTable {
        public static final String TABLE = "users";
        public static final String COL_ID = "_id";
        public static final String COL_USERNAME = "username";
        public static final String COL_PASSWORD = "password";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " +
                UserTable.COL_USERNAME + " text, " +
                UserTable.COL_PASSWORD + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE);
        onCreate(db);
    }

    public void addUser(LoginModel userinfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UserTable.COL_USERNAME, userinfo.getUsername());
        values.put(UserTable.COL_PASSWORD, userinfo.getPassword());

        db.insert(UserTable.TABLE, null, values);
    }

    public List<LoginModel> getUsers() {
        List<LoginModel> returnList = new ArrayList<>();

        //get data
        String query1 = "SELECT * FROM " + UserTable.TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query1, null);

        //check to see if things are in database
        if (cursor.moveToFirst()) {
            // loop and place into the list
            do {
                // id at 0, username at 1, password at 2
                String username1 = cursor.getString(1);
                String password = cursor.getString(2);

                LoginModel newLog = new LoginModel(username1,password);
                returnList.add(newLog);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

}
