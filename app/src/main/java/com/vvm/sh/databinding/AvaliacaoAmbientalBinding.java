package com.vvm.sh.databinding;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.OnAvaliacaoAmbientalListener;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class AvaliacaoAmbientalBinding {




    @BindingAdapter({"equipamento"})
    public static void setNomeEquipamento(TextView view, int tipo) {

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                view.setText(view.getContext().getString(R.string.termohigrometro));
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                view.setText(view.getContext().getString(R.string.luxometro));
                break;

            default:
                break;

        }
    }


    @BindingAdapter({"tituloRelatorio"})
    public static void setTituloRelatorio(TextView view, int tipo) {

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                view.setText(view.getContext().getString(R.string.iluminacao));
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                view.setText(view.getContext().getString(R.string.temperatura_humidade));
                break;

            default:
                break;

        }
    }


    @BindingAdapter({"imagemRelatorio"})
    public static void setImagemRelatorio(ImageView view, int tipo) {

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                view.setBackgroundResource(R.drawable.iluminacao_banner);
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                view.setBackgroundResource(R.drawable.termico_banner);
                break;

            default:
                break;

        }
    }




    @BindingAdapter({"listagemTipos"})
    public static void setListagemTipos(TextView view, List<Tipo> tipos) {

        String resultado = "";

        if(tipos != null) {

            resultado = "";

            for (Tipo item : tipos) {

                resultado += item.id + ". " + item.descricao + "\n";
            }
        }

        view.setText(resultado);

    }


    @BindingAdapter({"avaliacoesAmbientais", "tipo", "listener"})
    public static void setAvaliacoesAmbientais(RecyclerView view, List<AvaliacaoAmbiental> items, int tipo, OnAvaliacaoAmbientalListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AvaliacaoRecyclerAdapter adapter = (AvaliacaoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AvaliacaoRecyclerAdapter(view.getContext(), items, tipo, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }



}
