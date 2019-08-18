package com.eyeapp.tesmata;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Yulian on 13/06/2016.
 */
public class Database extends SQLiteAssetHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tes_mata.db";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getSoalBT(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String sqlTables = "soal_butawarna";
        String[] sqlSelect = {"0 _id","soal","jawab_1","jawab_2","jawab_3","jawab_4","kunci"};

        qb.setTables(sqlTables);

        Cursor c= qb.query(db,sqlSelect, null,null, null,null,"RANDOM()","20");
        c.moveToFirst();
        return c;
    }

    public Cursor getSoalVS(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String sqlTables = "soal_visus";
        String[] sqlSelect = {"id","soal","jawab_1","jawab_2","jawab_3","jawab_4","kunci"};

        qb.setTables(sqlTables);

        Cursor c= qb.query(db,sqlSelect, null,null, null,null,"RANDOM()","20");
        c.moveToFirst();
        return c;
    }
}
