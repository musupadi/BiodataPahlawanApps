package com.destinyapp.skripsiapps.Pahlawan;


import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.destinyapp.skripsiapps.DashboardActivity;
import com.destinyapp.skripsiapps.Maps.MapsActivity;
import com.destinyapp.skripsiapps.Model.Pahlawan;
import com.destinyapp.skripsiapps.R;
import com.destinyapp.skripsiapps.SharedPreferance.DB_Helper;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPahlawan extends Fragment {

    ImageView photo;
    TextView nama,remarks,lahir,wafat,detail;
    Button Favorite,Makam;
    DB_Helper dbHelper;

    public DetailPahlawan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pahlawan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //GetString
        final String Nama = this.getArguments().getString("Nama").toString();
        final String Remarks = this.getArguments().getString("Remarks").toString();
        final String Photo = this.getArguments().getString("Photo").toString();
        final String Detail = this.getArguments().getString("Detail").toString();
        final String Lahir = this.getArguments().getString("Lahir").toString();
        final String Wafat = this.getArguments().getString("Wafat").toString();
        final String Langitude = this.getArguments().getString("Lang").toString();
        final String Longitude = this.getArguments().getString("Long").toString();
        final String Suara = this.getArguments().getString("Suara").toString();
        //Done
        final MediaPlayer SuaraMe = MediaPlayer.create(getActivity(),Integer.parseInt(Suara));
        SuaraMe.start();
        //Deklarasi Variabel
        photo = (ImageView)view.findViewById(R.id.ivPhoto);
        nama = (TextView)view.findViewById(R.id.tvNama);
        remarks = (TextView)view.findViewById(R.id.tvJabatan);
        detail = (TextView)view.findViewById(R.id.tvDetail);
        lahir = (TextView)view.findViewById(R.id.tvLahir);
        wafat = (TextView)view.findViewById(R.id.tvWafat);
        Makam = (Button)view.findViewById(R.id.btn_set_share);
        Favorite = (Button)view.findViewById(R.id.btn_set_favorite);
        //Done
        getData(Nama,Remarks,Photo,Detail,Lahir,Wafat);
        Makam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataMap(Nama,Remarks,Photo,Detail,Lahir,Wafat,Langitude,Longitude);
            }
        });
        dbHelper = new DB_Helper(getActivity());
        Cursor cursors = dbHelper.checkPahlawan(Nama);;
        if (cursors.getCount()>0){
            Favorite.setText("Terfavorit");
        }else{
            Favorite.setText("Favorit");
        }
        Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursors = dbHelper.checkPahlawan(Nama);;
                if (cursors.getCount()>0){
                    dbHelper.deletePahlawanRecord(Nama,getActivity());
                    Favorite.setText("Favorit");
                }else{
                    Pahlawan pahlawan = new Pahlawan(Nama,Remarks,Photo,Detail,Lahir,Wafat,Langitude,Longitude,Suara);
                    dbHelper.FavoritePahlawan(pahlawan);
                    Favorite.setText("Terfavorit");
                }
            }
        });
    }
    private void getDataMap(String Nama,String Remarks,String Photo,String Detail,String Lahir,String Wafat,String Langitude,String Longitude){
        Intent goInput = new Intent(getActivity(), MapsActivity.class);
        goInput.putExtra("Nama",Nama);
        goInput.putExtra("Remarks",Remarks);
        goInput.putExtra("Photo",Photo);
        goInput.putExtra("Detail",Detail);
        goInput.putExtra("Lahir",Lahir);
        goInput.putExtra("Wafat",Wafat);
        goInput.putExtra("Lang",Langitude);
        goInput.putExtra("Long",Longitude);
        getActivity().startActivities(new Intent[]{goInput});
    }
    private void getData(String Nama,String Remarks,String Photo,String Detail,String Lahir,String Wafat){
        nama.setText(Nama);
        remarks.setText(Remarks);
        detail.setText(Detail);
        lahir.setText(Lahir);
        wafat.setText(Wafat);
        getImageFromURL(Photo);
    }
    private void getImageFromURL(String Photo){
        Glide.with(getActivity())
                .load(Photo)
                .into(photo);
    }
}
