package com.example.tugasbesarpbo.controller;

import com.example.tugasbesarpbo.model.Putusan;
import com.example.tugasbesarpbo.model.StatistikPutusan;
import com.example.tugasbesarpbo.util.InputHandler;
import com.example.tugasbesarpbo.util.CSVFileHandler;
import com.example.tugasbesarpbo.model.KnowledgeRepository;
import com.example.tugasbesarpbo.util.DynamicArray;

import java.io.File;
import java.time.LocalDate;


public class KnowledgeController {
    private final KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {

        this.repository = repository;
    }

    // GET ALL DATA
    public DynamicArray<Putusan> getSemuaPutusan() {

        return repository.getDaftarSemua();
    }

    // GET TOTAL DATA
    public int getTotalDataPutusan() {

        return repository.getTotalData();
    }

    // INSERT DATA
    public void tambahPutusan(
            String caseIdStr,
            String nomorPerkaraStr,
            String pengadilanStr,
            LocalDate tanggalStr,
            String kodeTerdakwaStr,
            String beratBBDb,
            String jenisNarkotikaStr,
            String barangBuktiStr,
            String pasalDakwaanStr,
            String pasalTerbuktiStr,
            String vonisBulanStr,
            String tuntutanBulanStr,
            String dendaRupiahStr,
            String keadaanMeringankanStr,
            String keadaanMemberatkanStr,
            String sourcePageStr
    ) {

        // 1. Validasi & parsing field String wajib (lewat InputHandler)
        String caseId = InputHandler.validasiString(caseIdStr, "Case Id");
        String nomorPerkara = InputHandler.validasiString(nomorPerkaraStr, "Nomor Perkara");
        String pengadilan = InputHandler.validasiString(pengadilanStr, "Pengadilan");
        LocalDate tanggal = tanggalStr;
        String kodeTerdakwa = InputHandler.validasiString(kodeTerdakwaStr, "Kode Terdakwa");
        String jenisNarkotika = InputHandler.validasiString(jenisNarkotikaStr, "Jenis Narkotika");
        Double beratBarangBuktiGram = InputHandler.validasiDouble(beratBBDb, "Berat Barang Bukti");
        String pasalDakwaan = InputHandler.validasiString(pasalDakwaanStr, "Pasal Dakwaan");
        String pasalTerbukti = InputHandler.validasiString(pasalTerbuktiStr, "Pasal Terbukti");
        long dendaRupiah = (long) InputHandler.validasiLong(dendaRupiahStr, "Denda Rupiah");
        int vonisBulan = InputHandler.validasiInt(vonisBulanStr, "Vonis Bulan");
        int tuntutanBulan = InputHandler.validasiInt(tuntutanBulanStr, "Tuntutan Bulan");
        String keadaanMeringankan = InputHandler.validasiString(keadaanMeringankanStr, "Keadaan Meringankan");
        String keadaanMemberatkan = InputHandler.validasiString(keadaanMemberatkanStr, "Keadaan Memberatkan");
        String sourcePages = InputHandler.validasiString(sourcePageStr, "Source Pages");
        String barangBukti = InputHandler.validasiString(barangBuktiStr, "Barang Bukti");

        // Validasi nomor perkara tidak boleh duplikat
        if (repository.isNomorPerkaraExists(nomorPerkara)) {
            throw new IllegalArgumentException(
                    "Nomor perkara \"" + nomorPerkara + "\" sudah terdaftar.");
        }

        Putusan pts = new Putusan(
                caseId, nomorPerkara, pengadilan, tanggal, kodeTerdakwa, tuntutanBulan, vonisBulan, dendaRupiah, barangBukti
                , jenisNarkotika, beratBarangBuktiGram, pasalTerbukti, pasalDakwaan, keadaanMemberatkan, keadaanMeringankan, sourcePages
        );

        repository.simpan(pts);
    }

    // UPDATE DATA
    public boolean updatePutusan(String caseIdStr,
                                 String nomorPerkaraStr,
                                 String pengadilanStr,
                                 LocalDate tanggalStr,
                                 String kodeTerdakwaStr,
                                 String beratBBDb,
                                 String jenisNarkotikaStr,
                                 String barangBuktiStr,
                                 String pasalDakwaanStr,
                                 String pasalTerbuktiStr,
                                 String vonisBulanStr,
                                 String tuntutanBulanStr,
                                 String dendaRupiahStr,
                                 String keadaanMeringankanStr,
                                 String keadaanMemberatkanStr,
                                 String sourcePageStr
    ) {

        // 1. Validasi & parsing field String wajib (lewat InputHandler)
        String caseId = InputHandler.validasiString(caseIdStr, "Case Id");
        String nomorPerkara = InputHandler.validasiString(nomorPerkaraStr, "Nomor Perkara");
        String pengadilan = InputHandler.validasiString(pengadilanStr, "Pengadilan");
        LocalDate tanggal = tanggalStr;
        String kodeTerdakwa = InputHandler.validasiString(kodeTerdakwaStr, "Kode Terdakwa");
        String jenisNarkotika = InputHandler.validasiString(jenisNarkotikaStr, "Jenis Narkotika");
        Double beratBarangBuktiGram = InputHandler.validasiDouble(beratBBDb, "Berat Barang Bukti");
        String pasalDakwaan = InputHandler.validasiString(pasalDakwaanStr, "Pasal Dakwaan");
        String pasalTerbukti = InputHandler.validasiString(pasalTerbuktiStr, "Pasal Terbukti");
        long dendaRupiah = (long) InputHandler.validasiLong(dendaRupiahStr, "Denda Rupiah");
        int vonisBulan = InputHandler.validasiInt(vonisBulanStr, "Vonis Bulan");
        int tuntutanBulan = InputHandler.validasiInt(tuntutanBulanStr, "Tuntutan Bulan");
        String keadaanMeringankan = InputHandler.validasiString(keadaanMeringankanStr, "Keadaan Meringankan");
        String keadaanMemberatkan = InputHandler.validasiString(keadaanMemberatkanStr, "Keadaan Memberatkan");
        String sourcePages = InputHandler.validasiString(sourcePageStr, "Source Pages");
        String barangBukti = InputHandler.validasiString(barangBuktiStr, "Barang Bukti");

        Putusan dataBaru = new Putusan(
                caseId, nomorPerkara, pengadilan, tanggal, kodeTerdakwa, tuntutanBulan, vonisBulan, dendaRupiah, barangBukti
                , jenisNarkotika, beratBarangBuktiGram, pasalTerbukti, pasalDakwaan, keadaanMemberatkan, keadaanMeringankan, sourcePages
        );

        repository.update(nomorPerkara, dataBaru);
        return true;
    }

    // DELETE DATA
    public boolean hapusPutusan(String nomor) {

        return repository.hapus(nomor);
    }

    public DynamicArray<Putusan> cariPutusan(String keyword, String mode) {
        if (keyword == null || keyword.isBlank() || mode == null) {
            return new DynamicArray<>();
        }

        switch (mode.toLowerCase().trim()) {
            case "nomor perkara":
                return repository.cariByNomor(keyword); // ini baru

            case "case id":
                return repository.cariByCaseId(keyword); // ini baru - Langsung return DynamicArray

            default:
                return new DynamicArray<>();
        }
    }

    //     FILTER DATA
    public DynamicArray<Putusan> filterPutusan(String kriteria, String nilai) {
        if (kriteria == null || nilai == null) {
            return new DynamicArray<>();
        }
        switch (kriteria.toLowerCase()) {
            case "jenis":
                return repository.filterByJenis(nilai);
//            case "pengadilan":
//                return repository.filterByPengadilan(nilai);
            default:
                return new DynamicArray<>();
        }
    }

    // GET DATA STATISTIK
    public StatistikPutusan getStatistik() {

        return new StatistikPutusan(repository.getDaftarSemua());
    }

    public void importData(File file) {
        if (file == null) return;

        try {
            DynamicArray<Putusan> data = CSVFileHandler.bacaFile(file);

            if (data == null) return;

            // ini baru - Kunci ukuran batas awal sebelum looping berjalan
            int totalDataBaru = data.size();
            System.out.println("[DEBUG IMPORT] Memulai import " + totalDataBaru + " data...");

            for (int i = 0; i < totalDataBaru; i++) { // ini baru - Gunakan variabel totalDataBaru
                Putusan p = data.get(i);
                if (p != null) {
                    repository.simpan(p);
                }
            }

            System.out.println("[DEBUG IMPORT] Selesai mengimpor data.");
        } catch (Exception e) {
            System.err.println("Gagal import: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //    // METHOD UNTUK FILTER RENTANG VONIS
    public DynamicArray<Putusan> filterByRentangVonis(String vonisMinStr, String vonisMaxStr) {
        int vonisMin = InputHandler.validasiInt(vonisMinStr, "Vonis Minimal");
        int vonisMax = InputHandler.validasiInt(vonisMaxStr, "Vonis Maksimal");

        if (vonisMin > vonisMax) {
            throw new IllegalArgumentException(
                    "Vonis Minimal tidak boleh lebih besar dari Vonis Maksimal.");
        }

        return repository.filterByVonisRange(vonisMin, vonisMax);
    }

    //    // METHOD UNTUK FILTER Berat Barang Bukti
    public DynamicArray<Putusan> filterByRentangBB(String beratMinStr, String beratMaxStr) {
        double beratMin = InputHandler.validasiDouble(beratMinStr, "Berat Minimal");
        double beratMax = InputHandler.validasiDouble(beratMaxStr, "Berat Maksimal");

        if (beratMin > beratMax) {
            throw new IllegalArgumentException(
                    "Vonis Minimal tidak boleh lebih besar dari Vonis Maksimal.");
        }

        return repository.filterByBB(beratMin, beratMax);
    }

    // ini baru - Method untuk mengekspor data ke CSV lewat Controller
    public void eksporData(File file, DynamicArray<Putusan> data) throws Exception {
        if (file == null || data == null) return;
        CSVFileHandler.eksporFile(file, data);
    }

}
