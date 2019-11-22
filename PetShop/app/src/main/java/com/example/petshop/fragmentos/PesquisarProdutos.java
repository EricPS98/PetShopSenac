package com.example.petshop.fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.petshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisarProdutos extends Fragment {


    public PesquisarProdutos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pesquisar_produtos, container, false);
    }

}
