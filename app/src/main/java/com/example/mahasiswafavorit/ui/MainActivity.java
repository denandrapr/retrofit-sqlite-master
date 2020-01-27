package com.example.mahasiswafavorit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.example.mahasiswafavorit.R;
import com.example.mahasiswafavorit.adapter.MahasiswaAdapter;
import com.example.mahasiswafavorit.api.ApiBuilder;
import com.example.mahasiswafavorit.api.ApiService;
import com.example.mahasiswafavorit.db.DatabaseHandler;
import com.example.mahasiswafavorit.model.Mahasiswa;
import com.example.mahasiswafavorit.model.MahasiswaModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<MahasiswaModel> mhsList = new ArrayList<>();
    private MahasiswaAdapter mahasiswaAdapter;
    private RecyclerView mrecyclerView;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(this);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        dbHandler.getWritableDatabase();

        recyclerViewInit();

        getData();
    }

    private void recyclerViewInit() {
        mrecyclerView = findViewById(R.id.recyclerView);

        mahasiswaAdapter = new MahasiswaAdapter(this, mhsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setLayoutManager(layoutManager);
        mrecyclerView.setAdapter(mahasiswaAdapter);
    }

    private void getData() {
        ApiService apiService = ApiBuilder.getRetrofit().create(ApiService.class);
        Call<Mahasiswa> call = apiService.getMahasiwa();

        call.enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                if (response.isSuccessful()){
                    mhsList = response.body().getData();
                    mahasiswaAdapter = new MahasiswaAdapter(MainActivity.this, mhsList);
                    mrecyclerView.setAdapter(mahasiswaAdapter);
                }else{
                }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == fab){
            Intent i = new Intent(MainActivity.this, DaftarFavoritActivity.class);
            startActivity(i);
        }
    }
}
