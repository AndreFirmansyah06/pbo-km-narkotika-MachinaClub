package com.example.tugasbesarpbo.controller;

import  com.example.tugasbesarpbo.util.InputHandler;
import com.example.tugasbesarpbo.model.KnowledgeRepository;
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
}
