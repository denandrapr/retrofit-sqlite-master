package com.example.mahasiswafavorit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mahasiswafavorit.R;
import com.example.mahasiswafavorit.adapter.MahasiswaAdapter;
import com.example.mahasiswafavorit.adapter.MahasiswaLocalAdapter;
import com.example.mahasiswafavorit.db.DatabaseHandler;
import com.example.mahasiswafavorit.model.MahasiswaModel;

import java.util.ArrayList;
import java.util.List;

public class DaftarFavoritActivity extends AppCompatActivity {

    private List<MahasiswaModel> mhsList = new ArrayList<>();
    private MahasiswaLocalAdapter mahasiswaAdapter;
    private RecyclerView mrecyclerView;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_favorit);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        dbHandler.getWritableDatabase();

        mrecyclerView = findViewById(R.id.recyclerView);

        db = new DatabaseHandler(this);
        mhsList.addAll(db.getAllRecord());

        mahasiswaAdapter = new MahasiswaLocalAdapter(this, mhsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setLayoutManager(layoutManager);
        mrecyclerView.setAdapter(mahasiswaAdapter);
    }
}
