package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;

import java.util.List;

public class ChecklistBinding {


    @BindingAdapter({"itens",  "listener"})
    public static void setChecklist(RecyclerView view, List<Item> items, OnChecklistListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
/*
        AvaliacaoRecyclerAdapter adapter = (AvaliacaoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AvaliacaoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
*/
    }



}
