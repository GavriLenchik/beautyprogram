package com.example.beautyprogram.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "clients";
    public static final String TABLE_CONTACTS = "contactsC";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";

    public ClientBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbCl) {
        dbCl.execSQL("create table " + DATABASE_NAME + " ("+ KEY_ID + " integer primary key autoincrement, "
                + KEY_NAME + " text, " + KEY_SURNAME + " text, " + KEY_PHONE +" text, "+ KEY_LOGIN +
                " text, "+ KEY_PASSWORD + " text" + "); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbCl, int i, int i1) {
        dbCl.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(dbCl);
    }
}
