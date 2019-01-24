package com.bere.dlira.music.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by devtacho on 9/03/18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "order.db";
    private static final int CURRENT_VERSION = 1;
    private final Context context;

    interface Tables{
        //Song made lira
        String SONG = "song";
     }

    interface References{
        //Song - made lira
        String ID_SONG =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.SONG,
                        Contract.Songs.ID);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                db.setForeignKeyConstraintsEnabled(true);
            }else{
                db.execSQL("PRAGMA foreign_keys=1");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Song made lira
        db.execSQL(String.format(
                "CREATE TABLE %s( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                Tables.SONG,
                BaseColumns._ID,
                Contract.Songs.ID,
                Contract.Songs.NAME,
                Contract.Songs.ARTIST,
                Contract.Songs.ALBUM,
                Contract.Songs.YEAR,
                Contract.Songs.DURATION));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //SONG
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.SONG);
        onCreate(db);
    }

}//End
