package com.bere.dlira.music.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bere.dlira.music.model.Song;

public final class DBOperations {
    private static DBHelper db;
    private static DBOperations operations;

    private DBOperations(){}

    public static DBOperations getDBOperations(
            Context context){
        if(operations==null) {
            operations = new DBOperations();
        }
        if(db==null){
            db = new DBHelper(context);
        }
        return operations;
    }

    //SONG METHOD MADE LIRA FIERRO
    //Operations of song
    public Cursor getSongs(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", DBHelper.Tables.SONG);
        return  database.rawQuery(sql, null);
    }

    public Cursor getSongById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                        " WHERE %s=?",
                DBHelper.Tables.SONG,
                Contract.Songs.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertSong(Song song){
        SQLiteDatabase database = db.getWritableDatabase();
        String idSong = Contract.Songs.generateIdSong();
        ContentValues values = new ContentValues();
        values.put(Contract.Songs.ID, idSong);
        values.put(Contract.Songs.NAME, song.getName());
        values.put(Contract.Songs.ARTIST, song.getArtist());
        values.put(Contract.Songs.ALBUM, song.getAlbum());
        values.put(Contract.Songs.YEAR, song.getYear());
        values.put(Contract.Songs.DURATION, song.getDuration());
        database.insertOrThrow(DBHelper.Tables.SONG, null, values);
        return idSong;
    }

    public boolean updateSong(Song song){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Songs.NAME, song.getName());
        values.put(Contract.Songs.ARTIST, song.getArtist());
        values.put(Contract.Songs.ALBUM, song.getAlbum());
        values.put(Contract.Songs.YEAR, song.getYear());
        values.put(Contract.Songs.DURATION, song.getDuration());
        String whereClause = String.format("%s=?", Contract.Songs.ID);
        String[] whereArgs = {song.getIdSong()};
        int result = database.update(DBHelper.Tables.SONG, values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteSong(String idSong) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = Contract.Songs.ID + "=?";
        String[] whereArgs = {idSong};
        int result = database.delete(DBHelper.Tables.SONG, whereClause, whereArgs);
        return result > 0;
    }

    public SQLiteDatabase getDb() {
        return db.getWritableDatabase();
    }
}//End
