package com.destinyapp.skripsiapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.destinyapp.skripsiapps.DashboardActivity;
import com.destinyapp.skripsiapps.Maps.MapsActivity;
import com.destinyapp.skripsiapps.Model.ModelPahlawan;
import com.destinyapp.skripsiapps.Model.Pahlawan;
import com.destinyapp.skripsiapps.R;
import com.destinyapp.skripsiapps.SharedPreferance.DB_Helper;

import java.util.ArrayList;

public class AdapterPahlawan extends RecyclerView.Adapter<AdapterPahlawan.CardViewViewHolder>{
    private Context context;
    private ArrayList<ModelPahlawan> listPahlawan;
    DB_Helper dbHelper;
    private ArrayList<ModelPahlawan> getListPahlawan() {
        return listPahlawan;
    }
    public void setListPahlawan(ArrayList<ModelPahlawan> listPahlawan) {
        this.listPahlawan = listPahlawan;
    }
    public AdapterPahlawan(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_pahlawan, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder cardViewViewHolder, int i) {
        final ModelPahlawan p = getListPahlawan().get(i);

        Glide.with(context)
                .load(p.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);
        cardViewViewHolder.tvName.setText(p.getNama());
        cardViewViewHolder.tvRemarks.setText(p.getRemarks());
        String names=null;
        dbHelper = new DB_Helper(context);
        Cursor cursors = dbHelper.checkPahlawan(p.getNama());;
        if (cursors.getCount()>0){
            cardViewViewHolder.btnFavorite.setText("Terfavorit");
        }else{
            cardViewViewHolder.btnFavorite.setText("Favorit");
        }
        cardViewViewHolder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                String name=null;
                Cursor cursors = dbHelper.checkPahlawan(p.getNama());;
                if (cursors.getCount()>0){
                    while (cursors.moveToNext()){
                        name = cursors.getString(0);
                    }
                }
                if (name != null){
                    dbHelper.deletePahlawanRecord(p.getNama(),context);
                    cardViewViewHolder.btnFavorite.setText("Favorit");
                }else{
                    Toast.makeText(context,"Pahlawan Berhasil Difavoritkan",Toast.LENGTH_SHORT).show();
                    Pahlawan pahlawan = new Pahlawan(p.getNama(),p.getRemarks(),p.getPhoto(),p.getDetail(),p.getLahir(),p.getWafat(),p.getLangitude(),p.getLongitude());
                    dbHelper.FavoritePahlawan(pahlawan);
                    cardViewViewHolder.btnFavorite.setText("Terfavorit");
                }
            }
        }));
        cardViewViewHolder.btnShare.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent goInput = new Intent(context, MapsActivity.class);
                goInput.putExtra("Nama",p.getNama());
                goInput.putExtra("Remarks",p.getRemarks());
                goInput.putExtra("Photo",p.getPhoto());
                goInput.putExtra("Detail",p.getDetail());
                goInput.putExtra("Lahir",p.getLahir());
                goInput.putExtra("Wafat",p.getWafat());
                goInput.putExtra("Lang",p.getLangitude());
                goInput.putExtra("Long",p.getLongitude());
                context.startActivities(new Intent[]{goInput});
            }
        }));
        cardViewViewHolder.layoutCardView.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent goInput = new Intent(context, DashboardActivity.class);
                goInput.putExtra("DetailExtra","detail");
                goInput.putExtra("Nama",getListPahlawan().get(position).getNama());
                goInput.putExtra("Remarks",getListPahlawan().get(position).getRemarks());
                goInput.putExtra("Photo",getListPahlawan().get(position).getPhoto());
                goInput.putExtra("Detail",getListPahlawan().get(position).getDetail());
                goInput.putExtra("Lahir",getListPahlawan().get(position).getLahir());
                goInput.putExtra("Wafat",getListPahlawan().get(position).getWafat());
                goInput.putExtra("Lang",getListPahlawan().get(position).getLangitude());
                goInput.putExtra("Long",getListPahlawan().get(position).getLongitude());
                context.startActivities(new Intent[]{goInput});
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListPahlawan().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvName,tvRemarks;
        Button btnFavorite,btnShare;
        LinearLayout layoutCardView;
        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
            layoutCardView = itemView.findViewById(R.id.LayoutCardView);
        }
    }
}


