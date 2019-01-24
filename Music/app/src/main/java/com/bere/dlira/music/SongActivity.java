package com.bere.dlira.music;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bere.dlira.music.adapter.SongAdapter;
import com.bere.dlira.music.model.Song;
import com.bere.dlira.music.sqlite.DBOperations;

import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtArtist;
    private EditText edtAlbum;
    private EditText edtYear;
    private EditText edtDuration;
    private Button btnSaveSong;
    private DBOperations operations;
    private Song song = new Song();
    private RecyclerView rcvSongs;
    private List<Song> songs = new ArrayList<>();
    private SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        operations = DBOperations.getDBOperations(getApplicationContext());
        song.setIdSong("");
        initComponents();
    }

    private void initComponents(){
        rcvSongs = (RecyclerView)findViewById(R.id.rcv_list);
        rcvSongs.setHasFixedSize(true);
        LinearLayoutManager layoutManeger = new LinearLayoutManager(this);
        rcvSongs.setLayoutManager(layoutManeger);
        getSongs();
        adapter = new SongAdapter(songs);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_delete:
                        operations.deleteSong(songs.get(rcvSongs.getChildPosition((View)v.getParent().getParent())).getIdSong());
                        getSongs();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_edit:
                        Toast.makeText(getApplicationContext(),"Edit song.",Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getSongById(songs.get(rcvSongs.getChildPosition((View)v.getParent().getParent())).getIdSong());
                            if (c!=null){
                                if (c.moveToFirst()){
                                    song = new Song(c.getString(1),c.getString(2),c.getString(3),c.getString(4), c.getString(4), c.getString(5));
                                }
                                edtName.setText(song.getName());
                                edtArtist.setText(song.getArtist());
                                edtAlbum.setText(song.getAlbum());
                                edtYear.setText(song.getYear());
                                edtYear.setText(String.valueOf(song.getYear()));
                                edtDuration.setText(String.valueOf(song.getDuration()));
                            }else{
                                Toast.makeText(getApplicationContext(),"Record not found.",Toast.LENGTH_SHORT).show();
                            }
                        break;
                }
            }
        });

        rcvSongs.setAdapter(adapter);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtArtist = (EditText) findViewById(R.id.edt_artist);
        edtAlbum = (EditText) findViewById(R.id.edt_album);
        edtYear = (EditText) findViewById(R.id.edt_year);
        edtDuration = (EditText) findViewById(R.id.edt_duration);
        btnSaveSong = (Button) findViewById(R.id.btn_save_song);

        btnSaveSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!song.getIdSong().equals("")){
                    song.setName(edtName.getText().toString());
                    song.setArtist(edtArtist.getText().toString());
                    song.setAlbum(edtAlbum.getText().toString());
                    song.setYear(edtYear.getText().toString());
                    song.setDuration(edtDuration.getText().toString());
                    operations.updateSong(song);
                }else {
                    song = new Song("", edtName.getText().toString(), edtArtist.getText().toString(), edtAlbum.getText().toString(),
                            edtYear.getText().toString(), edtDuration.getText().toString());
                    operations.insertSong(song);
                }
                getSongs();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getSongs(){
        Cursor c = operations.getSongs();
        songs.clear();
        if(c!=null){
            while (c.moveToNext()){
                songs.add(new Song(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
            }
        }
    }

    private void clearData(){
        edtName.setText("");
        edtArtist.setText("");
        edtAlbum.setText("");
        edtYear.setText("");
        edtDuration.setText("");
        song = null;
        song = new Song();
        song.setIdSong("");
    }

}//End
