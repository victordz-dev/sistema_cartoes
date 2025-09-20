package br.com.fiap.bo;

import java.util.*;

public class BeneficiosCartaoBO {
    private static final Map<String, List<String>> beneficios = new HashMap<>();

    static {
        beneficios.put("VISA", Arrays.asList("Seguro viagem", "Garantia estendida"));
        beneficios.put("MASTERCARD", Arrays.asList("Sala VIP", "Proteção de compras"));
    }

    public static List<String> getBeneficios(String bandeira) {
        return beneficios.getOrDefault(bandeira, List.of("Nenhum benefício"));
    }
}

