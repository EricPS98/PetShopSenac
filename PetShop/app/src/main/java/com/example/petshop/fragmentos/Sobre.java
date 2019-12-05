package com.example.petshop.fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.petshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sobre extends Fragment {

    private LinearLayout lRodape;

    public Sobre() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Infla o XML de sobre
        View view = inflater.inflate(R.layout.sobre, container, false);

        return view;
    }

}
