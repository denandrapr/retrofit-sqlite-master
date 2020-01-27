package com.example.mahasiswafavorit.api;

import com.example.mahasiswafavorit.model.Mahasiswa;
import com.example.mahasiswafavorit.model.MahasiswaModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //--------------------------------------------------
    //Class untuk Mengambil endpoint dari API webservice
    //--------------------------------------------------

    @GET("uas/getData.php")
    Call<Mahasiswa> getMahasiwa();

}
