package com.destinyapp.skripsiapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.destinyapp.skripsiapps.API.ApiRequest;
import com.destinyapp.skripsiapps.API.RetroServer;
import com.destinyapp.skripsiapps.Model.ResponseModel;
import com.destinyapp.skripsiapps.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button Register,Kembali;
    EditText Username,Password,Nama,Email;
    String DefaultProfile = "supriyadi.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Nama = (EditText)findViewById(R.id.etNama);
        Email = (EditText)findViewById(R.id.etEmail);
        Register = (Button)findViewById(R.id.btnRegister);
        Kembali = (Button)findViewById(R.id.btnKembali);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Daftar();
            }
        });
        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
    private void Daftar(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Sedang Mengambil Data Ke Server");
        pd.setCancelable(false);
        pd.show();
        final String username = Username.getText().toString();
        final String password = Password.getText().toString();
        final String nama = Nama.getText().toString();
        final String email = Email.getText().toString();
        if (username.isEmpty() || password.isEmpty() || nama.isEmpty() || email.isEmpty()){
            Toast.makeText(RegisterActivity.this,"Data Username,Password,Nama atau Email Belum Terisi",Toast.LENGTH_SHORT).show();
            pd.hide();
        }else{
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            Call<ResponseModel> register = api.register(username,password,nama,email,DefaultProfile);
            register.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.hide();
                    if (response.body().getResponse().equals("Insert")){
                        Toast.makeText(RegisterActivity.this,"Register Berhasil",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else if(response.body().getResponse().equals("Update")){
                        Toast.makeText(RegisterActivity.this,"Username Sudah Digunakan !!",Toast.LENGTH_SHORT).show();
                    }else if(response.body().getResponse().equals("Error")){
                        Toast.makeText(RegisterActivity.this,R.string.data_error,Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(RegisterActivity.this,R.string.koneksi_gagal,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
