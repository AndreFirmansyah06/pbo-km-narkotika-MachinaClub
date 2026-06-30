package com.example.tugasbesarpbo.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Putusan implements Displayable {

    private SimpleStringProperty nomorPerkara;
    private SimpleStringProperty pengadilan;
    private SimpleStringProperty tanggalPutusan;
    private SimpleStringProperty namaTerdakwa;
    private SimpleIntegerProperty umurTerdakwa;
    private SimpleStringProperty jenisNarkotika;
    private SimpleDoubleProperty beratBarangBukti;
    private SimpleStringProperty pasalDilanggar;
    private SimpleStringProperty peranTerdakwa;
    private SimpleIntegerProperty vonisHukuman;
    private SimpleDoubleProperty vonisDenda;
    private SimpleStringProperty namaHakim;

    private static int jumlahDibuat = 0;

    // ==================== CONSTRUCTOR ====================

    public Putusan() {
        this.nomorPerkara = new SimpleStringProperty("");
        this.pengadilan = new SimpleStringProperty("");
        this.tanggalPutusan = new SimpleStringProperty("");
        this.namaTerdakwa = new SimpleStringProperty("");
        this.umurTerdakwa = new SimpleIntegerProperty(0);
        this.jenisNarkotika = new SimpleStringProperty("");
        this.beratBarangBukti = new SimpleDoubleProperty(0);
        this.pasalDilanggar = new SimpleStringProperty("");
        this.peranTerdakwa = new SimpleStringProperty("");
        this.vonisHukuman = new SimpleIntegerProperty(0);
        this.vonisDenda = new SimpleDoubleProperty(0);
        this.namaHakim = new SimpleStringProperty("");
        jumlahDibuat++;
    }

    public Putusan(String nomorPerkara, String pengadilan, String tanggalPutusan,
                   String namaTerdakwa, int umurTerdakwa, String jenisNarkotika,
                   double beratBarangBukti, String pasalDilanggar, String peranTerdakwa,
                   int vonisHukuman, double vonisDenda, String namaHakim) {
        this.nomorPerkara = new SimpleStringProperty(nomorPerkara);
        this.pengadilan = new SimpleStringProperty(pengadilan);
        this.tanggalPutusan = new SimpleStringProperty(tanggalPutusan);
        this.namaTerdakwa = new SimpleStringProperty(namaTerdakwa);
        this.umurTerdakwa = new SimpleIntegerProperty(umurTerdakwa);
        this.jenisNarkotika = new SimpleStringProperty(jenisNarkotika);
        this.beratBarangBukti = new SimpleDoubleProperty(beratBarangBukti);
        this.pasalDilanggar = new SimpleStringProperty(pasalDilanggar);
        this.peranTerdakwa = new SimpleStringProperty(peranTerdakwa);
        this.vonisHukuman = new SimpleIntegerProperty(vonisHukuman);
        this.vonisDenda = new SimpleDoubleProperty(vonisDenda);
        this.namaHakim = new SimpleStringProperty(namaHakim);
        jumlahDibuat++;
    }

    // ==================== STATIC METHOD ====================

    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }

    // ==================== DISPLAYABLE (overloading) ====================

    @Override
    public void tampilkan() {
        System.out.println(nomorPerkara.get() + " - " + namaTerdakwa.get() + " (" + jenisNarkotika.get() + ")");
    }

    @Override
    public void tampilkan(boolean detail) {
        if (detail) {
            System.out.println("==============================");
            System.out.println("Nomor Perkara  : " + nomorPerkara.get());
            System.out.println("Pengadilan     : " + pengadilan.get());
            System.out.println("Tanggal Putusan: " + tanggalPutusan.get());
            System.out.println("Nama Terdakwa  : " + namaTerdakwa.get());
            System.out.println("Umur           : " + umurTerdakwa.get() + " tahun");
            System.out.println("Jenis Narkotika: " + jenisNarkotika.get());
            System.out.println("Berat BB       : " + beratBarangBukti.get() + " gram");
            System.out.println("Pasal Dilanggar: " + pasalDilanggar.get());
            System.out.println("Peran Terdakwa : " + peranTerdakwa.get());
            System.out.println("Vonis Hukuman  : " + vonisHukuman.get() + " bulan");
            System.out.println("Vonis Denda    : Rp" + String.format("%.0f", vonisDenda.get()));
            System.out.println("Nama Hakim     : " + namaHakim.get());
            System.out.println("Kategori       : " + getKategoriHukuman());
            System.out.println("==============================");
        } else {
            tampilkan();
        }
    }

    // ==================== STATIC METHOD ====================

    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }

