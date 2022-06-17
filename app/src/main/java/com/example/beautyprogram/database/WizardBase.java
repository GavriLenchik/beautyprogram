package com.example.beautyprogram.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class WizardBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "wizards";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CITY = "city";
    public static final String KEY_ACTIVITY = "activity";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_MAIL = "mail";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_STATUS = "status";
    public static final String KEY_RANGE= "range";
    public static final String KEY_SPECIAL= "special";

    public WizardBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbWiz) {
        dbWiz.execSQL("create table " + DATABASE_NAME + " ("+ KEY_ID + " integer primary key autoincrement, "
                + KEY_NAME + " text, " + KEY_CITY + " text, " + KEY_ACTIVITY + " text, " + KEY_SURNAME + " text, "+ KEY_MAIL+ " text, "+ KEY_PHONE +" text, "+ KEY_LOGIN +
                " text, "+ KEY_PASSWORD + " text, " + KEY_STATUS + " text, " + KEY_SPECIAL + " text, " + KEY_RANGE + " text" + "); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbWiz, int i, int i1) {
        dbWiz.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(dbWiz);
    }
}
