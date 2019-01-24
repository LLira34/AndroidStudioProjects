package com.bere.dlira.songexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private ListView lsvSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imv_photo);
        Picasso.with(this).load("http://172.16.2.180:3000/photo_store/8.jpg")
                .error(R.mipmap.ic_launcher).fit().centerInside().into(imageView);
    }

    //Method list
    private class ListWSTask extends AsyncTask<String,Integer,Boolean> {
        private String[] songs;
        @Override
        protected Boolean doInBackground(String... strings) {
            boolean result = true;
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://192.168.1.94:3000/songs.json");
            get.setHeader("content-type","application/json");
            try{
                HttpResponse response = httpClient.execute(get);
                String strResponse = EntityUtils.toString(response.getEntity());
                JSONArray jsonResponse = new JSONArray(strResponse);
                songs = new String[jsonResponse.length()];

                for (int i=0; i<jsonResponse.length(); i++){
                    JSONObject jsonObject = jsonResponse.getJSONObject(i);
                    int idSong = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String artist = jsonObject.getString("artist");
                    String album = jsonObject.getString("album");
                    songs[i] = "" + idSong + " | " + name + " | " + artist + " | " + album;
                }
            }catch (Exception e){
                Log.e("Rest service error","Error!",e);
                result= false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,songs);
                lsvSongs.setAdapter(arrayAdapter);
            }
        }
    }//End method list

}//End