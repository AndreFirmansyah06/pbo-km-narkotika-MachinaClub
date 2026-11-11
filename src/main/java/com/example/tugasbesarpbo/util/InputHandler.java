package com.example.tugasbesarpbo.util;

import java.sql.Date;
import java.time.LocalDate;

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

//    public static int validasiDate(String value, String namaField) {
//        try {
//            return LocalDate;
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException(namaField + " harus berupa angka bulat yang valid!");
//        }
//    }


    public static double validasiDouble(String value, String namaField) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(namaField + " harus berupa angka desimal yang valid!");
        }
    }

    public static double validasiLong(String value, String namaField) {
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(namaField + " harus berupa angka long yang valid!");
        }
    }

}
