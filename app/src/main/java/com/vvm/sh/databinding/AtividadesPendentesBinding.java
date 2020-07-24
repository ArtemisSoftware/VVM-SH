package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.atividadesPendentes.adaptadores.AtividadePendenteRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;

import java.util.List;

public class AtividadesPendentesBinding {

    @BindingAdapter({"atividadesPendentes"})
    public static void setAtividadesPendentes(RecyclerView view, List<AtividadePendente> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AtividadePendenteRecyclerAdapter adapter = (AtividadePendenteRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AtividadePendenteRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }
}