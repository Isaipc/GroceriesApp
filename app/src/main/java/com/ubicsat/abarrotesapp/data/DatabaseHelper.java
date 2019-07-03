package com.ubicsat.abarrotesapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ubicsat.abarrotesapp.data.AbarrotesAppContract;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Abarrotes.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AbarrotesAppContract.SQL_CREATE_CATEGORY_ENTRY);
        db.execSQL(AbarrotesAppContract.SQL_CREATE_PRODUCT_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AbarrotesAppContract.SQL_DELETE_PRODUCT_ENTRY);
        db.execSQL(AbarrotesAppContract.SQL_DELETE_CATEGORY_ENTRY);

        onCreate(db);
    }
}
