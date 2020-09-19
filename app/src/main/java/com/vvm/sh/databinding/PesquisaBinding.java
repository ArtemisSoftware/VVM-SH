package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.pesquisa.OnPesquisaListener;
import com.vvm.sh.ui.pesquisa.Pesquisa;
import com.vvm.sh.ui.pesquisa.PesquisaRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PesquisaBinding {


    @BindingAdapter({"registosPesquisa", "listener"})
    public static void setRegistosPesquisa(RecyclerView view, List<Tipo> items, OnPesquisaListener.OnPesquisaRegistoListener listener) {

        if(items == null){
            return;
        }

        //List<Tipo> lolo = items;

        List<Tipo> lolo = new ArrayList<>();

        int i = 0;
        for (Tipo item : items) {

            lolo.add(item);

            if(i == 14){
                break;
            }

            ++i;
        }

        //TODO: fazer paginacao

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();


        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PesquisaRecyclerAdapter adapter = (PesquisaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new PesquisaRecyclerAdapter(view.getContext(), lolo, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(lolo);
        }

    }


    @BindingAdapter({"pesquisaSelecionado", "listener"})
    public static void setsPesquisaSelecionado(RecyclerView view, List<Tipo> items, OnPesquisaListener.OnPesquisaSelecionadoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();


        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PesquisaRecyclerAdapter adapter = (PesquisaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new PesquisaRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


}