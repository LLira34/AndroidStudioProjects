package com.bere.dlira.music.sqlite;

import java.util.UUID;

public class Contract {
    //Song - start
    interface SongColumns{
        String ID = "id";
        String NAME = "name";
        String ARTIST = "artist";
        String ALBUM = "album";
        String YEAR = "year";
        String DURATION = "duration";
    }
    //Song - end


    //Song method start
    public static class Songs implements SongColumns{
        public static String generateIdSong(){
            return  "SG-"+ UUID.randomUUID().toString();
        }
    }
    //Song method end

    private Contract() {}

}//End
