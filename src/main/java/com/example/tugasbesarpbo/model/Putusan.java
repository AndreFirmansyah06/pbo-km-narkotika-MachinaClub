package com.example.tugasbesarpbo.model;

import javafx.beans.property.*;
import javafx.util.converter.LocalDateStringConverter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Putusan {

    private SimpleStringProperty caseId;
    private SimpleStringProperty nomorPerkara;
    private SimpleStringProperty pengadilan;
    private ObjectProperty<LocalDate> tanggalPutusan;
    private SimpleStringProperty terdakawaKode;
    private SimpleStringProperty jenisNarkotika;
    private SimpleDoubleProperty beratBarangBuktiGram;
    private SimpleStringProperty pasalDakwaan;
    private SimpleStringProperty pasalTerbukti;
    private SimpleIntegerProperty tuntutanBulan;
    private SimpleIntegerProperty vonisBulan;
    private SimpleLongProperty dendaRupiah;
    private SimpleStringProperty barangBukti;
    private SimpleStringProperty keadaanMemberatkan;
    private SimpleStringProperty keadaanMeringankan;
    private SimpleStringProperty sourcePages;

    private static int jumlahDibuat = 0;

    // ==================== CONSTRUCTOR ====================

    public Putusan() {
        this.caseId = new SimpleStringProperty("");
        this.nomorPerkara = new SimpleStringProperty("");
        this.pengadilan = new SimpleStringProperty("");
        this.tanggalPutusan = new SimpleObjectProperty<>(LocalDate.now());
        this.terdakawaKode = new SimpleStringProperty("");
        this.jenisNarkotika = new SimpleStringProperty("");
        this.beratBarangBuktiGram = new SimpleDoubleProperty(0);
        this.pasalDakwaan = new SimpleStringProperty("");
        this.pasalTerbukti = new SimpleStringProperty("");
        this.tuntutanBulan = new SimpleIntegerProperty(0);
        this.vonisBulan = new SimpleIntegerProperty(0);
        this.dendaRupiah = new SimpleLongProperty(0);
        this.barangBukti = new SimpleStringProperty("");
        this.keadaanMemberatkan = new SimpleStringProperty("");
        this.keadaanMeringankan = new SimpleStringProperty("");
        this.sourcePages = new SimpleStringProperty("");
        jumlahDibuat++;
    }

    public Putusan(String caseId, String nomorPerkara, String pengadilan, LocalDate tanggalPutusan,
                   String terdakawaKode, int tuntutanBulan, int vonisBulan, long dendaRupiah, String barangBukti, String jenisNarkotika,
                   double beratBarangBukti, String pasalTerbukti, String pasalDakwaan,
                   String keadaanMemberatkan, String keadaanMeringankan, String sourcePages) {
        this.caseId = new SimpleStringProperty(caseId);
        this.nomorPerkara = new SimpleStringProperty(nomorPerkara);
        this.pengadilan = new SimpleStringProperty(pengadilan);
        this.tanggalPutusan = new SimpleObjectProperty<>(tanggalPutusan);
        this.terdakawaKode = new SimpleStringProperty(terdakawaKode);
        this.tuntutanBulan = new SimpleIntegerProperty(tuntutanBulan);
        this.jenisNarkotika = new SimpleStringProperty(jenisNarkotika);
        this.beratBarangBuktiGram = new SimpleDoubleProperty(beratBarangBukti);
        this.pasalTerbukti = new SimpleStringProperty(pasalTerbukti);
        this.pasalDakwaan = new SimpleStringProperty(pasalDakwaan);
        this.vonisBulan = new SimpleIntegerProperty(vonisBulan);
        this.dendaRupiah = new SimpleLongProperty(dendaRupiah);
        this.barangBukti = new SimpleStringProperty(barangBukti);
        this.keadaanMemberatkan = new SimpleStringProperty(keadaanMemberatkan);
        this.keadaanMeringankan = new SimpleStringProperty(keadaanMeringankan);
        this.sourcePages = new SimpleStringProperty(sourcePages);
        jumlahDibuat++;
    }

    // ==================== STATIC METHOD ====================

    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }


    @Override
    public String toString() {
        return String.format("Nomor: %s | Terdakwa: %s | Vonis: %d bulan | Denda: Rp%.0f",
                nomorPerkara.get(), terdakawaKode.get(), vonisBulan.get(), dendaRupiah.get());
    }

    // ==================== GETTERS, SETTERS & PROPERTY METHODS ====================

    // Field Case Id
    public String getCaseId() {
        return caseId.get();
    }

    public void setCaseId(String caseId) {
        this.nomorPerkara.set(caseId);
    }

    public SimpleStringProperty caseIdProperty() {
        return caseId;
    }


    // Field Nomor Perkara
    public String getNomorPerkara() {
        return nomorPerkara.get();
    }

    public void setNomorPerkara(String nomorPerkara) {
        this.nomorPerkara.set(nomorPerkara);
    }

    public SimpleStringProperty nomorPerkaraProperty() {
        return nomorPerkara;
    }


    // Field Pengadilan
    public String getPengadilan() {
        return pengadilan.get();
    }

    public void setPengadilan(String pengadilan) {
        this.pengadilan.set(pengadilan);
    }

    public SimpleStringProperty pengadilanProperty() {
        return pengadilan;
    }


    //Field Tanggal Putusan
    // Getter nilai biasa
    public LocalDate getTanggalPutusan() {
        return tanggalPutusan.get();
    }

    // Setter nilai biasa
    public void setTanggalPutusan(LocalDate tanggalPutusan) {
        this.tanggalPutusan.set(tanggalPutusan);
    }

    public ObjectProperty<LocalDate> tanggalPutusanProperty() {
        return tanggalPutusan;
    }

    //Field Berat Barang Bukti Gram
    public double getBeratBarangBuktiGram() {
        return beratBarangBuktiGram.get();
    }

    public void setBeratBarangBuktiGram(double beratBarangBuktiGram) {
        this.beratBarangBuktiGram.set(beratBarangBuktiGram);
    }

    public SimpleDoubleProperty beratBarangBuktiGramProperty() {
        return beratBarangBuktiGram;
    }

    //Field Terdakwa Kode
    public String getterdakawaKode() {
        return terdakawaKode.get();
    }

    public void setterdakawaKode(String terdakawaKode) {
        this.terdakawaKode.set(terdakawaKode);
    }

    public SimpleStringProperty terdakawaKodeProperty() {
        return terdakawaKode;
    }


    //Field Tuntutan Bulan
    public int gettuntutanBulan() {
        return tuntutanBulan.get();
    }

    public void settuntutanBulan(int tuntutanBulan) {
        this.tuntutanBulan.set(tuntutanBulan);
    }

    public SimpleIntegerProperty tuntutanBulanProperty() {
        return tuntutanBulan;
    }

    //Field Jenis Narkotika
    public String getJenisNarkotika() {
        return jenisNarkotika.get();
    }

    public void setJenisNarkotika(String jenisNarkotika) {
        this.jenisNarkotika.set(jenisNarkotika);
    }

    public SimpleStringProperty jenisNarkotikaProperty() {
        return jenisNarkotika;
    }


    //Field  Barang Bukti
    public String getBarangBukti() {
        return barangBukti.get();
    }

    public void setBarangBukti(String barangBukti) {
        this.barangBukti.set(barangBukti);
    }

    public SimpleStringProperty BarangBuktiProperty() {
        return barangBukti;
    }


    //Field Pasal Terbukti
    public String getpasalTerbukti() {
        return pasalTerbukti.get();
    }

    public void setpasalTerbukti(String pasalTerbukti) {
        this.pasalTerbukti.set(pasalTerbukti);
    }

    public SimpleStringProperty pasalTerbuktiProperty() {
        return pasalTerbukti;
    }


    //Field Pasal Dakwaan
    public String getpasalDakwaan() {
        return pasalDakwaan.get();
    }

    public void setpasalDakwaan(String pasalDakwaan) {
        this.pasalDakwaan.set(pasalDakwaan);
    }

    public SimpleStringProperty pasalDakwaanProperty() {
        return pasalDakwaan;
    }


    //Field Vonis Bulan
    public int getVonisBulan() {
        return vonisBulan.get();
    }

    public void setVonisBulan(int vonisBulan) {
        this.vonisBulan.set(vonisBulan);
    }

    public SimpleIntegerProperty vonisBulanProperty() {
        return vonisBulan;
    }


    //Field Denda Rupiah
    public long getdendaRupiah() {
        return dendaRupiah.get();
    }

    public void setdendaRupiah(long dendaRupiah) {
        this.dendaRupiah.set(dendaRupiah);
    }

    public SimpleLongProperty dendaRupiahProperty() {
        return dendaRupiah;
    }


    //Field Keadaan Memberatkan
    public String getkeadaanMemberatkan() {
        return keadaanMemberatkan.get();
    }

    public void setkeadaanMemberatkan(String keadaanMemberatkan) {
        this.keadaanMemberatkan.set(keadaanMemberatkan);
    }

    public SimpleStringProperty keadaanMemberatkanProperty() {
        return keadaanMemberatkan;
    }


    //Field Keadaan Meringankan
    public String getkeadaanMeringankan() {
        return keadaanMeringankan.get();
    }

    public void setkeadaanMMeringankan(String keadaanMeringankan) {
        this.keadaanMemberatkan.set(keadaanMeringankan);
    }

    public SimpleStringProperty keadaanMeringankanProperty() {
        return keadaanMeringankan;
    }

    //Field Source Pages
    public String getSourcePages() {
        return sourcePages.get();
    }

    public void setSourcePages(String sourcePages) {
        this.sourcePages.set(sourcePages);
    }

    public SimpleStringProperty sourcePagesProperty() {
        return sourcePages;
    }
}

