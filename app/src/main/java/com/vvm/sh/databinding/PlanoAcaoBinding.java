package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.planoAccao.adaptadores.OnPlanoAtividadeListener;
import com.vvm.sh.ui.planoAccao.adaptadores.PlanoAtividadeRecyclerAdapter;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;

import java.util.List;

public class PlanoAcaoBinding {


    @BindingAdapter({"atividades", "listener"})
    public static void setAtividadesPlano(RecyclerView view, List<AtividadeRegisto> items, OnPlanoAtividadeListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PlanoAtividadeRecyclerAdapter adapter = (PlanoAtividadeRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new PlanoAtividadeRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }


}
