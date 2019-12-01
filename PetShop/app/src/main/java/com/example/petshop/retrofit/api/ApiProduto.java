package com.example.petshop.retrofit.api;

import com.example.petshop.retrofit.modelo.ModeloProduto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiProduto {

    @GET("/android/rest/produto")
    Call<List<ModeloProduto>> listarProdutos();

    @GET("/android/rest/produto/{id}")
    Call<ModeloProduto> getProduto(@Path("id") Integer id);

    @GET("/android/rest/produto/categoria/{id}")
    Call<List<ModeloProduto>> listarProdutoPorCategoria(@Path("id") Integer id);
}
