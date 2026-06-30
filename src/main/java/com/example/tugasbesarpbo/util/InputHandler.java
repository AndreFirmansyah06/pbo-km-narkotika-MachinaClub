package com.example.tugasbesarpbo.util;

public class InputHandler {
    public static String validasiString(String value, String namaField) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(namaField + " wajib diisi!");
        }
        return value.trim();
    }

    public static int validasiInt(String value, String namaField) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(namaField + " harus berupa angka bulat yang valid!");
        }
    }

    public static double validasiDouble(String value, String namaField) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(namaField + " harus berupa angka desimal yang valid!");
        }
    }

}
