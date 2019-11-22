package com.example.petshop.fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petshop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Carrinho extends Fragment {


    public Carrinho() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Infla o XML de carrinho de compras
        return inflater.inflate(R.layout.carrinho, container, false);
    }

}
