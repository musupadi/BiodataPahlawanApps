package com.destinyapp.skripsiapps.Pahlawan;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.destinyapp.skripsiapps.Adapter.AdapterPahlawan;
import com.destinyapp.skripsiapps.Model.ModelPahlawan;
import com.destinyapp.skripsiapps.Model.Pahlawan;
import com.destinyapp.skripsiapps.Model.PahlawanData;
import com.destinyapp.skripsiapps.R;
import com.destinyapp.skripsiapps.SharedPreferance.DB_Helper;
import com.destinyapp.skripsiapps.SharedPreferance.PahlawanAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPahlawan extends Fragment {
    private RecyclerView rvCategory;
    private ArrayList<ModelPahlawan> pList = new ArrayList<>();
    DB_Helper dbHelper;

    public ListPahlawan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_pahlawan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Variable
        rvCategory = (RecyclerView)view.findViewById(R.id.recycler);
        //Done
        //getArguments
        final String List = this.getArguments().getString("LIST").toString();
        final String Data = this.getArguments().getString("DATA").toString();
        //Done
        pList.addAll(PahlawanData.getListData());
        if (List.equals("All")){
            rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
            AdapterPahlawan cardViewPresidentAdapter = new AdapterPahlawan(getActivity());
            cardViewPresidentAdapter.setListPahlawan(pList);
            rvCategory.setAdapter(cardViewPresidentAdapter);
        }else if(Data.equals("All")){
            rvCategory.setHasFixedSize(true);
            dbHelper = new DB_Helper(getActivity());
            rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
            PahlawanAdapter pahlawanAdapter = new PahlawanAdapter(dbHelper.pahlawankuList(),getActivity(),rvCategory);
            rvCategory.setAdapter(pahlawanAdapter);
        }

    }
}
