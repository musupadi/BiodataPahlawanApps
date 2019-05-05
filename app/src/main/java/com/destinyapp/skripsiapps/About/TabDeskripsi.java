package com.destinyapp.skripsiapps.About;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.skripsiapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabDeskripsi extends Fragment {
    TextView deskripsi;

    public TabDeskripsi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_deskripsi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        deskripsi = (TextView)view.findViewById(R.id.Deskripsi);
        final MediaPlayer SuaraMe = MediaPlayer.create(getActivity(),R.raw.sherisingshieldhero);
        deskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Tekan Untuk Mendengarkan Deskripsi Saya",Toast.LENGTH_SHORT).show();
            }
        });
        deskripsi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SuaraMe.start();
                return true;
            }
        });


    }
}
