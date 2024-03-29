package com.example.petshop.retrofit.modelo;

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

    public void setDescontoPromocao(float descontoPromocao) {
        this.descontoPromocao = descontoPromocao;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setQtdMinEstoque(int qtdMinEstoque) {
        this.qtdMinEstoque = qtdMinEstoque;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public void setAtivoProduto(boolean ativoProduto) {
        this.ativoProduto = ativoProduto;
    }

    public void setPrecProduto(float precProduto) {
        this.precProduto = precProduto;
    }
}



