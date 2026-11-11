package com.example.tugasbesarpbo.model;

//import java.util.DynamicArray;

import java.util.HashMap;
import java.util.Map;

import com.example.tugasbesarpbo.util.DynamicArray;

public class StatistikPutusan {

    private int totalPerkara;
    private double rataRataVonis;
    private double grandTotalDenda;
    private String jenisNarkotikaTerbanyak;
    private String[] distribusiNarkoba;

    private DynamicArray<Putusan> daftar;


    public StatistikPutusan(DynamicArray<Putusan> daftar) {
        this.daftar = daftar;
        hitungSemua();
    }

    public void hitungSemua() {
        if (daftar == null || daftar.isEmpty()) {
            totalPerkara = 0;
            rataRataVonis = 0;
            grandTotalDenda = 0;
            jenisNarkotikaTerbanyak = "Tidak ada data";
            distribusiNarkoba = new String[]{"Tidak ada data"};
            return;
        }

        totalPerkara = daftar.size();

        int totalVonis = 0;
        for (int i = 0; i < daftar.size(); i++) {
            Putusan p = daftar.get(i);
            totalVonis += p.getVonisBulan();
        }
        rataRataVonis = (double) totalVonis / daftar.size();
//        for (Putusan p : daftar) totalVonis += p.getVonisHukuman();
//        rataRataVonis = (double) totalVonis / totalPutusan;

        double totalDenda = 0;
        for (int i = 0; i < daftar.size(); i++) {
            Putusan p = daftar.get(i);
             totalDenda += p.getdendaRupiah();

        }
        grandTotalDenda = totalDenda;
//        for (Putusan p : daftar) totalDenda += p.getVonisDenda();
//        rataRataDenda = totalDenda / totalPutusan;

        HashMap<String, Integer> hitungJenis = new HashMap<>();
        for (int i = 0; i < daftar.size(); i++) {
            Putusan p = daftar.get(i);
            String jenis = p.getJenisNarkotika();
            hitungJenis.put(jenis, hitungJenis.getOrDefault(jenis, 0) + 1);

        }
//            String jenis = p.getJenisNarkotika();
//            hitungJenis.put(jenis, hitungJenis.getOrDefault(jenis, 0) + 1);
//        }
//
//        int maxCount = 0;
//        jenisNarkotikaTerbanyak = "-";
//        for (Map.Entry<String, Integer> entry : hitungJenis.entrySet()) {
//            if (entry.getValue() > maxCount) {
//                maxCount = entry.getValue();
//                jenisNarkotikaTerbanyak = entry.getKey();
//            }
//        }

        HashMap<String, Integer> hitungNarkotika = new HashMap<>();
        for (int i = 0; i < daftar.size(); i++) {
            Putusan p = daftar.get(i);
            String peran = p.getJenisNarkotika();
            hitungNarkotika.put(peran, hitungNarkotika.getOrDefault(peran, 0) + 1);

        }
//        for (Putusan p : daftar) {
//            String peran = p.getPeranTerdakwa();
//            hitungPeran.put(peran, hitungPeran.getOrDefault(peran, 0) + 1);
//        }
        distribusiNarkoba = new String[hitungNarkotika.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : hitungNarkotika.entrySet()) {
            distribusiNarkoba[i++] = entry.getKey() + ": " + entry.getValue() + " kasus";
        }
    }


//    public void tampilkanLaporan() {
//        System.out.println("========================================");
//        System.out.println("       LAPORAN STATISTIK PUTUSAN        ");
//        System.out.println("========================================");
//        System.out.println("Total Putusan             : " + totalPutusan);
//        System.out.printf("Rata-rata Vonis           : %.1f bulan%n", rataRataVonis);
//        System.out.printf("Rata-rata Denda           : Rp%.0f%n", rataRataDenda);
//        System.out.println("Jenis Narkotika Terbanyak : " + jenisNarkotikaTerbanyak);
//        System.out.println("Distribusi Peran          :");
//        for (String s : distribusiPeran) {
//            System.out.println("  - " + s);
//        }
//        System.out.println("========================================");
//    }

    public int getTotalPerkara() {

        return totalPerkara;
    }

    public double getRataRataVonis() {

        return rataRataVonis;
    }

    public double getTotalDenda() {

        return grandTotalDenda;
    }

    public String getJenisNarkotikaTerbanyak() {

        return jenisNarkotikaTerbanyak;
    }

    public String[] getDistribusiPeran() {

        return distribusiNarkoba;
    }
}
