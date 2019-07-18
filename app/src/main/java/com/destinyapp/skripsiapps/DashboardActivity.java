package com.destinyapp.skripsiapps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.skripsiapps.API.ApiRequest;
import com.destinyapp.skripsiapps.API.RetroServer;
import com.destinyapp.skripsiapps.Fragment.DashboardFragment;
import com.destinyapp.skripsiapps.About.AboutMe;
import com.destinyapp.skripsiapps.Model.ResponseModel;
import com.destinyapp.skripsiapps.Pahlawan.CariPahlawan;
import com.destinyapp.skripsiapps.Pahlawan.DetailPahlawan;
import com.destinyapp.skripsiapps.Pahlawan.ListPahlawan;
import com.destinyapp.skripsiapps.SharedPreferance.DB_Helper;
import com.destinyapp.skripsiapps.SharedPreferance.User;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DB_Helper dbHelper;
    String User,Person;
    MenuItem LoginApp,RegisterApp,LogoutApp;
    TextView navUsername,navName;
    CircleImageView navHeaderPP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new DashboardFragment();
        Intent data = getIntent();
        //Deklarasi Variable
        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.navHeaderUsername);
        navName = (TextView)headerView.findViewById(R.id.navHeaderNama);
        navHeaderPP = (CircleImageView) headerView.findViewById(R.id.navHeaderPP);
        //DONE

        //Checker
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkSession();

        //ClickListener

        //
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                User = cursor.getString(0);
                Person = cursor.getString(1);
            }
        }
        if (User != null){
            getDataUser();
        }

        //DONE
        //IF ELSE
        String List = data.getStringExtra("LIST");
        String Data = data.getStringExtra("DATA");
        String Cari = data.getStringExtra("CARI");
        String Favorite = data.getStringExtra("FAVORITE");
        String Kuis = data.getStringExtra("KUIS");
        String Highscore = data.getStringExtra("HIGHSCORE");
        String Musik = data.getStringExtra("MUSIK");
        String DetailExtra = data.getStringExtra("DetailExtra");
        final String Nama = data.getStringExtra("Nama");
        final String Remarks = data.getStringExtra("Remarks");
        final String Photo = data.getStringExtra("Photo");
        final String Detail = data.getStringExtra("Detail");
        final String Lahir = data.getStringExtra("Lahir");
        final String Wafat = data.getStringExtra("Wafat");
        final String Langitude = data.getStringExtra("Lang");
        final String Longitude = data.getStringExtra("Long");

        if (List !=null){
            Bundle bundle = new Bundle();
            bundle.putString("LIST",List);
            bundle.putString("DATA",Data);
            fragment = new ListPahlawan();
            fragment.setArguments(bundle);
        }else if(Cari !=null){
            fragment = new CariPahlawan();
        }else if(Favorite !=null){
            fragment = new CariPahlawan();
        }else if(Kuis !=null){
            fragment = new KuisFragment();
        }else if(Highscore !=null){
            fragment = new HighScoreFragment();
        }else if(Musik !=null){
            fragment = new CariPahlawan();
        }else if(DetailExtra !=null){
            Bundle bundle = new Bundle();
            bundle.putString("Nama",Nama);
            bundle.putString("Remarks",Remarks);
            bundle.putString("Photo",Photo);
            bundle.putString("Detail",Detail);
            bundle.putString("Lahir",Lahir);
            bundle.putString("Wafat",Wafat);
            bundle.putString("Lang",Langitude);
            bundle.putString("Long",Longitude);
            fragment = new DetailPahlawan();
            fragment.setArguments(bundle);
        }
        //DONE
        ChangeFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        LoginApp = menu.findItem(R.id.action_login);
        RegisterApp = menu.findItem(R.id.action_signup);
        LogoutApp = menu.findItem(R.id.action_logout);
        if (User !=null){
            LoginApp.setVisible(false);
            RegisterApp.setVisible(false);
            LogoutApp.setVisible(true);
        }else{
            LoginApp.setVisible(true);
            RegisterApp.setVisible(true);
            LogoutApp.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(DashboardActivity.this,LoginActivity.class);
            startActivity(intent);
            //ClickListener();
            return true;
        }else if(id == R.id.action_signup){
            Intent intent = new Intent(DashboardActivity.this,RegisterActivity.class);
            startActivity(intent);
            //ClickListener();
            return true;
        }else if(id == R.id.action_logout){
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_dashboard) {
            fragment = new DashboardFragment();
        }else if (id == R.id.nav_about) {
            fragment = new AboutMe();
        }
        ChangeFragment(fragment);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.Container,fragment);
            ft.commit();
        }
    }
    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda Yakin ingin Logout ?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                        dbHelper = new DB_Helper(DashboardActivity.this);
                        dbHelper.userLogout(User,DashboardActivity.this);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                //Set your icon here
                .setTitle("Perhatian !!!")
                .setIcon(R.drawable.ic_close_black_24dp);
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void getDataUser(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> data = api.Datauser(User);
        data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                getImageFromURL(response.body().getProfile());
                navName.setText(response.body().getNama());
                navUsername.setText(response.body().getUsername());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DashboardActivity.this,R.string.koneksi_gagal,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getImageFromURL(String url){
        String BASE_URL = getString(R.string.base_url);
        String URL = BASE_URL+"Profile/"+url;
        Glide.with(DashboardActivity.this)
                .load(URL)
                .into(navHeaderPP);
    }
}
