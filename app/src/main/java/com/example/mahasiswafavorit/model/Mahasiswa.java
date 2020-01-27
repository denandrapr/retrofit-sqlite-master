package com.example.mahasiswafavorit.model;

import java.util.List;

public class Mahasiswa {
    private List<MahasiswaModel> data;

    public Mahasiswa(List<MahasiswaModel> data) {
        this.data = data;
    }

    public List<MahasiswaModel> getData() {
        return data;
    }
}
