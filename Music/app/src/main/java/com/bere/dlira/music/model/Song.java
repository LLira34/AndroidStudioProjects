package com.bere.dlira.music.model;

/**
 * Created by DLira on 25/03/2018.
 */

public class Song {
    private String idSong;
    private String name;
    private String artist;
    private String album;
    private String year;
    private String duration;

    //Constructor
    public Song(String idSong, String name, String artist, String album, String  year, String duration) {
        this.idSong = idSong;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.duration = duration;
    }

    //Constructor empty
    public Song(){
        this("", "", "", "", "","");
    }

    //Generated getters and setters
    public String getIdSong() {return idSong;}

    public void setIdSong(String idSong) {this.idSong = idSong;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getArtist() {return artist;}

    public void setArtist(String artist) {this.artist = artist;}

    public String getAlbum() {return album;}

    public void setAlbum(String album) {this.album = album;}

    public String getYear() {return year;}

    public void setYear(String year) {this.year = year;}

    public String getDuration() {return duration;}

    public void setDuration(String duration) {this.duration = duration;}

    //Generated toString
    @Override
    public String toString() {
        return "Song{" +
                "idSong='" + idSong + '\'' +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                '}';
    }

}//End
