package com.example.petshop.fragmentos;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petshop.R;
import com.example.petshop.retrofit.api.ApiCategoria;
import com.example.petshop.retrofit.modelo.ModeloCategoria;
import com.example.petshop.utils.TabPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasProduto extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabPageAdapter adapter;


    public CategoriasProduto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Infla o XML de pesquisa
        View view = inflater.inflate(R.layout.categorias_produto, container, false);


        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tabslayout);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://oficinacordova.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();

        ApiCategoria apiCategoria = retrofit.create(ApiCategoria.class);
        Call<List<ModeloCategoria>> callCategoria = apiCategoria.getCategoria();

        callCategoria.enqueue(new Callback<List<ModeloCategoria>>() {
            @Override
            public void onResponse(Call<List<ModeloCategoria>> call, Response<List<ModeloCategoria>> response) {
                List<ModeloCategoria> listaCat = response.body();
                Resources resources = getResources();

                adapter = new TabPageAdapter(getChildFragmentManager());

                for (int i = 0; i < listaCat.size(); i++) {
                    adapter.add(new Categoria(listaCat.get(i).getIdCategoria(), listaCat.get(i).getNomeCategoria()), listaCat.get(i).getNomeCategoria());
                }

                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void onFailure(Call<List<ModeloCategoria>> call, Throwable t) {
                t.printStackTrace();
                showDialog("Falha ao obter a lista de categorias", "erro");
            }
        });

        return view;
    }

    private void showDialog(String val, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(val);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
