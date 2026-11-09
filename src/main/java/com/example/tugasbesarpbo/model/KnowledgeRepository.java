package com.example.tugasbesarpbo.model;

//import java.util.DynamicArray;

import com.example.tugasbesarpbo.util.DynamicArray;


public class KnowledgeRepository {

    private DynamicArray<Putusan> daftarPutusan = new DynamicArray<>();

    public void simpan(Putusan p) {
        if (p != null) {
            daftarPutusan.add(p);
        }
    }

    public Putusan cariByNomor(String nomor) {

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i); // Ambil data secara manual pakai indeks
            if (p.getNomorPerkara().equalsIgnoreCase(nomor));
        }
        return null;
    }
    public DynamicArray<Putusan> cariByNama(String nama) {
        DynamicArray<Putusan> hasil = new DynamicArray<>();


        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i); // Ambil data secara manual pakai indeks
            if (p.getNamaTerdakwa().toLowerCase().contains(nama.toLowerCase()))
                hasil.add(p);
        }
        return hasil;
    }

    public DynamicArray<Putusan> getDaftarSemua() {

        return daftarPutusan;
    }

    public int getTotalData() {

        return daftarPutusan.size();
    }

//    public DynamicArray<Putusan> filterByJenis(String jenis) {
//
//        if (jenis.equalsIgnoreCase("Semua")) {
//            return new DynamicArray<>(daftarPutusan);
//        }
//
//        DynamicArray<Putusan> hasil = new DynamicArray<>();
//        for (Putusan p : daftarPutusan) {
//            if (p.getJenisNarkotika().equalsIgnoreCase(jenis)) {
//                hasil.add(p);
//            }
//        }
//        return hasil;
//    }
//
//    public DynamicArray<Putusan> filterByPengadilan(String pengadilan) {
//        if (pengadilan.equalsIgnoreCase("Semua")) {
//            return new DynamicArray<>(daftarPutusan);
//        }
//
//        DynamicArray<Putusan> hasil = new DynamicArray<>();
//        for (Putusan p : daftarPutusan) {
//            if (p.getPengadilan().toLowerCase().contains(pengadilan.toLowerCase())) {
//                hasil.add(p);
//            }
//        }
//        return hasil;
//    }
//
    public boolean update(String nomor, Putusan putusan) {
        for (int i = 0; i < daftarPutusan.size(); i++) {
            if (daftarPutusan.get(i).getNomorPerkara().equalsIgnoreCase(nomor)) {
                daftarPutusan.set(i, putusan);
                return true;
            }
        }
        return false;
    }
//
//    public boolean hapus(String nomor) {
//        Putusan target = cariByNomor(nomor);
//        if (target != null) return daftarPutusan.remove(target);
//        return false;
//    }

    public boolean hapus(String nomor) {
        int indexToRemove = -1;

        // 1. Cari indeks dari data berdasarkan nomor
        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            // Pastikan 'getNomor()' disesuaikan dengan method getter di kelas Putusan Anda
            if (p.getNomorPerkara().equals(nomor)) {
                indexToRemove = i;
                break;
            }
        }

        // 2. Jika data ditemukan (indeks valid), hapus menggunakan method remove(int position)
        if (indexToRemove != -1) {
            daftarPutusan.remove(indexToRemove);
            return true;
        }

        // 3. Jika tidak ditemukan
        return false;
    }
//
//    // Filter berdasarkan rentang vonis hukuman (dalam bulan), inklusif di kedua batas
//    public DynamicArray<Putusan> filterByVonisRange(int vonisMin, int vonisMax) {
//        DynamicArray<Putusan> hasil = new DynamicArray<>();
//        for (Putusan p : daftarPutusan) {
//            int vonis = p.getVonisHukuman();
//            if (vonis >= vonisMin && vonis <= vonisMax) {
//                hasil.add(p);
//            }
//        }
//        return hasil;
//    }
}