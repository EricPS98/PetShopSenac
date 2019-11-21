package com.example.eric.petshop.api;

import com.example.eric.petshop.modelo.ModeloProduto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiProduto {

    @GET("/android/rest/produto")
    Call<List<ModeloProduto>> listarProdutos();

}
