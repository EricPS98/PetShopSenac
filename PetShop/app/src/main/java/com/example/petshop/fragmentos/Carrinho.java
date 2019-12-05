package com.example.petshop.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.R;
import com.example.petshop.retrofit.modelo.ModeloProduto;
import com.example.petshop.utils.Singleton;
import com.example.petshop.utils.Util;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class Carrinho extends AppCompatActivity {

    private TextView tConteudo;
    private List<ModeloProduto> listaProduto;
    private ViewGroup lCarrinho;
    private TextView tPreco;
    private Button bComprar, bLimpar, bRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tConteudo = findViewById(R.id.tConteudo);
        lCarrinho = findViewById(R.id.svProdutos);
        tPreco = findViewById(R.id.tPrecoProduto);
        bComprar = findViewById(R.id.bComprar);
        bLimpar = findViewById(R.id.bLimpar);
        bRemover = findViewById(R.id.bRemover);

        final Singleton singleton = Singleton.getInstance();
        listaProduto = singleton.getCarrinho();

        if (listaProduto != null) {
            try{
                addProdutos(listaProduto);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        bComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Carrinho.this, Login.class);
                startActivity(i);
            }
        });

        bLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleton.removeAll();
                addProdutos(singleton.getCarrinho());
            }
        });

    }

    public void addProdutos(List<ModeloProduto> list){

        float aPreco, aTotal = 0;

        lCarrinho.removeAllViews();

        if (list.isEmpty() || list == null) {

            tPreco.setText(Util.formataValor((float) 0.00));

        } else {
            for (ModeloProduto modeloProduto : list) {
                aPreco = modeloProduto.getPrecProduto();

                addItem(modeloProduto.getNomeProduto(), aPreco, modeloProduto.getIdProduto());
                aTotal += aPreco;
            }
            tPreco.setText(Util.formataValor(aTotal));
        }

    }

    private void addItem(String tNome, float tPreco, final int id) {
        final LinearLayout itemCarrinho = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_carrinho, lCarrinho, false);

        TextView titulo = itemCarrinho.findViewById(R.id.tTitulo);
        TextView preco = itemCarrinho.findViewById(R.id.tPrecoProduto);
        ImageView imagem = itemCarrinho.findViewById(R.id.imagem);
        Button bRemover = itemCarrinho.findViewById(R.id.bRemover);

        String url = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/" +id;
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(Carrinho.this));

        imageLoader.displayImage(url, imagem);
        titulo.setText(tNome);
        preco.setText(Util.formataValor(tPreco));

        bRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Singleton s = Singleton.getInstance();
                List<ModeloProduto> listAux = s.getCarrinho();
                for (ModeloProduto p : listAux) {
                    if (id == p.getIdProduto()) {
                        listAux.remove(p);
                    }
                    break;
                }
                s.setCarrinho(listAux);
                addProdutos(listAux);
            }
        });

        lCarrinho.addView(itemCarrinho);

    }
}
