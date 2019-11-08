package com.example.eric.petshop;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricoCompras extends Fragment {


    public HistoricoCompras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Infla o XML de compras realizadas
        return inflater.inflate(R.layout.historico_compras, container, false);
    }

}
