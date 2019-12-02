package com.example.petshop.fragmentos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.R;
import com.example.petshop.retrofit.api.ApiProduto;
import com.example.petshop.retrofit.modelo.ModeloProduto;
import com.example.petshop.utils.Singleton;
import com.example.petshop.utils.Util;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalheProduto extends AppCompatActivity {

    private ImageView imagem;
    private TextView tTitulo, tPreco, tPrecoFinal, tDescricao;
    private Button bAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_produto);

        imagem = findViewById(R.id.imagemDetalhe);
        tTitulo = findViewById(R.id.tTitulo);
        tPreco = findViewById(R.id.tPreco);
        tPrecoFinal = findViewById(R.id.tPrecoFinal);
        tDescricao = findViewById(R.id.tDescricao);
        bAdd = findViewById(R.id.bAdd);

        Intent i = getIntent();
        int id = i.getIntExtra("id",0);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://oficinacordova.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();

        ApiProduto apiProd = retrofit.create(ApiProduto.class);
        Call<ModeloProduto> call = apiProd.getProduto(id);

        call.enqueue(new Callback<ModeloProduto>() {
            @Override
            public void onResponse(Call<ModeloProduto> call, Response<ModeloProduto> response) {
                final ModeloProduto produto =  response.body();

                try {
                    if (produto.getDescontoPromocao() == 0) {
                        tPrecoFinal.setVisibility(View.INVISIBLE);
                        tPreco.setText(Util.formataValor(produto.getPrecProduto()));

                    } else {
                        Float precoTotal = (produto.getPrecProduto() - produto.getDescontoPromocao());

                        SpannableString preco = new SpannableString(Util.formataValor(produto.getPrecProduto()));
                        preco.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, preco.length(), 0);

                        tPreco.setText(preco);
                        tPrecoFinal.setText("Promoção: " + Util.formataValor(precoTotal));
                    }

                    tTitulo.setText(produto.getNomeProduto());
                    tDescricao.setText(produto.getDescProduto());

                    String url = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/" + produto.getIdProduto();
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    imageLoader.init(ImageLoaderConfiguration.createDefault(DetalheProduto.this));

                    imageLoader.displayImage(url, imagem);

                    bAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Singleton s = Singleton.getInstance();
                            s.addCarrinho(produto);
                            showDialog("OK", "Produto adicionado no Carrinho");
                        }
                    });

                } catch (Throwable t) {
                    t.printStackTrace();
                }
                
            }

            @Override
            public void onFailure(Call<ModeloProduto> call, Throwable t) {
                t.printStackTrace();
                showDialog("Falha ao obter a lista de produtos", "erro");
            }
        });
    }
    private void showDialog(String val, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetalheProduto.this);
        builder.setMessage(val);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
