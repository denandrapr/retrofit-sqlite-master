package com.example.mahasiswafavorit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mahasiswafavorit.model.MahasiswaModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_mahasiswa";
    private static final String TABLE_NAME = "tb_mhs_favorit";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                "id  varchar(20) primary key, "+
                "nama varhcar(50)," +
                "umur varchar(2)," +
                "favorit int);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addRecord(MahasiswaModel mahasiswaModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", mahasiswaModel.getId());
        values.put("nama", mahasiswaModel.getNama());
        values.put("umur", mahasiswaModel.getUmur());
        values.put("favorit", mahasiswaModel.getFavorit());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<MahasiswaModel> getAllRecord(){
        List<MahasiswaModel> userList = new ArrayList<>();
        String getAllQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);
        if(cursor.moveToFirst()){
            do {
                MahasiswaModel barangModel = new MahasiswaModel(
                        cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("nama")),
                        cursor.getString(cursor.getColumnIndex("umur")),
                        cursor.getInt(cursor.getColumnIndex("favorit"))
                );
                userList.add(barangModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return userList;
    }

    public int hapus_favorit(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = '"+id+"'", null);
        db.close();

        return 1;
    }
}
