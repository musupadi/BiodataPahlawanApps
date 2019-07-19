package com.destinyapp.skripsiapps.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.skripsiapps.Model.DataModel;
import com.destinyapp.skripsiapps.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListHighScore extends RecyclerView.Adapter<AdapterListHighScore.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    public AdapterListHighScore (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_highscore,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListHighScore.HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        holderData.rank.setText(String.valueOf(posistion+1));
        holderData.score.setText(dm.getScore());
        holderData.username.setText("Username : "+dm.getUsername());
        holderData.nama.setText("Nama : "+dm.getNama());
        holderData.tanggal.setText(dm.getTanggal());
        holderData.dm=dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView rank,score,username,nama,tanggal;
        DataModel dm;
        HolderData(View v){
            super(v);
            rank = (TextView)v.findViewById(R.id.tvRank);
            score= (TextView)v.findViewById(R.id.tvScore);
            username= (TextView)v.findViewById(R.id.tvUsername);
            nama = (TextView)v.findViewById(R.id.tvNama);
            tanggal = (TextView)v.findViewById(R.id.tvTanggal);
        }
    }

}
