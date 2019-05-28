package com.destinyapp.skripsiapps.SharedPreferance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.destinyapp.skripsiapps.Model.Pahlawan;

import java.util.LinkedList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "session.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "session";
    public static final String COLUMN_USERNME = "username";
    public static final String COLUMN_PERSON = "person";
    public static final String TABLE_NAME_BIODATA = "biodata";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_DETAIL = "detail";
    public static final String COLUMN_LAHIR = "lahir";
    public static final String COLUMN_WAFAT = "wafat";
    public static final String COLUMN_LANG = "langitude";
    public static final String COLUMN_LONG = "longitude";

    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_BIODATA+" (" +
                COLUMN_NAMA+" TEXT NOT NULL, "+
                COLUMN_REMARKS+" TEXT NOT NULL,"+
                COLUMN_PHOTO+" TEXT NOT NULL,"+
                COLUMN_DETAIL+" TEXT NOT NULL,"+
                COLUMN_LAHIR+" TEXT NOT NULL,"+
                COLUMN_WAFAT+" TEXT NOT NULL,"+
                COLUMN_LANG+" TEXT NOT NULL,"+
                COLUMN_LONG+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (" +
                COLUMN_USERNME+" TEXT PRIMARY KEY, "+
                COLUMN_PERSON+" TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BIODATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public List<Pahlawan> pahlawankuList() {
        String query = "SELECT  * FROM " + TABLE_NAME_BIODATA;

        List<Pahlawan> pahlawanLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Pahlawan pahlawan;

        if (cursor.moveToFirst()) {
            do {
                pahlawan = new Pahlawan();
                pahlawan.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_NAMA)));
                pahlawan.setRemarks(cursor.getString(cursor.getColumnIndex(COLUMN_REMARKS)));
                pahlawan.setPhoto(cursor.getString(cursor.getColumnIndex(COLUMN_PHOTO)));
                pahlawan.setDetail(cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL)));
                pahlawan.setLahir(cursor.getString(cursor.getColumnIndex(COLUMN_LAHIR)));
                pahlawan.setWafat(cursor.getString(cursor.getColumnIndex(COLUMN_WAFAT)));
                pahlawan.setLangitude(cursor.getString(cursor.getColumnIndex(COLUMN_LANG)));
                pahlawan.setLongitude(cursor.getString(cursor.getColumnIndex(COLUMN_LONG)));
                pahlawanLinkedList.add(pahlawan);
            } while (cursor.moveToNext());
        }
        return pahlawanLinkedList;
    }
    public void FavoritePahlawan(Pahlawan pahlawan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, pahlawan.getNama());
        values.put(COLUMN_REMARKS, pahlawan.getRemarks());
        values.put(COLUMN_PHOTO, pahlawan.getPhoto());
        values.put(COLUMN_DETAIL, pahlawan.getDetail());
        values.put(COLUMN_LAHIR,pahlawan.getLahir());
        values.put(COLUMN_WAFAT,pahlawan.getWafat());
        values.put(COLUMN_LANG,pahlawan.getLangitude());
        values.put(COLUMN_LONG,pahlawan.getLongitude());

        // insert
        db.insert(TABLE_NAME_BIODATA,null, values);
        db.close();
    }
    public void deletePahlawanRecord(String nama, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_BIODATA+" WHERE nama='"+nama+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }
    public void saveSession(User user){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNME, user.getUsername());
        values.put(COLUMN_PERSON,user.getPerson());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void userLogout(String username,Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+"");
        Toast.makeText(context, "Logout Berhasil", Toast.LENGTH_SHORT).show();
    }
    public Cursor checkPahlawan(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_BIODATA+" WHERE nama = '"+nama+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor checkSession(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME+"";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}

