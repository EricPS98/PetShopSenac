package com.example.petshop.retrofit.api;

import com.example.petshop.retrofit.modelo.ModeloProduto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiProduto {

    @GET("/android/rest/produto")
    Call<List<ModeloProduto>> listarProdutos();

}
