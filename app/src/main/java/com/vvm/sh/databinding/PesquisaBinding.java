package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.pesquisa.OnPesquisaListener;
import com.vvm.sh.ui.pesquisa.Pesquisa;

import java.util.List;

public class PesquisaBinding {


    @BindingAdapter({"registosPesquisa", "listener"})
    public static void setRegistosPesquisa(RecyclerView view, List<Pesquisa> items, OnPesquisaListener.OnPesquisaRegistoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        /*
        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        ExtintorRecyclerAdapter adapter = (ExtintorRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new ExtintorRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
        */
    }


    @BindingAdapter({"pesquisaSelecionado", "listener"})
    public static void setsPesquisaSelecionado(RecyclerView view, List<Pesquisa> items, OnPesquisaListener.OnPesquisaSelecionadoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        /*
        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        ExtintorRecyclerAdapter adapter = (ExtintorRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new ExtintorRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
        */
    }


}
