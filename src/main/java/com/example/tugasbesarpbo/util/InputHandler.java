package com.example.tugasbesarpbo.util;

public class InputHandler {
    public static String validasiString(String value, String namaField) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(namaField + " wajib diisi!");
        }
        return value.trim();
    }
}
