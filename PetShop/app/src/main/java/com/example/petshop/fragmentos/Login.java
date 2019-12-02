package com.example.petshop.fragmentos;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.petshop.R;
import com.example.petshop.retrofit.api.ApiCliente;
import com.example.petshop.retrofit.modelo.ModeloCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    private EditText tLogin;
    private EditText tSenha;
    private Button bLogar;
    private Button bCadastrar;


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Infla o XML de login
        View view = inflater.inflate(R.layout.login, container, false);

        tLogin = view.findViewById(R.id.tLogin);
        tSenha = view.findViewById(R.id.tSenha);
        bLogar = view.findViewById(R.id.bLogar);
        bCadastrar = view.findViewById(R.id.bCadastrar);

        View.OnClickListener listenerBLogar = new View.OnClickListener(){
            public void onClick(View v) {
                ModeloCliente Cliente = new ModeloCliente();
                Cliente.setEmailCliente(tLogin.getText().toString());
                Cliente.setSenhaCliente(tSenha.getText().toString());

                Retrofit instanciaRetrofit = new Retrofit.Builder()
                        .baseUrl("https://oficinacordova.azurewebsites.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiCliente apiCliente = instanciaRetrofit.create(ApiCliente.class);
                Call<ModeloCliente> callModeloCliente = apiCliente.inserirCliente(Cliente);

                Callback<ModeloCliente> callbackCliente =
                        new Callback<ModeloCliente>() {
                            @Override
                            public void onResponse(Call<ModeloCliente> call,
                                                   Response<ModeloCliente> response) {

                            }
                            @Override
                            public void onFailure(Call<ModeloCliente> call,
                                                  Throwable t) {

                            }
                        };
                callModeloCliente.enqueue(callbackCliente);


            }
        };
        bLogar.setOnClickListener(listenerBLogar);


        View.OnClickListener listenerBCadastrar = new View.OnClickListener(){
            public void onClick(View v) {

            }
        };
        bCadastrar.setOnClickListener(listenerBCadastrar);

        return view;
    }

}
