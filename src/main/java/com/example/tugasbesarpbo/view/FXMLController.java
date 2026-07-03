package com.example.tugasbesarpbo.view;

import com.example.tugasbesarpbo.model.StatistikPutusan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.tugasbesarpbo.model.Putusan;
import com.example.tugasbesarpbo.controller.KnowledgeController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class FXMLController {
    // FORM INPUT
    @FXML
    private TextField txtNomorPerkara;
    @FXML private TextField txtPengadilan;
    @FXML private TextField txtTanggalPutusan;
    @FXML private TextField txtNamaTerdakwa;
    @FXML private TextField txtUmurTerdakwa;
    @FXML private ComboBox<String> cmbJenisNarkotika;
    @FXML private TextField txtBeratBarangBukti;
    @FXML private TextField txtPasalDilanggar;
    @FXML private ComboBox<String> cmbPeranTerdakwa;
    @FXML private TextField txtVonisHukuman;
    @FXML private TextField txtVonisDenda;
    @FXML private TextField txtNamaHakim;

    // TABEL
    @FXML private TableView<Putusan> tablePutusan;
    @FXML private TableColumn<Putusan, String> colNomor;
    @FXML private TableColumn<Putusan, String> colNama;
    @FXML private TableColumn<Putusan, String> colJenis;
    @FXML private TableColumn<Putusan, Double> colBerat;
    @FXML private TableColumn<Putusan, String> colPeran;
    @FXML private TableColumn<Putusan, Integer> colVonis;
    @FXML private TableColumn<Putusan, Double> colDenda;
    @FXML private TableColumn<Putusan, String> colHakim;

    // PENCARIAN & FILTER
    @FXML private TextField txtCari;
    @FXML private Button btnUploadFile;
    @FXML private TextField txtVonisMin;
    @FXML private TextField txtVonisMax;
    @FXML private ComboBox<String> cmbFilterJenis;
    @FXML private ComboBox<String> cmbFilterPengadilan;
    @FXML private ComboBox<String> cbSearchMode;

    // STATISTIK
    @FXML private Label lblTotalPutusan;
    @FXML private Label lblRataRataVonis;
    @FXML private Label lblRataRataDenda;
    @FXML private Label lblJenisTerbanyak;
    @FXML private TextArea txtDistribusiPeran;

    // STATUS
    @FXML private Label lblStatus;
    @FXML private Label lblTotalData;

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
        colNomor.setCellValueFactory(cell ->
                cell.getValue().nomorPerkaraProperty());
        colNama.setCellValueFactory(cell ->
                cell.getValue().namaTerdakwaProperty());
        colJenis.setCellValueFactory(cell ->
                cell.getValue().jenisNarkotikaProperty());
        colBerat.setCellValueFactory(cell ->
                cell.getValue().beratBarangBuktiProperty().asObject());
        colPeran.setCellValueFactory(cell ->
                cell.getValue().peranTerdakwaProperty());
        colVonis.setCellValueFactory(cell ->
                cell.getValue().vonisHukumanProperty().asObject());
        colDenda.setCellValueFactory(cell ->
                cell.getValue().vonisDendaProperty().asObject());
        colHakim.setCellValueFactory(cell ->
                cell.getValue().namaHakimProperty());


        // EVENT UNTUK UPDATE (KLIK DATA DI TABEL DAN DATA AKAN MUNCUL DI FORM
        tablePutusan.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        txtNomorPerkara.setText(newVal.getNomorPerkara());
                        txtPengadilan.setText(newVal.getPengadilan());
                        txtTanggalPutusan.setText(newVal.getTanggalPutusan());
                        txtNamaTerdakwa.setText(newVal.getNamaTerdakwa());
                        txtUmurTerdakwa.setText(String.valueOf(newVal.getUmurTerdakwa()));
                        cmbJenisNarkotika.setValue(newVal.getJenisNarkotika());
                        txtBeratBarangBukti.setText(String.valueOf(newVal.getBeratBarangBukti()));
                        txtPasalDilanggar.setText(newVal.getPasalDilanggar());
                        cmbPeranTerdakwa.setValue(newVal.getPeranTerdakwa());
                        txtVonisHukuman.setText(String.valueOf(newVal.getVonisHukuman()));
                        txtVonisDenda.setText(String.valueOf(newVal.getVonisDenda()));
                        txtNamaHakim.setText(newVal.getNamaHakim());
                    }
                }
        );

        tablePutusan.setItems(dataList);
    }

    @FXML
    private void onTambahPutusan() {

        String jenisNarkotika = cmbJenisNarkotika.getValue();
        String peranTerdakwa = cmbPeranTerdakwa.getValue();
        try {
            controller.tambahPutusan(
                    txtNomorPerkara.getText(),
                    txtPengadilan.getText(),
                    txtTanggalPutusan.getText(),
                    txtNamaTerdakwa.getText(),
                    txtUmurTerdakwa.getText(),
                    jenisNarkotika,
                    txtBeratBarangBukti.getText(),
                    txtPasalDilanggar.getText(),
                    peranTerdakwa,
                    txtVonisHukuman.getText(),
                    txtVonisDenda.getText(),
                    txtNamaHakim.getText()
            );

            setStatus("");
            showAlert(Alert.AlertType.INFORMATION,"Tambah Data Putusan","Data Putusan Berhasil Ditambahkan");
            onClearForm();
            refreshTabel(controller.getSemuaPutusan());
            amountData();
            updateStatistik();

        } catch (IllegalArgumentException e) {
            setStatus("");
            showAlert(Alert.AlertType.WARNING,"Tambah Data Putusan","Pastikan kolom Umur, Berat, Vonis Hukuman, dan Denda berisi angka yang valid");
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
        String peranTerdakwa = cmbPeranTerdakwa.getValue();

        try {
            boolean berhasil = controller.updatePutusan(
                    nomor.trim(),
                    txtNomorPerkara.getText(),
                    txtPengadilan.getText(),
                    txtTanggalPutusan.getText(),
                    txtNamaTerdakwa.getText(),
                    txtUmurTerdakwa.getText(),
                    jenisNarkotika,
                    txtBeratBarangBukti.getText(),
                    txtPasalDilanggar.getText(),
                    peranTerdakwa,
                    txtVonisHukuman.getText(),
                    txtVonisDenda.getText(),
                    txtNamaHakim.getText()
            );
            if (berhasil) {
                showAlert(Alert.AlertType.INFORMATION,"Update Data Putusan","Data Putusan Berhasil Diupdate");
                setStatus("");
                onClearForm();
                refreshTabel(controller.getSemuaPutusan());
                amountData();
                updateStatistik();
            } else {
                setStatus("");
                showAlert(Alert.AlertType.WARNING,"Update Data Putusan","Data dengan nomor perkara "+nomor+" tersebut tidak ditemukan");
            }
        } catch (IllegalArgumentException e) {
            setStatus("");
            showAlert(Alert.AlertType.WARNING,"Update Data Putusan","Pastikan kolom Umur, Berat, Vonis Hukuman, dan Denda berisi angka yang valid");
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
            showAlert(Alert.AlertType.INFORMATION,"Hapus Data Putusan","Data putusan nomor " + nomor + " berhasil dihapus");
            onClearForm();
            refreshTabel(controller.getSemuaPutusan());
            amountData();
            updateStatistik();
        } else {
            setStatus("");
            showAlert(Alert.AlertType.WARNING,"Hapus Data Putusan","Data dengan nomor perkara tersebut tidak ditemukan");
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

    public void refreshTabel(ArrayList<Putusan> list) {
        dataList.setAll(list);
        tablePutusan.refresh();
    }



    private void amountData() {
        lblTotalData.setText("Total: " + controller.getTotalDataPutusan() + " data");
    }

    public void updateStatistik() {
        StatistikPutusan stat = controller.getStatistik();

        lblTotalPutusan.setText(
                "Total Putusan : " + stat.getTotalPutusan());

        lblRataRataVonis.setText(
                String.format("Rata-rata Vonis : %.0f bulan",
                        stat.getRataRataVonis()));

        lblRataRataDenda.setText(
                String.format("Rata-rata Denda : Rp %.2f",
                        stat.getRataRataDenda()));

        lblJenisTerbanyak.setText(
                "Jenis Terbanyak : " +
                        stat.getJenisNarkotikaTerbanyak());

        StringBuilder sb = new StringBuilder();

        for (String s : stat.getDistribusiPeran()) {
            sb.append("• ").append(s).append("\n");
        }

        txtDistribusiPeran.setText(sb.toString());
    }

    public void onClearForm() {
        txtNomorPerkara.clear();
        txtPengadilan.clear();
        txtTanggalPutusan.clear();
        txtNamaTerdakwa.clear();
        txtUmurTerdakwa.clear();
        cmbJenisNarkotika.setValue("Pilih jenis narkotika");
        cmbPeranTerdakwa.setValue("Pilih peran terdakwa");
        txtBeratBarangBukti.clear();
        txtPasalDilanggar.clear();
        txtVonisHukuman.clear();
        txtVonisDenda.clear();
        txtNamaHakim.clear();
    }

    @FXML
    private void onSearch() {
        String keyword = txtCari.getText();
        String mode = cbSearchMode.getValue();
        System.out.println(mode);
        ArrayList<Putusan> hasil = controller.cariPutusan(keyword, mode);
        refreshTabel(hasil);
        setStatus("Ditemukan " + hasil.size() + " data untuk pencarian \"" + keyword + "\".");
    }

    @FXML
    private void onFilter() {

        String jenis = cmbFilterJenis.getValue();
        String pengadilan = cmbFilterPengadilan.getValue();

        String kategori;
        String keyword;

        if (jenis != null) {
            kategori = "jenis";
            keyword = jenis;
        } else if (pengadilan != null) {
            kategori = "pengadilan";
            keyword = pengadilan;
        } else {
            setStatus("Pilih filter terlebih dahulu.");
            return;
        }

        ArrayList<Putusan> hasil = controller.filterPutusan(kategori, keyword);

        refreshTabel(hasil);
        setStatus("Ditemukan " + hasil.size() + " data.");
    }

    @FXML
    private void onFilterVonis() {
        try {
            ArrayList<Putusan> hasil = controller.filterByRentangVonis(
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
    private void onResetFilter() {
        txtCari.clear();
        txtVonisMin.clear();
        txtVonisMax.clear();
        cmbFilterJenis.setValue("Semua");
        cmbFilterPengadilan.setValue("Semua");
        cbSearchMode.setValue("Nama");
        refreshTabel(controller.getSemuaPutusan());
        setStatus("Menampilkan seluruh data putusan.");
    }


    @FXML
    private void onUploadFile() {
        FileChooser chooser = new FileChooser();
        Stage stage = (Stage) btnUploadFile.getScene().getWindow();

        File file = chooser.showOpenDialog(stage);

        controller.importData(file);

        refreshTabel(controller.getSemuaPutusan());
        amountData();
        updateStatistik();
    }

    @FXML
    private void onResetForm() {
        onClearForm();
        setStatus("Form telah direset.");
    }


}
