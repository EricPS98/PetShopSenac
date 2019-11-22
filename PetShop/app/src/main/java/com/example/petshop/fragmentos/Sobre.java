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
public class Sobre extends Fragment {


    public Sobre() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Infla o XML de sobre
        return inflater.inflate(R.layout.sobre, container, false);
    }

}
