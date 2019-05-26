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
import com.destinyapp.skripsiapps.SharedPreferance.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText Username,Password;
    Button Login,Kembali;
    private DB_Helper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Kembali = (Button)findViewById(R.id.btnKembali);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
    private void SessionLoginSucces(String Username,String Password){
        String Person="User";
        dbHelper = new DB_Helper(this);
        if(Username.isEmpty()){
            Toast.makeText(LoginActivity.this,"Silahkan Masukan Username",Toast.LENGTH_SHORT).show();
        }
        if(Password.isEmpty()){
            Toast.makeText(LoginActivity.this,"Silahkan Masukan Password",Toast.LENGTH_SHORT).show();
        }
        User user = new User(Username,Person);
        dbHelper.saveSession(user);
    }
    private void LoginUser(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Sedang Mengambil Data Ke Server");
        pd.setCancelable(false);
        pd.show();
        final String username = Username.getText().toString();
        final String password = Password.getText().toString();
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this,"Username atau Password Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
            pd.hide();
        }else{
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            Call<ResponseModel> login = api.login(username,password);
            login.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.hide();
                    String ress = response.body().getResponse();
                    if(ress.equals("succes")){
                        SessionLoginSucces(username,password);
                        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Silahkan Masukan Password",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(LoginActivity.this,R.string.koneksi_gagal,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
