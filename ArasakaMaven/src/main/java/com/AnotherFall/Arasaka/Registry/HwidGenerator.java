package com.AnotherFall.Arasaka.Registry;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HwidGenerator {
    private static final Map<CriadoraSOC, Long> CODIGOS_FABRICANTE = Stream.of(new Object[][] {
        { CriadoraSOC.ARASAKA, 8492333L }, 
        { CriadoraSOC.MILITECH, 7483921L },
        { CriadoraSOC.BIOTECH, 6813947L },
        { CriadoraSOC.HYDRA, 4999541L },
        { CriadoraSOC.KANGTOO, 3759023L }
    }).collect(Collectors.toMap(data -> (CriadoraSOC) data[0], data -> (Long) data[1]));

    /**
     * Gera um HWID único de 18 dígitos baseado no modelo e fabricante do SOC.
     * @param modeloSoc O nome do modelo do SOC (ex: "Noragami").
     * @param criadoraSoc O enum do fabricante (ex: CriadoraSOC.ARASAKA).
     * @return Uma String de 18 dígitos representando o HWID.
     */
    public static String gerar(String modeloSoc, CriadoraSOC criadoraSoc) {
        long pesoModelo = 0;
        if (modeloSoc != null) {
            for (char c : modeloSoc.toUpperCase().toCharArray()) {
                pesoModelo += c;
            }
        }

        long codigoFabricante = CODIGOS_FABRICANTE.getOrDefault(criadoraSoc, 1L);

        // 3. Usa o tempo em nanossegundos para garantir unicidade absoluta. Isso aqui foi ouro viu ?
        long fatorTemporal = System.nanoTime();

        // 4. A Fórmula: Combina os elementos para criar um número grande e complexo.
        // Misturar os números de forma eficaz.
        long resultadoNumerico = (pesoModelo * codigoFabricante) + fatorTemporal;

        // 5. Formata o resultado para ter exatamente 18 dígitos.
        // Pega o valor absoluto para evitar sinais negativos.
        // Usa os últimos 18 dígitos do resultado para garantir um tamanho fixo.
        String resultadoString = String.valueOf(Math.abs(resultadoNumerico));

        // Se a string for menor que 18, preenche com zeros à esquerda.
        if (resultadoString.length() < 18) {
            return String.format("%018d", Long.parseLong(resultadoString));
        } else {
            // Se for maior, pega apenas os últimos 18 dígitos.
            return resultadoString.substring(resultadoString.length() - 18);
        }
    }
}