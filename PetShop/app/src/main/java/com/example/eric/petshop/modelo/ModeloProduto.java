package com.example.eric.petshop.modelo;

public class ModeloProduto {

    //O nome da variável deve ser o mesmo do nome da variável no link que deseja puxar o valor
    private float descontoPromocao;
    private int idCategoria;
    private int qtdMinEstoque;
    private String nomeProduto;
    private int idProduto;
    private String descProduto;
    private boolean ativoProduto;
    private float precProduto;

    public float getDescontoPromocao() {
        return descontoPromocao;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getQtdMinEstoque() {
        return qtdMinEstoque;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public boolean isAtivoProduto() {
        return ativoProduto;
    }

    public float getPrecProduto() {
        return precProduto;
    }
}
