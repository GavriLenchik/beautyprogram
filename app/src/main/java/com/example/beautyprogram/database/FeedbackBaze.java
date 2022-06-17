package com.example.beautyprogram.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FeedbackBaze extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "feedbacks";
    public static final String TABLE_CONTACTS = "contactsF";

    public static final String KEY_ID = "_id";
    public static final String KEY_FROMLOGIN = "fromlogin";
    public static final String KEY_TOLOGIN = "tologin";


    public FeedbackBaze(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbFeed) {
        dbFeed.execSQL("create table " + DATABASE_NAME + " ("+ KEY_ID + " integer primary key autoincrement, "
                + KEY_FROMLOGIN + " text, " + KEY_TOLOGIN + " text " + "); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbFeed, int i, int i1) {
        dbFeed.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(dbFeed);
    }
}
