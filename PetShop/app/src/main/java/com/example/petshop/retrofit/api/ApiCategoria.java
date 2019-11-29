package com.example.petshop.retrofit.api;

import com.example.petshop.retrofit.modelo.ModeloCategoria;
import com.example.petshop.retrofit.modelo.ModeloProduto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCategoria {

    @GET("/android/rest/categoria")
    Call<List<ModeloCategoria>> getCategoria();
}
