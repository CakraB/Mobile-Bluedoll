package com.cakrab.project_mobile_bluedoll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DollHelper {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DollHelper(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public boolean createDoll(String name, String creator, String description, String image) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("creator", creator);
        contentValues.put("description", description);
        contentValues.put("image", image);
        long queryResult = sqLiteDatabase.insert(databaseHelper.TABLE_DOLL, null, contentValues);
        // If Something Wrong return false
        if (queryResult == -1) {
            return false;
        }
        return true;
    }

    public void close() {
        sqLiteDatabase.close();
    }
}
