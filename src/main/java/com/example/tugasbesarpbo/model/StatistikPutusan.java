package com.example.tugasbesarpbo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatistikPutusan {

    private int totalPutusan;
    private double rataRataVonis;
    private double rataRataDenda;
    private String jenisNarkotikaTerbanyak;
    private String[] distribusiPeran;

    private ArrayList<Putusan> daftar;

    public StatistikPutusan(ArrayList<Putusan> daftar) {
        this.daftar = daftar;
        hitungSemua();
    }

    public void hitungSemua() {
        if (daftar == null || daftar.isEmpty()) {
            totalPutusan = 0;
            rataRataVonis = 0;
            rataRataDenda = 0;
            jenisNarkotikaTerbanyak = "Tidak ada data";
            distribusiPeran = new String[]{"Tidak ada data"};
            return;
        }

        totalPutusan = daftar.size();

        int totalVonis = 0;
        for (Putusan p : daftar) totalVonis += p.getVonisHukuman();
        rataRataVonis = (double) totalVonis / totalPutusan;

        double totalDenda = 0;
        for (Putusan p : daftar) totalDenda += p.getVonisDenda();
        rataRataDenda = totalDenda / totalPutusan;

        HashMap<String, Integer> hitungJenis = new HashMap<>();
        for (Putusan p : daftar) {
            String jenis = p.getJenisNarkotika();
            hitungJenis.put(jenis, hitungJenis.getOrDefault(jenis, 0) + 1);
        }

        int maxCount = 0;
        jenisNarkotikaTerbanyak = "-";
        for (Map.Entry<String, Integer> entry : hitungJenis.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                jenisNarkotikaTerbanyak = entry.getKey();
            }
        }

        HashMap<String, Integer> hitungPeran = new HashMap<>();
        for (Putusan p : daftar) {
            String peran = p.getPeranTerdakwa();
            hitungPeran.put(peran, hitungPeran.getOrDefault(peran, 0) + 1);
        }
        distribusiPeran = new String[hitungPeran.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : hitungPeran.entrySet()) {
            distribusiPeran[i++] = entry.getKey() + ": " + entry.getValue() + " kasus";
        }
    }


    public void tampilkanLaporan() {
        System.out.println("========================================");
        System.out.println("       LAPORAN STATISTIK PUTUSAN        ");
        System.out.println("========================================");
        System.out.println("Total Putusan             : " + totalPutusan);
        System.out.printf( "Rata-rata Vonis           : %.1f bulan%n", rataRataVonis);
        System.out.printf( "Rata-rata Denda           : Rp%.0f%n", rataRataDenda);
        System.out.println("Jenis Narkotika Terbanyak : " + jenisNarkotikaTerbanyak);
        System.out.println("Distribusi Peran          :");
        for (String s : distribusiPeran) {
            System.out.println("  - " + s);
        }
        System.out.println("========================================");
    }

    public int getTotalPutusan() {

        return totalPutusan;
    }

    public double getRataRataVonis() {

        return rataRataVonis;
    }

    public double getRataRataDenda() {

        return rataRataDenda;
    }

    public String getJenisNarkotikaTerbanyak() {

        return jenisNarkotikaTerbanyak;
    }

    public String[] getDistribusiPeran() {

        return distribusiPeran;
    }
}
