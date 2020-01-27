package com.example.mahasiswafavorit.model;

public class MahasiswaModel {

    //--------------------------------------------------
    //Model getter dan Contructor
    //--------------------------------------------------

    private String id;
    private String nama;
    private String umur;
    private int favorit;

    public MahasiswaModel(String id, String nama, String umur) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
    }

    public MahasiswaModel(String id, String nama, String umur, int favorit) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.favorit = favorit;
    }

    public int getFavorit() {
        return favorit;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getUmur() {
        return umur;
    }
}
