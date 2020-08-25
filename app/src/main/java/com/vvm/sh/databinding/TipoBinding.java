package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.opcoes.modelos.Colecao;
import com.vvm.sh.ui.opcoes.adaptadores.OnTipoListener;
import com.vvm.sh.ui.opcoes.adaptadores.TipoRecyclerAdapter;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;

import java.util.List;

public class TipoBinding {


    @BindingAdapter({"tipos", "onLongClick"})
    public static void setTipos(RecyclerView view, List<ResumoTipo> items, OnTipoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TipoRecyclerAdapter adapter = (TipoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new TipoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


}
