package com.example.petshop.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> listaFragmentos = new ArrayList<Fragment>();
    private List<String> listaTitulos =  new ArrayList<>();

    public TabPageAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
    }

    public void add(Fragment frag, String title){
        this.listaFragmentos.add(frag);
        this.listaTitulos.add(title);
    }

    @Override
    //retorna o Fragmento de acordo com o número da página do ViewPager
    public Fragment getItem(int posicao) {
        return listaFragmentos.get(posicao);
    }
    //retorna o número de Fragmentos que o componente irá gerenciar
    public int getCount() {
        return listaFragmentos.size();
    }
    @Override
    // retorna o título referente a página selecionada
    public CharSequence getPageTitle(int posicao) {
        return listaTitulos.get(posicao);
    }
}


