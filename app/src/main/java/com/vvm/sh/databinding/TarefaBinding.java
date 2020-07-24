package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.tarefa.adaptadores.OnTarefaListener;
import com.vvm.sh.ui.tarefa.adaptadores.OpcaoClienteRecyclerAdapter;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;

import java.util.List;

public class TarefaBinding {

    @BindingAdapter({"opcoesCliente", "onItemClick"})
    public static void setOpcoesCliente(RecyclerView view, List<OpcaoCliente> items, OnTarefaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        OpcaoClienteRecyclerAdapter adapter = (OpcaoClienteRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new OpcaoClienteRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }
}
