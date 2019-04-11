package com.ferinabay.infokampus1.model;

public class Kampus {

    long idkampus;
    String name, fakultas, alamat, akreditasi, about;

    public Kampus(){

    }

    public Kampus( String name, String alamat, String fakultas, String akreditasi, String about) {

        this.name = name;
        this.fakultas = fakultas;
        this.alamat = alamat;
        this.akreditasi = akreditasi;
        this.about = about;
    }

    public long getIdkampus() {
        return idkampus;
    }

    public void setIdkampus(int idkampus) {
        this.idkampus = idkampus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAkreditasi() {
        return akreditasi;
    }

    public void setAkreditasi(String akreditasi) {
        this.akreditasi = akreditasi;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
