package com.example.tugasbesarpbo.view;

import com.example.tugasbesarpbo.model.StatistikPutusan;
import com.example.tugasbesarpbo.util.CSVFileHandler;
import com.example.tugasbesarpbo.util.DynamicArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.tugasbesarpbo.model.Putusan;
import com.example.tugasbesarpbo.controller.KnowledgeController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.Collection;
//import java.util.ArrayList;

public class FXMLController {
    // FORM INPUT
    @FXML
    private TextField txtCaseId;

    @FXML
    private TextField txtNomorPerkara;
    @FXML
    private TextField txtPengadilan;
    @FXML
    private DatePicker txtTanggalPutusan;
    @FXML
    private TextField txtKodeTerdakwa;

    @FXML
    private ComboBox<String> cmbJenisNarkotika;
    @FXML
    private TextField txtBeratBarangBukti;
    @FXML
    private TextField txtPasalDakwaan;
    @FXML
    private TextField txtPasalTerbukti;
    @FXML
    private TextField txtTuntutanBulan;
    @FXML
    private TextField txtVonisBulan;
    @FXML
    private TextField txtDendaRupiah;
    @FXML
    private TextField txtBarangBukti;
    @FXML
    private TextField txtKeadaanMemberatkan;
    @FXML
    private TextField txtKeadaanMeringankan;
    @FXML
    private TextField txtSourcePages;

    // TABEL
    @FXML
    private TableView<Putusan> tablePutusan;
    @FXML
    private TableColumn<Putusan, String> colCase;
    @FXML
    private TableColumn<Putusan, String> colNomorPerkara;
    @FXML
    private TableColumn<Putusan, String> colPengadilan;
    @FXML
    private TableColumn<Putusan, LocalDate> colTanggalPutusan;
    @FXML
    private TableColumn<Putusan, String> colKodeTerdakwa;
    @FXML
    private TableColumn<Putusan, String> colJenisNarkotika;
    @FXML
    private TableColumn<Putusan, Double> colBeratBarangBukti;
    @FXML
    private TableColumn<Putusan, String> colPasalDakwaan;
    @FXML
    private TableColumn<Putusan, String> colPasalTerbukti;
    @FXML
    private TableColumn<Putusan, Integer> colTuntutanBulan;
    @FXML
    private TableColumn<Putusan, Integer> colVonisBulan;
    @FXML
    private TableColumn<Putusan, Long> colDendaRupiah;
    @FXML
    private TableColumn<Putusan, String> colBarangBukti;
    @FXML
    private TableColumn<Putusan, String> colKeadaanMemberatkan;
    @FXML
    private TableColumn<Putusan, String> colKeadaanMeringankan;
    @FXML
    private TableColumn<Putusan, String> colSourcePages;

    // PENCARIAN & FILTER
    @FXML
    private TextField txtCari;
    @FXML
    private Button btnUploadFile;
    @FXML
    private Button btnExport;
    @FXML
    private TextField txtVonisMin;
    @FXML
    private TextField txtVonisMax;
    @FXML
    private TextField txtBeratMin;
    @FXML
    private TextField txtBeratMax;
    @FXML
    private ComboBox<String> cmbFilterJenis;
    @FXML
    private ComboBox<String> cbSearchMode;

    // STATISTIK
    @FXML
    private Label lblTotalPutusan;
    @FXML
    private Label lblRataRataVonis;
    @FXML
    private Label lblRataRataDenda;
    @FXML
    private TextArea txtDistribusiPeran;

    // STATUS
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblTotalData;

    private KnowledgeController controller;

    private ObservableList<Putusan> dataList = FXCollections.observableArrayList();

    public void setController(KnowledgeController controller) {
        this.controller = controller;
        refreshTabel(controller.getSemuaPutusan());
        amountData();
    }

    @FXML
    public void initialize() {
        // SETUP KOLOM
        colCase.setCellValueFactory(cell ->
                cell.getValue().caseIdProperty());
        colNomorPerkara.setCellValueFactory(cell ->
                cell.getValue().nomorPerkaraProperty());
        colPengadilan.setCellValueFactory(cell ->
                cell.getValue().pengadilanProperty());
        colTanggalPutusan.setCellValueFactory(cell ->
                cell.getValue().tanggalPutusanProperty());
        colKodeTerdakwa.setCellValueFactory(cell ->
                cell.getValue().terdakawaKodeProperty());
        colJenisNarkotika.setCellValueFactory(cell ->
                cell.getValue().jenisNarkotikaProperty());
        colBeratBarangBukti.setCellValueFactory(cell ->
                cell.getValue().beratBarangBuktiGramProperty().asObject());
        colPasalDakwaan.setCellValueFactory(cell ->
                cell.getValue().pasalDakwaanProperty());
        colPasalTerbukti.setCellValueFactory(cell ->
                cell.getValue().pasalTerbuktiProperty());
        colTuntutanBulan.setCellValueFactory(cell ->
                cell.getValue().tuntutanBulanProperty().asObject());
        colVonisBulan.setCellValueFactory(cell ->
                cell.getValue().vonisBulanProperty().asObject());
        colDendaRupiah.setCellValueFactory(cell ->
                cell.getValue().dendaRupiahProperty().asObject());
        colBarangBukti.setCellValueFactory(cell ->
                cell.getValue().BarangBuktiProperty());
        colKeadaanMemberatkan.setCellValueFactory(cell ->
                cell.getValue().keadaanMemberatkanProperty());
        colKeadaanMeringankan.setCellValueFactory(cell ->
                cell.getValue().keadaanMeringankanProperty());
        colSourcePages.setCellValueFactory(cell ->
                cell.getValue().sourcePagesProperty());


        // EVENT UNTUK UPDATE (KLIK DATA DI TABEL DAN DATA AKAN MUNCUL DI FORM
        tablePutusan.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        txtCaseId.setText(newVal.getCaseId());
                        txtNomorPerkara.setText(newVal.getNomorPerkara());
//                        txtPengadilan.setText(newVal.getPengadilan());
                        txtTanggalPutusan.setValue(newVal.getTanggalPutusan());
                        txtKodeTerdakwa.setText(newVal.getterdakawaKode());
                        txtPasalDakwaan.setText(String.valueOf(newVal.getpasalDakwaan()));
                        cmbJenisNarkotika.setValue(newVal.getJenisNarkotika());
                        txtBeratBarangBukti.setText(String.valueOf(newVal.getBeratBarangBuktiGram()));
                        txtPasalTerbukti.setText(newVal.getpasalTerbukti());
                        txtTuntutanBulan.setText(String.valueOf(newVal.gettuntutanBulan()));
                        txtVonisBulan.setText(String.valueOf(newVal.getVonisBulan()));
                        txtDendaRupiah.setText(String.valueOf(newVal.getdendaRupiah()));
                        txtBarangBukti.setText(String.valueOf(newVal.getBarangBukti()));
                        txtKeadaanMemberatkan.setText(newVal.getkeadaanMemberatkan());
                        txtKeadaanMeringankan.setText(newVal.getkeadaanMeringankan());
                        txtSourcePages.setText(newVal.getSourcePages());
                    }
                }
        );

        tablePutusan.setItems(dataList);
    }

    @FXML
    private void onTambahPutusan() {

        String jenisNarkotika = cmbJenisNarkotika.getValue();

        try {
            controller.tambahPutusan(
                    txtCaseId.getText(),
                    txtNomorPerkara.getText(),
                    txtPengadilan.getText(),
                    txtTanggalPutusan.getValue(),
                    txtKodeTerdakwa.getText(),
                    txtBeratBarangBukti.getText(),
                    jenisNarkotika,
                    txtBarangBukti.getText(),
                    txtPasalDakwaan.getText(),
                    txtPasalTerbukti.getText(),
                    txtTuntutanBulan.getText(),
                    txtDendaRupiah.getText(),
                    txtVonisBulan.getText(),
                    txtKeadaanMeringankan.getText(),
                    txtKeadaanMemberatkan.getText(),
                    txtSourcePages.getText()
            );

            setStatus("");
            showAlert(Alert.AlertType.INFORMATION, "Tambah Data Putusan", "Data Putusan Berhasil Ditambahkan");
            onClearForm();
            refreshTabel(controller.getSemuaPutusan());
            amountData();
            updateStatistik();

        } catch (Exception e) {
            setStatus("");
            showAlert(Alert.AlertType.WARNING, "Tambah Data Putusan", e.getMessage());
        }
    }

    @FXML
    private void onUpdatePutusan() {
        String nomor = txtNomorPerkara.getText();
        if (nomor == null || nomor.isBlank()) {
            setStatus("Nomor perkara tidak boleh kosong saat update.");
            return;
        }
        String jenisNarkotika = cmbJenisNarkotika.getValue();


        try {
            boolean berhasil = controller.updatePutusan(

                    txtCaseId.getText(),
                    nomor.trim(),
                    txtPengadilan.getText(),
                    txtTanggalPutusan.getValue(),
                    txtKodeTerdakwa.getText(),
                    txtBeratBarangBukti.getText(),
                    jenisNarkotika,
                    txtBarangBukti.getText(),
                    txtPasalDakwaan.getText(),
                    txtPasalTerbukti.getText(),
                    txtVonisBulan.getText(),
                    txtTuntutanBulan.getText(),
                    txtDendaRupiah.getText(),
                    txtKeadaanMeringankan.getText(),
                    txtKeadaanMemberatkan.getText(),
                    txtSourcePages.getText()
            );
            if (berhasil) {
                showAlert(Alert.AlertType.INFORMATION, "Update Data Putusan", "Data Putusan Berhasil Diupdate");
                setStatus("");
                onClearForm();
                refreshTabel(controller.getSemuaPutusan());
//                amountData();
//                updateStatistik();
            } else {
                setStatus("");
                showAlert(Alert.AlertType.WARNING, "Update Data Putusan", "Data dengan nomor perkara " + nomor + " tersebut tidak ditemukan");
            }
        } catch (IllegalArgumentException e) {
            setStatus(e.getMessage());
            showAlert(Alert.AlertType.WARNING, "Update Data Putusan", "Pastikan kolom Umur, Berat, Vonis Hukuman, dan Denda berisi angka yang valid");
        }
    }

    @FXML
    private void onHapusPutusan() {
        String nomor = txtNomorPerkara.getText();
        if (nomor == null || nomor.isBlank()) {
            setStatus("Pilih nomor perkara yang ingin dihapus.");
            return;
        }
        boolean berhasil = controller.hapusPutusan(nomor.trim());
        if (berhasil) {
            setStatus("");
            showAlert(Alert.AlertType.INFORMATION, "Hapus Data Putusan", "Data putusan nomor " + nomor + " berhasil dihapus");
            onClearForm();
            refreshTabel(controller.getSemuaPutusan());
            amountData();
            updateStatistik();
        } else {
            setStatus("");
            showAlert(Alert.AlertType.WARNING, "Hapus Data Putusan", "Data dengan nomor perkara tersebut tidak ditemukan");
        }
    }


    public void setStatus(String pesan) {
        lblStatus.setText(pesan);
    }

    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void refreshTabel(DynamicArray<Putusan> data) {
        ObservableList<Putusan> list = FXCollections.observableArrayList(); // ini baru

        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                list.add(data.get(i)); // ini baru - Masukkan elemen dari DynamicArray ke ObservableList
            }
        }

        tablePutusan.setItems(list); // ini baru - Ikat daftar terbaru ke TableView
        tablePutusan.refresh(); // ini baru - Paksa JavaFX menggambar ulang baris tabel
    }


    private void amountData() {
        lblTotalData.setText("Total: " + controller.getTotalDataPutusan() + " data");
    }

    public void updateStatistik() {
        StatistikPutusan stat = controller.getStatistik();

        lblTotalPutusan.setText(
                "Total Perkara : " + stat.getTotalPerkara());

        lblRataRataVonis.setText(
                String.format("Rata-rata Vonis : %.0f bulan",
                        stat.getRataRataVonis()));

        lblRataRataDenda.setText(
                String.format("Total Denda : Rp %.2f ",
                        stat.getTotalDenda()));

        StringBuilder sb = new StringBuilder();

        for (String s : stat.getDistribusiPeran()) {
            sb.append("• ").append(s).append("\n");
        }

        txtDistribusiPeran.setText(sb.toString());
    }

    public void onClearForm() {
        txtCaseId.clear();
        txtNomorPerkara.clear();
        txtTanggalPutusan.setValue(null);
        txtKodeTerdakwa.clear();
        txtBeratBarangBukti.clear();
        txtPasalDakwaan.clear();
        txtPasalTerbukti.clear();
        txtTuntutanBulan.clear();
        txtVonisBulan.clear();
        txtDendaRupiah.clear();
        txtBarangBukti.clear();
        txtKeadaanMemberatkan.clear();
        txtKeadaanMeringankan.clear();
        txtSourcePages.clear();
        cmbJenisNarkotika.setValue("Pilih Jenis Narkoba");
    }

    @FXML
    private void onSearch() {
        String keyword = txtCari.getText();
        String mode = cbSearchMode.getValue();

        // ini baru - Jika kotak pencarian kosong, tampilkan kembali seluruh data
        if (keyword == null || keyword.trim().isEmpty()) {
            DynamicArray<Putusan> semuaData = controller.getSemuaPutusan(); // atau repository.getDaftarPutusan() // ini baru
            refreshTabel(semuaData);
            setStatus("Menampilkan semua data putusan (" + semuaData.size() + " data)."); // ini baru
            return; // ini baru
        }

        System.out.println("Keyword : " + keyword + " | Mode: " + mode);
        DynamicArray<Putusan> hasil = controller.cariPutusan(keyword, mode);
        System.out.println("Ukuran Hasil" + hasil.size());
        refreshTabel(hasil);
        setStatus("Ditemukan " + hasil.size() + " data untuk pencarian \"" + keyword + "\".");
    }

    @FXML
    private void onFilter() {

        String jenis = cmbFilterJenis.getValue();

        String kategori;
        String keyword;

        if (jenis != null) {
            kategori = "jenis";
            keyword = jenis;
        }  else {
            setStatus("Pilih filter terlebih dahulu.");
            return;
        }

        DynamicArray<Putusan> hasil = controller.filterPutusan(kategori, keyword);

        refreshTabel(hasil);
        setStatus("Ditemukan " + hasil.size() + " data.");
    }

    @FXML
    private void onFilterVonis() {
        try {
            DynamicArray<Putusan> hasil = controller.filterByRentangVonis(
                    txtVonisMin.getText(),
                    txtVonisMax.getText()
            );
            refreshTabel(hasil);
            setStatus("Filter vonis diterapkan: " + hasil.size() + " data ditemukan.");
        } catch (IllegalArgumentException e) {
            setStatus(e.getMessage());
        }
    }

    @FXML
    private void onFilterBeratBarangBukti() {
        try {
            DynamicArray<Putusan> hasil = controller.filterByRentangBB(
                    txtBeratMin.getText(),
                    txtBeratMax.getText()
            );
            refreshTabel(hasil);
            setStatus("Rentang Berat Barang Bukti Yang Diterapkan: " + hasil.size() + " data ditemukan.");
        } catch (IllegalArgumentException e) {
            setStatus(e.getMessage());
        }
    }



    @FXML
    private void onResetFilter() {
        txtCari.clear();
        txtVonisMin.clear();
        txtVonisMax.clear();
        txtBeratMax.clear();
        txtBeratMin.clear();
        cmbFilterJenis.setValue("Semua");
        cbSearchMode.setValue("Pilih");
        refreshTabel(controller.getSemuaPutusan());
        setStatus("Menampilkan seluruh data putusan.");
    }

    @FXML
    private void onUploadFile() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

            Stage stage = (Stage) btnUploadFile.getScene().getWindow();
            File file = chooser.showOpenDialog(stage);

            if (file == null) return;

            setStatus("Memproses import file CSV...");

            new Thread(() -> {
                // 1. Panggil importData di background thread
                controller.importData(file);

                // 2. Refresh UI di Platform.runLater
                javafx.application.Platform.runLater(() -> {
                    DynamicArray<Putusan> semuaData = controller.getSemuaPutusan();
                    refreshTabel(semuaData);
                    amountData();
                    updateStatistik();
                    setStatus("Berhasil mengimpor data. Total data saat ini: " + semuaData.size());
                });
            }).start();

        } catch (Exception e) {
            setStatus("Gagal: " + e.getMessage());
        }
    }

    @FXML
    private void onResetForm() {
        onClearForm();
        setStatus("Form telah direset.");
    }

    @FXML
    private void onEksporCSV() {
        try {
            // Mengambil seluruh data di repository (atau data yang sedang tampil)
            DynamicArray<Putusan> dataToExport = controller.getSemuaPutusan();

            if (dataToExport == null || dataToExport.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Ekspor CSV", "Tidak ada data yang tersedia untuk diekspor!");
                return;
            }

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Simpan File CSV");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv"));
            chooser.setInitialFileName("daftar_putusan_narkotika.csv");

            Stage stage = (Stage) btnUploadFile.getScene().getWindow();
            File file = chooser.showSaveDialog(stage);

            if (file == null) {
                return; // Pengguna membatalkan dialog simpan
            }

            setStatus("Mengekspor data ke file CSV...");

            // Jalankan penulisan file di Background Thread
            new Thread(() -> {
                try {
                    controller.eksporData(file, dataToExport);

                    javafx.application.Platform.runLater(() -> {
                        setStatus("Berhasil mengekspor " + dataToExport.size() + " data ke file CSV.");
                        showAlert(Alert.AlertType.INFORMATION, "Ekspor CSV Berhasil",
                                "Data berhasil disimpan ke:\n" + file.getAbsolutePath());
                    });
                } catch (Exception e) {
                    javafx.application.Platform.runLater(() -> {
                        setStatus("Gagal mengekspor CSV: " + e.getMessage());
                        showAlert(Alert.AlertType.ERROR, "Ekspor CSV Gagal", e.getMessage());
                    });
                }
            }).start();

        } catch (Exception e) {
            setStatus("Gagal: " + e.getMessage());
        }
    }
}



