
package com.example.tugasbesarpbo.util;

import com.example.tugasbesarpbo.model.Putusan;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;

public class CSVFileHandler {

    public static DynamicArray<Putusan> bacaFile(File file) {
        DynamicArray<Putusan> daftar = new DynamicArray<>();

        CSVFormat format = CSVFormat.RFC4180.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        try (Reader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, format)) {

            for (CSVRecord record : csvParser) {

                // ini baru - Ganti break menjadi continue agar tidak menghentikan baris lainnya
                if (record.size() != 16) {
                    System.err.println("[SKIP CSV] Baris ke-" + record.getRecordNumber() + " dilewati karena jumlah kolom (" + record.size() + ") != 16");
                    continue; // ini baru
                }

                // ini baru - try-catch di dalam loop agar 1 baris error tidak menggagalkan seluruh file
                try {
                    String caseId = record.get(0);
                    String nomorPerkara = record.get(1);
                    String pengadilan = record.get(2);
                    String tanggalStr = record.get(3);
                    LocalDate tanggal = LocalDate.parse(tanggalStr);

                    String kodeTerdakwa = record.get(4);
                    String jenisNarkotika = record.get(5);

                    String beratStr = record.get(6).replace(",", ".");
                    double beratBarangBuktiGram = Double.parseDouble(beratStr);

                    String pasalDakwaan = record.get(7);
                    String pasalTerbukti = record.get(8);
                    int tuntutanBulan = Integer.parseInt(record.get(9));
                    int vonisBulan = Integer.parseInt(record.get(10));

                    String dendaStr = record.get(11).replace(".", "");
                    long dendaRupiah = Long.parseLong(dendaStr);

                    String barangBukti = record.get(12);
                    String keadaanMemberatkan = record.get(13);
                    String keadaanMeringankan = record.get(14);
                    String sourcePages = record.get(15);

                    Putusan p = new Putusan(
                            caseId, nomorPerkara, pengadilan, tanggal, kodeTerdakwa, tuntutanBulan, vonisBulan, dendaRupiah, barangBukti,
                            jenisNarkotika, beratBarangBuktiGram, pasalTerbukti, pasalDakwaan, keadaanMemberatkan, keadaanMeringankan, sourcePages
                    );

                    daftar.add(p);
                } catch (Exception e) { // ini baru
                    System.err.println("[SKIP CSV] Gagal parse baris ke-" + record.getRecordNumber() + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Gagal membaca file CSV: " + e.getMessage());
        }

        System.out.println("[DEBUG CSV] Total baris CSV berhasil di-parse: " + daftar.size());
        return daftar;
    }

    public static void eksporFile(File file, DynamicArray<Putusan> data) throws Exception {
        CSVFormat format = CSVFormat.RFC4180.builder()
                .setHeader(
                        "case_id", "nomor_perkara", "pengadilan", "tanggal_putusan",
                        "kode_terdakwa", "jenis_narkotika", "berat_barang_bukti",
                        "pasal_dakwaan", "pasal_terbukti", "tuntutan_bulan",
                        "vonis_bulan", "denda_rupiah", "barang_bukti",
                        "keadaan_memberatkan", "keadaan_meringankan", "source_pages"
                )
                .build();

        try (Writer writer = new FileWriter(file);
             CSVPrinter csvPrinter = new CSVPrinter(writer, format)) {

            for (int i = 0; i < data.size(); i++) {
                Putusan p = data.get(i);
                csvPrinter.printRecord(
                        p.getCaseId(),
                        p.getNomorPerkara(),
                        p.getPengadilan(),
                        p.getTanggalPutusan() != null ? p.getTanggalPutusan().toString() : "",
                        p.getterdakawaKode(),
                        p.getJenisNarkotika(),
                        p.getBeratBarangBuktiGram(),
                        p.getpasalDakwaan(),
                        p.getpasalTerbukti(),
                        p.gettuntutanBulan(),
                        p.getVonisBulan(),
                        p.getdendaRupiah(),
                        p.getBarangBukti(),
                        p.getkeadaanMemberatkan(),
                        p.getkeadaanMeringankan(),
                        p.getSourcePages()
                );
            }
            csvPrinter.flush();
        }
    }
}