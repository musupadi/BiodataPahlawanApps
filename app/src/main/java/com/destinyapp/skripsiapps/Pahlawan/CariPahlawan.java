package com.destinyapp.skripsiapps.Pahlawan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.destinyapp.skripsiapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CariPahlawan extends Fragment {


    public CariPahlawan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cari_pahlawan, container, false);
    }

}
