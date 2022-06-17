package com.example.beautyprogram.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OrdersBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "orders";
    public static final String TABLE_CONTACTS = "contactsO";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOGINFROM= "loginfrom";
    public static final String KEY_TYPE_ORD = "type";
    public static final String KEY_CHECK = "choice";
    public static final String KEY_PRICE = "price";
    public static final String KEY_VALUTA = "valuta";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_EQUIPMENT = "equipment";
    public static final String KEY_DATA = "data";
    public static final String KEY_TIME = "time";
    public static final String KEY_DETAILS = "details";

    public OrdersBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbOrd) {
        dbOrd.execSQL("create table " + DATABASE_NAME + " ("+ KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text, "
                + KEY_TYPE_ORD + " text, "+ KEY_LOGINFROM + " text, " + KEY_CHECK + " text, " + KEY_PRICE +" float, "+ KEY_VALUTA +
                " text, "+ KEY_ADDRESS + " text, " + KEY_EQUIPMENT + " text, " + KEY_DATA + " text, " + KEY_TIME + " text, " + KEY_DETAILS + " text" + "); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbOrd, int i, int i1) {
        dbOrd.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(dbOrd);
    }
}
