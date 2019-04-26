package com.destinyapp.skripsiapps.Pahlawan;


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
import com.destinyapp.skripsiapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPahlawan extends Fragment {

    ImageView photo;
    TextView nama,remarks,lahir,wafat,detail;
    Button Favorite,Share;

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
        //Done

        //Deklarasi Variabel
        photo = (ImageView)view.findViewById(R.id.ivPhoto);
        nama = (TextView)view.findViewById(R.id.tvNama);
        remarks = (TextView)view.findViewById(R.id.tvJabatan);
        detail = (TextView)view.findViewById(R.id.tvDetail);
        lahir = (TextView)view.findViewById(R.id.tvLahir);
        wafat = (TextView)view.findViewById(R.id.tvWafat);
        //Done
        getData(Nama,Remarks,Photo,Detail,Lahir,Wafat);
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
