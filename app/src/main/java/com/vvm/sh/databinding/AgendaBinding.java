package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.agenda.adaptadores.TarefaRecyclerAdapter;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;

import java.util.List;

public class AgendaBinding {

    @BindingAdapter({"tarefas", "onItemClick"})
    public static void setTarefas(RecyclerView view, List<TarefaDia> items, OnAgendaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TarefaRecyclerAdapter adapter = (TarefaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new TarefaRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }
}
