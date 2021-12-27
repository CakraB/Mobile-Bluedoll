package com.cakrab.project_mobile_bluedoll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserHelper {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public UserHelper(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public boolean createUser(String name, String email, String password, String gender) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("gender", password);
        long queryResult = sqLiteDatabase.insert(databaseHelper.TABLE_USER, null, contentValues);
        // If Something Wrong return false
        if (queryResult == -1) {
            return false;
        }
        return true;
    }

    public boolean readUser(String email, String password) {
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + databaseHelper.TABLE_USER + " WHERE email= ?" + " AND password= ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{email, password});
        cursor.moveToLast();
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public void close() {
        sqLiteDatabase.close();
    }
}
