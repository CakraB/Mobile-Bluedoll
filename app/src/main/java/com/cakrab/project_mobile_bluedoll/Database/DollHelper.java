package com.cakrab.project_mobile_bluedoll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cakrab.project_mobile_bluedoll.Doll;

import java.util.ArrayList;

public class DollHelper {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DollHelper(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public boolean createDoll(String name, String creator, String description, String image) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("creator", creator);
        values.put("description", description);
        values.put("image", image);
        long query = sqLiteDatabase.insert(databaseHelper.TABLE_DOLL, null, values);
        // If Something Wrong return false
        if (query == -1) {
            return false;
        }
        return true;
    }

    public ArrayList<Doll> getAllDoll() {
        ArrayList<Doll> dollArrayList = new ArrayList<>();
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + databaseHelper.TABLE_DOLL;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
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
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("creator", creator);
        values.put("description", description);
        values.put("image", image);
        long query = sqLiteDatabase.update(databaseHelper.TABLE_DOLL, values, "id = ?", new String[]{id});
        // If Something Wrong return false
        if (query > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteDoll(String id) {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        long query = sqLiteDatabase.delete(databaseHelper.TABLE_DOLL, "id = ?", new String[]{id});
        // If Something Wrong return false
        if (query > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void close() {
        sqLiteDatabase.close();
    }
}
