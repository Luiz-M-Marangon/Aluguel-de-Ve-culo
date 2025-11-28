package com.mycompany.locadora.util;

import java.util.regex.Pattern;

public class Validadores {
    // Regex para validações
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String TELEFONE_REGEX = "^(\\(\\d{2}\\)|\\d{2})\\s?\\d{4,5}-?\\d{4}$";
    private static final String PLACA_REGEX = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$";
    
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return Pattern.matches(EMAIL_REGEX, email);
    }
    
    public static boolean validarTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            return false;
        }
        // Remove espaços e caracteres especiais para validação
        String telefoneLimpo = telefone.replaceAll("[\\s()-]", "");
        return Pattern.matches(TELEFONE_REGEX, telefone);
    }
    
    public static boolean validarPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            return false;
        }
        placa = placa.toUpperCase().replaceAll("[^A-Z0-9]", "");
        return Pattern.matches(PLACA_REGEX, placa);
    }
    
    public static String formatarPlaca(String placa) {
        if (placa == null) return "";
        placa = placa.toUpperCase().replaceAll("[^A-Z0-9]", "");
        if (placa.length() == 7) {
            return placa.substring(0, 3) + "-" + placa.substring(3);
        }
        return placa;
    }
    
    public static boolean validarAno(String anoStr) {
        if (anoStr == null || anoStr.trim().isEmpty()) {
            return false;
        }
        try {
            int ano = Integer.parseInt(anoStr.trim());
            return ano >= 1900 && ano <= 2030;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean validarNumeroPositivo(String numeroStr) {
        if (numeroStr == null || numeroStr.trim().isEmpty()) {
            return false;
        }
        try {
            int numero = Integer.parseInt(numeroStr.trim());
            return numero >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}