package com.example.petshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.petshop.R;
import com.example.petshop.fragmentos.Carrinho;
import com.example.petshop.fragmentos.Catalogo;
import com.example.petshop.fragmentos.HistoricoCompras;
import com.example.petshop.fragmentos.Perfil;
import com.example.petshop.fragmentos.Sobre;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    Catalogo catalogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //Habilita o botão de abertura do menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        catalogo = new Catalogo();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, catalogo).commit();

        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                drawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.action_inicio) {
                    Catalogo fragInicio = new Catalogo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragInicio).commit();
                    return true;
                }
                if (menuItem.getItemId() == R.id.action_perfil) {
                    Perfil fragPerfil = new Perfil();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragPerfil).commit();
                    return true;
                }
                if (menuItem.getItemId() == R.id.action_carrinho) {
                    Carrinho fragCarrinho = new Carrinho();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragCarrinho).commit();
                    return true;
                }
                if (menuItem.getItemId() == R.id.action_compras) {
                    HistoricoCompras fragCompras = new HistoricoCompras();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragCompras).commit();
                    return true;
                }
                if (menuItem.getItemId() == R.id.action_sobre) {
                    Sobre fragSobre = new Sobre();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragSobre).commit();
                    return true;
                }

                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer) {
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_top_bar, menu);
        return true;
    }

}

