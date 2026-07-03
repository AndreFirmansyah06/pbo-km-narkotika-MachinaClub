package com.example.tugasbesarpbo.controller;

import com.example.tugasbesarpbo.model.Putusan;
import com.example.tugasbesarpbo.model.StatistikPutusan;
import  com.example.tugasbesarpbo.util.InputHandler;
import  com.example.tugasbesarpbo.util.CSVFileHandler;
import com.example.tugasbesarpbo.model.KnowledgeRepository;

import java.io.File;
import java.util.ArrayList;

public class KnowledgeController {
    private final KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {

        this.repository = repository;
    }

    // GET ALL DATA
    public ArrayList<Putusan> getSemuaPutusan() {

        return repository.getDaftarSemua();
    }

    // GET TOTAL DATA
    public int getTotalDataPutusan(){

        return  repository.getTotalData();
    }

    // INSERT DATA
    public void tambahPutusan(
            String nomorPerkaraStr,
            String pengadilanStr,
            String tanggalStr,
            String namaTerdakwaStr,
            String umurStr,
            String jenisNarkotikaStr,
            String beratStr,
            String pasalStr,
            String peranStr,
            String vonisHukumanStr,
            String vonisDendaStr,
            String hakimStr
    ) {

        // 1. Validasi & parsing field String wajib (lewat InputHandler)
        String nomorPerkara   = InputHandler.validasiString(nomorPerkaraStr, "Nomor Perkara");
        String pengadilan     = InputHandler.validasiString(pengadilanStr, "Pengadilan");
        String tanggal        = InputHandler.validasiString(tanggalStr, "Tanggal");
        String namaTerdakwa   = InputHandler.validasiString(namaTerdakwaStr, "Nama Terdakwa");
        String jenisNarkotika = InputHandler.validasiString(jenisNarkotikaStr, "Jenis Narkotika");
        String pasal          = InputHandler.validasiString(pasalStr, "Pasal");
        String peran          = InputHandler.validasiString(peranStr, "Peran");
        String hakim          = InputHandler.validasiString(hakimStr, "Hakim");

        // Validasi nomor perkara tidak boleh duplikat
        if (repository.cariByNomor(nomorPerkara) != null) {
            throw new IllegalArgumentException(
                    "Nomor perkara \"" + nomorPerkara + "\" sudah terdaftar.");
        }


        // 2. Validasi & parsing field numerik (lewat InputHandler)
        int umur            = InputHandler.validasiInt(umurStr, "Umur Terdakwa");
        double berat         = InputHandler.validasiDouble(beratStr, "Berat Barang Bukti");
        int vonisHukuman     = InputHandler.validasiInt(vonisHukumanStr, "Vonis Hukuman");
        double vonisDenda     = InputHandler.validasiDouble(vonisDendaStr, "Vonis Denda");

        Putusan pts = new Putusan(
                nomorPerkara, pengadilan, tanggal, namaTerdakwa, umur,
                jenisNarkotika, berat, pasal, peran, vonisHukuman, vonisDenda, hakim
        );

        repository.simpan(pts);
    }

    // UPDATE DATA
    public boolean updatePutusan(String nomor,
                                 String nomorPerkaraStr,
                                 String pengadilanStr,
                                 String tanggalStr,
                                 String namaTerdakwaStr,
                                 String umurStr,
                                 String jenisNarkotikaStr,
                                 String beratStr,
                                 String pasalStr,
                                 String peranStr,
                                 String vonisHukumanStr,
                                 String vonisDendaStr,
                                 String hakimStr
    ) {

        // 1. Validasi & parsing field String wajib (lewat InputHandler)
        String nomorPerkara   = InputHandler.validasiString(nomorPerkaraStr, "Nomor Perkara");
        String pengadilan     = InputHandler.validasiString(pengadilanStr, "Pengadilan");
        String tanggal        = InputHandler.validasiString(tanggalStr, "Tanggal");
        String namaTerdakwa   = InputHandler.validasiString(namaTerdakwaStr, "Nama Terdakwa");
        String jenisNarkotika = InputHandler.validasiString(jenisNarkotikaStr, "Jenis Narkotika");
        String pasal          = InputHandler.validasiString(pasalStr, "Pasal");
        String peran          = InputHandler.validasiString(peranStr, "Peran");
        String hakim          = InputHandler.validasiString(hakimStr, "Hakim");

        // 2. Validasi & parsing field numerik (lewat InputHandler)
        int umur            = InputHandler.validasiInt(umurStr, "Umur Terdakwa");
        double berat         = InputHandler.validasiDouble(beratStr, "Berat Barang Bukti");
        int vonisHukuman     = InputHandler.validasiInt(vonisHukumanStr, "Vonis Hukuman");
        double vonisDenda     = InputHandler.validasiDouble(vonisDendaStr, "Vonis Denda");

        Putusan dataBaru = new Putusan(
                nomorPerkara, pengadilan, tanggal, namaTerdakwa, umur,
                jenisNarkotika, berat, pasal, peran, vonisHukuman, vonisDenda, hakim
        );

        repository.update(nomor, dataBaru);
        return true;
    }

    // DELETE DATA
    public boolean hapusPutusan(String nomor) {

        return repository.hapus(nomor);
    }

    // SEARCH DATA
    public ArrayList<Putusan> cariPutusan(String keyword, String mode) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        if (keyword == null || keyword.isBlank() || mode == null) {
            return hasil;
        }
        switch (mode.toLowerCase()) {
            case "nomor perkara":
                Putusan p = repository.cariByNomor(keyword);
                if (p != null) hasil.add(p);
                break;
            case "nama":
                hasil = repository.cariByNama(keyword);
                break;
            default:
                // mode tidak dikenali -> hasil kosong
                break;
        }
        return hasil;
    }

    // FILTER DATA
    public ArrayList<Putusan> filterPutusan(String kriteria, String nilai) {
        if (kriteria == null || nilai == null) {
            return new ArrayList<>();
        }
        switch (kriteria.toLowerCase()) {
            case "jenis":
                return repository.filterByJenis(nilai);
            case "pengadilan":
                return repository.filterByPengadilan(nilai);
            default:
                return new ArrayList<>();
        }
    }

    // GET DATA STATISTIK
    public StatistikPutusan getStatistik() {

        return new StatistikPutusan(repository.getDaftarSemua());
    }

    // METHOD UNTUK GET DATA DARI FILE .csv
    public void importData(File file){
        try{
            ArrayList<Putusan> data = CSVFileHandler.bacaFile(file);

            for (Putusan p : data) {
                repository.simpan(p);
            }
        } catch (Exception e) {

            System.out.println(e);
        }

    }

    // METHOD UNTUK FILTER RENTANG VONIS
    public ArrayList<Putusan> filterByRentangVonis(String vonisMinStr, String vonisMaxStr) {
        int vonisMin = InputHandler.validasiInt(vonisMinStr, "Vonis Minimal");
        int vonisMax = InputHandler.validasiInt(vonisMaxStr, "Vonis Maksimal");

        if (vonisMin > vonisMax) {
            throw new IllegalArgumentException(
                    "Vonis Minimal tidak boleh lebih besar dari Vonis Maksimal.");
        }

        return repository.filterByVonisRange(vonisMin, vonisMax);
    }

}
