// Pacote para suas classes de utilidades
package com.AnotherFall.Arasaka.Registry;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HwidGenerator {

    // Códigos únicos para cada fabricante
    private static final Map<CriadoraSOC, Long> CODIGOS_FABRICANTE = Stream.of(new Object[][]{
    	 { CriadoraSOC.ARASAKA, 8492333L }, 
         { CriadoraSOC.MILITECH, 7483921L },
         { CriadoraSOC.BIOTECH, 6813947L },
         { CriadoraSOC.HYDRA, 4999541L },
         { CriadoraSOC.KANGTOO, 3759023L }
    }).collect(Collectors.toMap(data -> (CriadoraSOC) data[0], data -> (Long) data[1]));

    /**
     * Gera um HWID único no formato XXXX-XXXX-XXXX-XXXX-VV, usando hexadecimal.
     * @param modeloSoc O nome do modelo do SOC.
     * @param criadoraSoc O enum do fabricante.
     * @return Uma String formatada representando o HWID hexadecimal.
     */
    public static String gerar(String modeloSoc, CriadoraSOC criadoraSoc) {

        // --- ETAPA 1: CÁLCULO DA SEMENTE NUMÉRICA ---
        // BigInteger para evitar estouro ao multiplicar números grandes.
        BigInteger pesoModelo = BigInteger.ZERO;
        if (modeloSoc != null && !modeloSoc.isEmpty()) {
            long pesoLong = 0;
            for (char c : modeloSoc.toUpperCase().toCharArray()) {
                pesoLong += c;
            }
            pesoModelo = BigInteger.valueOf(pesoLong);
        }

        BigInteger codigoFabricante = BigInteger.valueOf(CODIGOS_FABRICANTE.getOrDefault(criadoraSoc, 1L));
        BigInteger fatorTemporal = BigInteger.valueOf(System.nanoTime());

        // A nova fórmula usa multiplicação para misturar os valores
        BigInteger resultadoNumerico = pesoModelo.multiply(codigoFabricante).multiply(fatorTemporal);

        // --- ETAPA 2: CONVERSÃO PARA HEXADECIMAL E FORMATAÇÃO DA BASE ---
        String hexString = resultadoNumerico.toString(16).toUpperCase(); // Converte para hexadecimal
        
        // Pega os últimos 16 caracteres para a base, ou preenche com zeros se for menor
        String base16;
        if (hexString.length() >= 16) {
            base16 = hexString.substring(hexString.length() - 16);
        } else {
            base16 = String.format("%16s", hexString).replace(' ', '0');
        }

        // --- ETAPA 3: CÁLCULO DOS 2 VALIDATORS HEXADECIMAIS ---
        String v1 = calcularValidadorHex(base16);
        String v2 = calcularValidadorHex(base16 + v1);

        String hwidCompleto = base16 + v1 + v2;
        
        // --- ETAPA 4: FORMATAÇÃO FINAL ---
        return String.format("%s-%s-%s-%s-%s",
            hwidCompleto.substring(0, 4),
            hwidCompleto.substring(4, 8),
            hwidCompleto.substring(8, 12),
            hwidCompleto.substring(12, 16),
            hwidCompleto.substring(16, 18)
        );
    }

    /**
     * Calcula um único dígito validador hexadecimal para uma base hexadecimal.
     * @param baseHex A string hexadecimal para calcular o dígito.
     * @return Um caractere hexadecimal (0-F).
     */
    private static String calcularValidadorHex(String baseHex) {
        int soma = 0;
        for (int i = 0; i < baseHex.length(); i++) {
            // Converte o caractere hexadecimal para seu valor inteiro
            int valorDoChar = Integer.parseInt(String.valueOf(baseHex.charAt(i)), 16);
            soma += valorDoChar * (i + 1); // Um peso simples baseado na posição
        }
        int resultado = soma % 16; // O resultado será um número de 0 a 15
        
        // Converte o resultado de volta para um caractere hexadecimal
        return Integer.toHexString(resultado).toUpperCase();
    }
}
