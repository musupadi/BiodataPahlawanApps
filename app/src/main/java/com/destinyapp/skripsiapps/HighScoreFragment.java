package com.destinyapp.skripsiapps;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.destinyapp.skripsiapps.API.ApiRequest;
import com.destinyapp.skripsiapps.API.RetroServer;
import com.destinyapp.skripsiapps.Adapter.AdapterListHighScore;
import com.destinyapp.skripsiapps.Model.DataModel;
import com.destinyapp.skripsiapps.Model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighScoreFragment extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.LayoutManager mManager;
    RecyclerView HighScore;
    Button Kuis;
    public HighScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_high_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HighScore = (RecyclerView)view.findViewById(R.id.recycler);
        Kuis = (Button)view.findViewById(R.id.btnKuis);
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        HighScore.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> GetAllDataGuru = api.HighScore();
        GetAllDataGuru.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                mItems=response.body().getResult();
                mAdapter = new AdapterListHighScore(getActivity(),mItems);
                HighScore.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(),R.string.koneksi_gagal,Toast.LENGTH_SHORT).show();
            }
        });
        Kuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(),KuisActivity.class);
                goInput.putExtra("NO",String.valueOf(0));
                goInput.putExtra("SCORE",String.valueOf(0));
                getActivity().startActivities(new Intent[]{goInput});
            }
        });
    }
}
