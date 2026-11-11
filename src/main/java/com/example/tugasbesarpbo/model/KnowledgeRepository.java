package com.example.tugasbesarpbo.model;

import com.example.tugasbesarpbo.util.DynamicArray;

public class KnowledgeRepository {

    private DynamicArray<Putusan> daftarPutusan = new DynamicArray<>();

    public void simpan(Putusan p) {
        if (p != null) {
            daftarPutusan.add(p);
            System.out.println("[DEBUG REPO] Berhasil simpan 1 data. Size saat ini: " + daftarPutusan.size());
        } else {
            System.err.println("[DEBUG REPO] Objek Putusan yang akan disimpan bernilai NULL!");
        }
    }


    // 1. Method untuk FITUR PENCARIAN (Search Table) - Mengembalikan DynamicArray
    public DynamicArray<Putusan> cariByNomor(String nomor) { // ini baru
        DynamicArray<Putusan> hasil = new DynamicArray<>();

        if (nomor == null || nomor.isBlank()) { // ini baru
            return hasil;
        }
        System.out.println("DEBUG REPOSITORY -> Size daftarPutusan: " + daftarPutusan.size());
        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            // Menggunakan contains agar fleksibel (user tidak perlu mengetik nomor perkara secara penuh)
            if (p.getNomorPerkara() != null &&
                    p.getNomorPerkara().toLowerCase().contains(nomor.toLowerCase().trim())) { // ini baru
                hasil.add(p);
                System.out.println("Data Ditemukan");
            } else {
                System.out.println("Data Tidak Ada");
            }
        }
        return hasil;
    }

    // 2. Method khusus VALIDASI TAMBAH DATA (Cek Duplikasi) - Mengembalikan boolean
    public boolean isNomorPerkaraExists(String nomor) { // ini baru
        if (nomor == null || nomor.isBlank()) { // ini baru
            return false;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            // Menggunakan equalsIgnoreCase untuk mencocokkan nomor perkara secara PERSIS
            if (p.getNomorPerkara() != null &&
                    p.getNomorPerkara().equalsIgnoreCase(nomor.trim())) { // ini baru
                return true; // Langsung kembalikan true jika ditemukan yang sama
            }
        }
        return false; // Kembalikan false jika tidak ada yang sama
    }

    public DynamicArray<Putusan> cariByCaseId(String caseId) { // ini baru - Ubah return type ke DynamicArray
        DynamicArray<Putusan> hasil = new DynamicArray<>(); // ini baru

        if (caseId == null || caseId.isBlank()) { // ini baru
            return hasil;
        }

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            // PERBAIKAN: Gunakan getCaseId() (bukan getNomorPerkara()) dan gunakan contains
            if (p.getCaseId() != null &&
                    p.getCaseId().toLowerCase().contains(caseId.toLowerCase().trim())) { // ini baru
                hasil.add(p); // ini baru
            }
        }
        return hasil; // ini baru
    }

    public DynamicArray<Putusan> getDaftarSemua() {

        return this.daftarPutusan;
    }

    public int getTotalData() {

        return daftarPutusan.size();
    }

    public DynamicArray<Putusan> filterByJenis(String jenis) {

        if (jenis.equalsIgnoreCase("Semua")) {
            return new DynamicArray<>();
        }

        DynamicArray<Putusan> hasil = new DynamicArray<>();
        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            if (p.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

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

//    // Filter berdasarkan rentang vonis hukuman (dalam bulan), inklusif di kedua batas
    public DynamicArray<Putusan> filterByVonisRange(int vonisMin, int vonisMax) {
        DynamicArray<Putusan> hasil = new DynamicArray<>();

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            int vonis = p.getVonisBulan();
            if (vonis >= vonisMin && vonis <= vonisMax) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    //    // Filter berdasarkan rentang vonis hukuman (dalam bulan), inklusif di kedua batas
    public DynamicArray<Putusan> filterByBB(double beratMin, double beratMax) {
        DynamicArray<Putusan> hasil = new DynamicArray<>();

        for (int i = 0; i < daftarPutusan.size(); i++) {
            Putusan p = daftarPutusan.get(i);
            double vonis = p.getBeratBarangBuktiGram();
            if (vonis >= beratMin && vonis <= beratMax) {
                hasil.add(p);
            }
        }
        return hasil;
    }
}