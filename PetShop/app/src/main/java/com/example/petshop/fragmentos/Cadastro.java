package com.example.petshop.fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.petshop.R;
import com.example.petshop.retrofit.api.ApiCliente;
import com.example.petshop.retrofit.modelo.ModeloCliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cadastro extends Fragment {

    private EditText tEmail;
    private EditText tSenha;
    private EditText tNome;
    private EditText tCPF;
    private EditText tCelular;
    private EditText tTelComercial;
    private EditText tTelResidencial;
    private Button bLimpar;
    private Button bAvancar;

    public Cadastro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Infla o XML de cadastro
        View view = inflater.inflate(R.layout.cadastro, container, false);

        tEmail = view.findViewById(R.id.tEmail);
        tSenha = view.findViewById(R.id.tSenha);
        tNome = view.findViewById(R.id.tNome);
        tCPF = view.findViewById(R.id.tCPF);
        tCelular = view.findViewById(R.id.tCelular);
        tTelComercial = view.findViewById(R.id.tTelComercial);
        tTelResidencial = view.findViewById(R.id.tTelResidencial);
        bLimpar = view.findViewById(R.id.bLimpar);
        bAvancar = view.findViewById(R.id.bAvancar);

        View.OnClickListener listenerBLimpar = new View.OnClickListener(){
            public void onClick(View v){
                tEmail.setText("");
                tSenha.setText("");
                tNome.setText("");
                tCPF.setText("");
                tCelular.setText("");
                tTelComercial.setText("");
                tTelResidencial.setText("");
            }
        };
        bLimpar.setOnClickListener(listenerBLimpar);

        View.OnClickListener listenerBAvancar = new View.OnClickListener(){
            public void onClick(View v){
                ModeloCliente Cliente = new ModeloCliente();
                Cliente.setEmailCliente(tEmail.getText().toString());
                Cliente.setSenhaCliente(tSenha.getText().toString());
                Cliente.setNomeCompletoCliente(tNome.getText().toString());
                Cliente.setCPFCliente(tCPF.getText().toString());
                Cliente.setCelularCliente(tCelular.getText().toString());
                Cliente.setTelComercialCliente(tTelComercial.getText().toString());
                Cliente.setTelResidencialCliente(tTelResidencial.getText().toString());


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
        bAvancar.setOnClickListener(listenerBAvancar);

        return view;
    }

}
