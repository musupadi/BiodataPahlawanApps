package com.destinyapp.skripsiapps;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

import com.destinyapp.skripsiapps.Fragment.DashboardFragment;
import com.destinyapp.skripsiapps.About.AboutMe;
import com.destinyapp.skripsiapps.Pahlawan.CariPahlawan;
import com.destinyapp.skripsiapps.Pahlawan.DetailPahlawan;
import com.destinyapp.skripsiapps.Pahlawan.ListPahlawan;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Dialog myDialog;
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
        myDialog = new Dialog(this);
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
            fragment = new CariPahlawan();
        }else if(Highscore !=null){
            fragment = new CariPahlawan();
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
            myDialog.setContentView(R.layout.layout_dialog_login);
            myDialog.show();
            return true;
        }else if(id == R.id.action_register){
            myDialog.setContentView(R.layout.layout_dialog_register);
            return true;
        }else if(id == R.id.action_logout){
            return true;
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
}
