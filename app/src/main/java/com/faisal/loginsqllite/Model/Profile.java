package com.faisal.loginsqllite.Model;

public class Profile {

    private String id, nip, pass, nama_karyawan, divisi, unit, lokasi, jabatan, hp,  email;

    public Profile() {
    }

    public Profile (String id, String nip, String pass, String nama_karyawan, String divisi,
                    String unit, String lokasi, String jabatan, String hp, String email ) {
        this.id = id;
        this.nip = nip;
        this.pass = pass;
        this.nama_karyawan = nama_karyawan;
        this.divisi = divisi;
        this.unit = unit;
        this.lokasi = lokasi;
        this.jabatan = jabatan;
        this.hp = hp;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getnip() {
        return nip;
    }

    public void setnip(String nip) {
        this.nip = nip;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNama_karyawan() {
        return nama_karyawan;
    }

    public void setNama_karyawan(String nama_karyawan) {
        this.nama_karyawan = nama_karyawan;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
