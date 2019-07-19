package com.destinyapp.skripsiapps;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.skripsiapps.API.ApiRequest;
import com.destinyapp.skripsiapps.API.RetroServer;
import com.destinyapp.skripsiapps.Model.ResponseModel;
import com.destinyapp.skripsiapps.SharedPreferance.DB_Helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class KuisFragment extends Fragment {

    Button Kuis,Save;
    TextView score;
    String User,Person;
    private DB_Helper dbHelper;
    public KuisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kuis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        score=(TextView)view.findViewById(R.id.tvScore);
        Kuis=(Button)view.findViewById(R.id.btnKuis);
        Save=(Button)view.findViewById(R.id.btnSave);
        final String Score = this.getArguments().getString("SCORE").toString();
        score.setText(Score);
        Kuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(),KuisActivity.class);
                goInput.putExtra("NO",String.valueOf(0));
                goInput.putExtra("SCORE",String.valueOf(0));
                getActivity().startActivities(new Intent[]{goInput});
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiRequest api = RetroServer.getClient().create(ApiRequest.class);

                //Checker
                dbHelper = new DB_Helper(getActivity());
                Cursor cursor = dbHelper.checkSession();

                //ClickListener

                //
                if (cursor.getCount()>0){
                    while (cursor.moveToNext()){
                        User = cursor.getString(0);
                    }
                }
                if (User != null){
                    final ProgressDialog pd = new ProgressDialog(getActivity());
                    pd.setMessage("Sedang Menyimpan Data ke Server");
                    pd.setCancelable(false);
                    pd.show();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String thisDay = dateFormat.format(date);
                    Call<ResponseModel> insertDataGuru = api.InsertScore(User,Score,thisDay);
                    insertDataGuru.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            Toast.makeText(getActivity(),"Data Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                            Intent goInput = new Intent(getActivity(), DashboardActivity.class);
                            goInput.putExtra("HIGHSCORE","highscore");
                            getActivity().startActivities(new Intent[]{goInput});
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Toast.makeText(getActivity(),R.string.koneksi_gagal,Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(User == null){
                    Toast.makeText(getActivity(),"Silahkan Login atau Register untuk menyimpan Score",Toast.LENGTH_SHORT).show();
                }




            }
        });

    }
}
