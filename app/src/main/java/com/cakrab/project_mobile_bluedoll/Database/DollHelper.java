package com.cakrab.project_mobile_bluedoll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.cakrab.project_mobile_bluedoll.Doll;
import com.cakrab.project_mobile_bluedoll.DollAdapter;

import java.util.ArrayList;

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

    public ArrayList<Doll> readDoll() {
        ArrayList<Doll> dollArrayList = new ArrayList<>();
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + databaseHelper.TABLE_DOLL;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        while(cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String creator = cursor.getString(2);
            String description = cursor.getString(3);
            String image = cursor.getString(4);

            Doll newDoll = new Doll(id, name, creator, description, image);
            dollArrayList.add(newDoll);
        }
        return dollArrayList;
    }

    public boolean updateDoll(String id, String name, String creator, String description, String image) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("creator", creator);
        contentValues.put("description", description);
        contentValues.put("image", image);
        long queryResult = sqLiteDatabase.update(databaseHelper.TABLE_DOLL, contentValues, "id = ?", new String[]{id});
        // If Something Wrong return false
        if (queryResult > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteDoll(String id) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        long queryResult = sqLiteDatabase.delete(databaseHelper.TABLE_DOLL, "id = ?", new String[]{id});
        // If Something Wrong return false
        if (queryResult > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void close() {
        sqLiteDatabase.close();
    }
}
