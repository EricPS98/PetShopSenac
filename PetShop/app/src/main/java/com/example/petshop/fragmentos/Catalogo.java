package com.example.petshop.fragmentos;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petshop.R;
import com.example.petshop.retrofit.api.ApiProduto;
import com.example.petshop.retrofit.modelo.ModeloProduto;
import com.example.petshop.utils.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Catalogo extends Fragment {

    private ViewGroup conteudo;
    private FloatingActionButton fbPesquisar;

    public Catalogo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Infla o XML de catálogo
        View view = inflater.inflate(R.layout.catalogo, container, false);

        conteudo = view.findViewById(R.id.produtos);
        fbPesquisar = view.findViewById(R.id.fbPesquisar);

        fbPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoriasProduto pesquisarProdutos = new CategoriasProduto();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, pesquisarProdutos);
                fragmentTransaction.commit();
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://oficinacordova.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();

        ApiProduto apiProduto = retrofit.create(ApiProduto.class);
        Call<List<ModeloProduto>> callModeloProduto = apiProduto.listarProdutos();

        Callback<List<ModeloProduto>> callbackProduto = new Callback<List<ModeloProduto>>() {
            @Override
            public void onResponse(Call<List<ModeloProduto>> call, Response<List<ModeloProduto>> response) {
                List<ModeloProduto> listaProd = response.body();
                for (int i = 0; i < listaProd.size(); i++){
                    ModeloProduto m = listaProd.get(i);

                    addItem(m.getNomeProduto(), m.getPrecProduto(),  m.getIdProduto());
                }

            }
            @Override
            public void onFailure(Call<List<ModeloProduto>> call, Throwable t) {
                t.printStackTrace();
                showDialog("Falha ao obter a lista de produtos", "erro");
            }
        };
        callModeloProduto.enqueue(callbackProduto);

        return view;

    }

    private void addItem(String tTitulo, Float tPreco, final int id){

        CardView cardView = (CardView) LayoutInflater.from(getActivity()).inflate(R.layout.item_produto, conteudo, false);
        TextView titulo = cardView.findViewById(R.id.tTitulo);
        TextView preco = cardView.findViewById(R.id.tPrecoProduto);
        ImageView imagem = cardView.findViewById(R.id.imagem);

        String url = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/" +id;
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));

        imageLoader.displayImage(url, imagem);
        titulo.setText(tTitulo);
        preco.setText(Util.formataValor(tPreco));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DetalheProduto.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        conteudo.addView(cardView);

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
