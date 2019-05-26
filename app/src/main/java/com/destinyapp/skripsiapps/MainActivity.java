package com.destinyapp.skripsiapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.destinyapp.skripsiapps.SharedPreferance.DB_Helper;

public class MainActivity extends AppCompatActivity {
    Button sub;
    TextView Nama;
    ImageView logo;
    Animation fromBottom,fromTop,fromLeft;
    private DB_Helper dbHelper;
    String User,Person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkSession();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                User = cursor.getString(0);
                Person = cursor.getString(1);
            }
        }
        if (User != null){
            Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
            startActivity(intent);
        }
        Nama = (TextView)findViewById(R.id.tvNama);
        sub = (Button)findViewById(R.id.btnMasukApps);
        logo = (ImageView)findViewById(R.id.ivLogo);

        fromBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromTop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromLeft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        sub.setAnimation(fromBottom);
        logo.setAnimation(fromTop);
        Nama.setAnimation(fromLeft);
        final ProgressDialog pd = new ProgressDialog(this);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Mempersiapkan Data");
                pd.setCancelable(false);
                pd.show();
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
                pd.hide();
            }
        });
    }
}
