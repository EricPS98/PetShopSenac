package com.example.petshop.retrofit.api;

import com.example.petshop.retrofit.modelo.ModeloCliente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiCliente {

    @POST("/android/rest/cliente")
    Call<ModeloCliente> inserirLogin(@Body ModeloCliente cliente);

    @POST("/android/rest/cliente/cadastro")
    Call<ModeloCliente> inserirCliente(@Body ModeloCliente cliente);
}
