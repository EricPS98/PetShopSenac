package com.example.petshop.utils;

import com.example.petshop.retrofit.modelo.ModeloProduto;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private List<ModeloProduto> carrinho;
    private List<ModeloProduto> itensSearch;

    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {

    }

    public static  Singleton getInstance() {
        return INSTANCE;
    }

    public List<ModeloProduto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<ModeloProduto> carrinho) {
        this.carrinho = carrinho;
    }

    public void addCarrinho(ModeloProduto p) {
        if (carrinho == null ){
            carrinho = new ArrayList<ModeloProduto>();
        }
        this.carrinho.add(p);
    }

    public void removeAll() {
        this.carrinho.clear();
    }

}


