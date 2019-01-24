package com.bere.dlira.music;

import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bere.dlira.music.model.Song;
import com.bere.dlira.music.sqlite.DBHelper;
import com.bere.dlira.music.sqlite.DBOperations;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DBOperations data;

    public class DataTaskTest extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            //Inserciones
            String currentDate= Calendar.getInstance().getTime().toString();
            try{
                data.getDb().beginTransaction();
                //Insert songs
                String song1 = data.insertSong(new Song(null, "Closer", "Chainsmokers", "So baby", "2016", "3.5"));

                //Delete songs
                data.deleteSong(song1);

            }finally {
                data.getDb().endTransaction();
            }

            //Querys
            //Songs
            Log.d("Songs", "Songs");
            DatabaseUtils.dumpCursor(data.getSongs());
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getApplicationContext().deleteDatabase(DBHelper.DATABASE_NAME);
        data = DBOperations.getDBOperations(getApplicationContext());
        new DataTaskTest().execute();
    }

}//End
