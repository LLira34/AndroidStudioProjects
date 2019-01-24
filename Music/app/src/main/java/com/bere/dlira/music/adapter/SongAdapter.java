package com.bere.dlira.music.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bere.dlira.music.model.Song;
import com.bere.dlira.music.*;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter <SongAdapter.SongViewHolder> implements View.OnClickListener {
    List<Song> songs;
    View.OnClickListener listener;

    //Constructor
    public SongAdapter(List<Song> songs){
        this.songs = songs;
    }

    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_cell_layout,parent,false);
        SongViewHolder holder=new SongViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.txvName.setText(songs.get(position).getName());
        holder.txvArtist.setText(songs.get(position).getArtist());
        holder.txvAlbum.setText(songs.get(position).getAlbum());
        holder.txvYear.setText(songs.get(position).getYear());
        holder.txvDuration.setText(songs.get(position).getDuration());
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class SongViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView crvSong;
        TextView txvName;
        TextView txvArtist;
        TextView txvAlbum;
        TextView txvYear;
        TextView txvDuration;
        ImageButton btnEdit;
        ImageButton btnDelete;
        View.OnClickListener listener;

        public SongViewHolder(View itemView) {
            super(itemView);
            crvSong = (CardView) itemView.findViewById(R.id.crv_song);
            txvName = (TextView) itemView.findViewById(R.id.txv_name);
            txvArtist = (TextView) itemView.findViewById(R.id.txv_artist);
            txvAlbum = (TextView) itemView.findViewById(R.id.txv_album);
            txvYear = (TextView) itemView.findViewById(R.id.txv_year);
            txvDuration = (TextView) itemView.findViewById(R.id.txv_duration);
            btnEdit = (ImageButton) itemView.findViewById(R.id.btn_edit);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btn_delete);

            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){
            this.listener=listener;
        }
    }
}//End
