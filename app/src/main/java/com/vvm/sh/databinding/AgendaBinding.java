package com.vvm.sh.databinding;

import android.graphics.Color;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.vvm.sh.ui.agenda.adaptadores.MarcacaoRecyclerAdapter;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class AgendaBinding {

    @BindingAdapter({"tarefas", "onItemClick"})
    public static void setTarefas(RecyclerView view, List<Marcacao> items, OnAgendaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        MarcacaoRecyclerAdapter adapter = (MarcacaoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new MarcacaoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"empresa"})
    public static void setEmpresa(ImageView view, int empresa) {

        String sigla = "O";
        int cor = Color.RED;

        switch (empresa){

            case Identificadores.App.APP_SA:

                sigla = Identificadores.App.SA;
                cor = Identificadores.App.COR_SA;
                break;


            default:

                break;

        }

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .endConfig()
                .buildRound(sigla, cor);

        view.setImageDrawable(drawable);

    }

}
