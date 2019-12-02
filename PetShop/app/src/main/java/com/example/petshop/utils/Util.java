package com.example.petshop.utils;

import java.text.DecimalFormat;

public class Util {

    // Formata o valor do Preco
    public static String formataValor(Float x){
        DecimalFormat format = new DecimalFormat("R$: 0.00");
        return format.format(x);
    }

}
