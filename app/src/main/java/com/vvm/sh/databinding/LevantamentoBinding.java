package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.CategoriaProfissionalRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.LevantamentoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.OnLevantamentoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.RiscoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;

import java.util.List;

public class LevantamentoBinding {

    @BindingAdapter({"levantamentos", "listener"})
    public static void setLevantamentos(RecyclerView view, List<Levantamento> items, OnLevantamentoListener.OnLevantamentoRegistoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        LevantamentoRecyclerAdapter adapter = (LevantamentoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new LevantamentoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"categorias", "listener"})
    public static void seCategorias(RecyclerView view, List<CategoriaProfissional> items, OnLevantamentoListener.OnCategoriaProfissionalListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        CategoriaProfissionalRecyclerAdapter adapter = (CategoriaProfissionalRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new CategoriaProfissionalRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"riscos", "listener"})
    public static void setRiscos(RecyclerView view, List<Risco> items, OnLevantamentoListener.OnRiscoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        RiscoRecyclerAdapter adapter = (RiscoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new RiscoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


}
