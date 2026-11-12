package com.example.tugasbesarpbo.util;




import java.time.LocalDateTime;

public class AuditLog {
    private final LocalDateTime waktu = LocalDateTime.now();
    private final String aksi, detail;

    public AuditLog(String aksi, String detail) { this.aksi = aksi; this.detail = detail; }

    public LocalDateTime getWaktu() { return waktu; }
    public String getAksi() { return aksi; }
    public String getDetail() { return detail; }

    @Override
    public String toString() { return waktu.withNano(0) + " [" + aksi + "] " + detail; }
}