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
}
