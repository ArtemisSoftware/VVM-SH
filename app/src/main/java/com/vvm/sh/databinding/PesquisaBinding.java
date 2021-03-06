package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.modelos.Equipamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.adaptadores.EquipamentoRecyclerHolder;
import com.vvm.sh.ui.pesquisa.adaptadores.OnPesquisaListener;
import com.vvm.sh.ui.pesquisa.adaptadores.PesquisaMedidaRecyclerAdapter;
import com.vvm.sh.ui.pesquisa.adaptadores.PesquisaRecyclerAdapter;
import com.vvm.sh.ui.pesquisa.modelos.Medida;

import java.util.ArrayList;
import java.util.List;

public class PesquisaBinding {


    @BindingAdapter({"registosPesquisa", "listener"})
    public static void setRegistosPesquisa(RecyclerView view, List<Tipo> items, OnPesquisaListener.OnPesquisaRegistoListener listener) {

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



    @BindingAdapter({"registosEquipamentos", "listener"})
    public static void setRegistosEquipamentos(RecyclerView view, List<Equipamento> items, OnPesquisaListener.OnPesquisaEquipamentoRegistoListener listener) {

        if(items == null){
            return;
        }


        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();


        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        EquipamentoRecyclerHolder adapter = (EquipamentoRecyclerHolder) view.getAdapter();

        if(adapter == null){
            adapter = new EquipamentoRecyclerHolder(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"equipamentosSelecionado", "listener"})
    public static void setsEquipamentosSelecionado(RecyclerView view, List<Equipamento> items, OnPesquisaListener.OnPesquisaEquipamentoSelecionadoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();


        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        EquipamentoRecyclerHolder adapter = (EquipamentoRecyclerHolder) view.getAdapter();

        if(adapter == null){
            adapter = new EquipamentoRecyclerHolder(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }




    @BindingAdapter({"registosMedidas", "listener"})
    public static void setRegistosMedidas(RecyclerView view, List<Medida> items, OnPesquisaListener.OnPesquisaMedidaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PesquisaMedidaRecyclerAdapter adapter = (PesquisaMedidaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new PesquisaMedidaRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }




}
