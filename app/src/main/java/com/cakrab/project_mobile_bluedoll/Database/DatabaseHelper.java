package com.cakrab.project_mobile_bluedoll.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Bluedoll";
    private static final int DATABASE_VERSION = 1;
    public final String TABLE_USER = "users";
    public final String TABLE_DOLL = "dolls";

    // User Table Column
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_GENDER = "gender";
    private static final String USER_ROLE = "role";
//    private static final String USER_BIRTHDAY = "birthday";

    // Doll Table Column
    private static final String DOLL_ID = "id";
    private static final String DOLL_NAME = "name";
    private static final String DOLL_CREATOR = "creator";
    private static final String DOLL_DESC = "description";
    private static final String DOLL_IMAGE = "image";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, " +
                USER_EMAIL + " TEXT, " +
                USER_PASSWORD + " TEXT, " +
                USER_GENDER + " TEXT, " +
                USER_ROLE + " TEXT " + ")");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USER + "(" +
                USER_ID + ", " +
                USER_NAME + ", " +
                USER_EMAIL + " , " +
                USER_PASSWORD + " , " +
                USER_GENDER  + " , " +
                USER_ROLE + ") VALUES(1, 'Administrator', 'admin@gmail.com', 'admin123', 'Male', 'Admin')");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DOLL + "(" +
                DOLL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DOLL_NAME + " TEXT, " +
                DOLL_CREATOR + " TEXT, " +
                DOLL_DESC + " TEXT, " +
                DOLL_IMAGE + " TEXT, " +
                "FOREIGN KEY (" + DOLL_CREATOR + ") REFERENCES " + TABLE_USER + "(" + USER_ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DOLL);
        onCreate(sqLiteDatabase);
    }
}
