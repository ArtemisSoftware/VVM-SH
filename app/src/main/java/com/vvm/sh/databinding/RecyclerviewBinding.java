package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.contaUtilizador.Colecao;
import com.vvm.sh.ui.opcoes.adaptadores.TipoRecyclerAdapter;

import java.util.List;

public class RecyclerviewBinding {


    @BindingAdapter("tipos")
    public static void setTipos(RecyclerView view, List<Colecao> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TipoRecyclerAdapter adapter = (TipoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new TipoRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


}
