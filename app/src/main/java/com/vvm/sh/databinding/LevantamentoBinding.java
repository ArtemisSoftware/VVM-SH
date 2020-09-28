package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.LevantamentoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.OnLevantamentoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;

import java.util.List;

public class LevantamentoBinding {

    @BindingAdapter({"levantamentos", "listener"})
    public static void setTarefas(RecyclerView view, List<Levantamento> items, OnLevantamentoListener.OnLevantamentoRegistoListener listener) {

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

}
