package com.vvm.sh.databinding;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.adaptadores.MarcacaoRecyclerAdapter;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.agenda.modelos.Marcacao;

import static com.vvm.sh.util.constantes.Identificadores.App.*;
import static com.vvm.sh.util.constantes.Identificadores.Sincronizacao.*;

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

            case APP_SA:

                sigla = SA;
                cor = COR_SA;
                break;

            default:
                break;

        }

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(sigla, cor);

        view.setImageDrawable(drawable);

    }



    @BindingAdapter({"sincronizado"})
    public static void setSincronizacao(Chip view, int sincronizado) {

        if(sincronizado == 0){
            view.setVisibility(View.INVISIBLE);
            return;
        }

        view.setVisibility(View.VISIBLE);


        int descricao = 0, cor = -1, icon = -1;

        switch (sincronizado){


            case SINCRONIZADO:

                descricao = R.string.sincronizado;
                cor =R.color.cor_sincronizado;
                icon = R.drawable.ic_sincronizado_24dp;
                break;

            case NAO_SINCRONIZADO:

                descricao = R.string.nao_sincronizado;
                cor = R.color.cor_nao_sincronizado;
                icon = R.drawable.ic_sincronizado_24dp;
                break;


            case TRANCADO:

                descricao = R.string.trancado;
                cor = R.color.cor_trancado;
                icon = R.drawable.ic_trancado_24dp;
                break;

            default:
                break;

        }

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        view.setText(view.getContext().getString(descricao));
        chipDrawable.setChipBackgroundColorResource(cor);
        view.setChipIcon(view.getContext().getResources().getDrawable(icon));

    }


}
