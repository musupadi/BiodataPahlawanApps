package com.destinyapp.skripsiapps.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.destinyapp.skripsiapps.DashboardActivity;
import com.destinyapp.skripsiapps.Pahlawan.ListPahlawan;
import com.destinyapp.skripsiapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    Button list,cari,favorite,highscore,musik,kuis;
    Context context;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (Button)view.findViewById(R.id.btnBiodata);
        cari = (Button)view.findViewById(R.id.btnCariBiodata);
        favorite = (Button)view.findViewById(R.id.btnFavoriteSaya);
        highscore = (Button)view.findViewById(R.id.btnHighScore);
        musik = (Button)view.findViewById(R.id.btnMusikNasional);
        kuis = (Button)view.findViewById(R.id.btnKuis);



        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(), DashboardActivity.class);
                goInput.putExtra("LIST","All");
                goInput.putExtra("DATA","123");
                getActivity().startActivities(new Intent[]{goInput});
            }
        });
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(), DashboardActivity.class);
                goInput.putExtra("CARI","mencari");
                getActivity().startActivities(new Intent[]{goInput});
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(), DashboardActivity.class);
                goInput.putExtra("FAVORITE","favorite");
                getActivity().startActivities(new Intent[]{goInput});
            }
        });
        kuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(), DashboardActivity.class);
                goInput.putExtra("KUIS","kuis");
                getActivity().startActivities(new Intent[]{goInput});
            }
        });
        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(), DashboardActivity.class);
                goInput.putExtra("HIGHSCORE","highscore");
                getActivity().startActivities(new Intent[]{goInput});
            }
        });
        musik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(), DashboardActivity.class);
                goInput.putExtra("MUSIK","musik");
                getActivity().startActivities(new Intent[]{goInput});
            }
        });

    }
}
