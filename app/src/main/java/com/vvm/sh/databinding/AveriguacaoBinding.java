package com.vvm.sh.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.AveriguacaoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.AveriguacaoRegistoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.OnAveriguacaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class AveriguacaoBinding {


    @BindingAdapter({"tipoAveriguacao"})
    public static void setTituloAveriguacao(TextView view, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO){
            view.setText(view.getContext().getString(R.string.avaliacaoRiscos));
        }
        else{
            view.setText(view.getContext().getString(R.string.auditoria));
        }
    }


    @BindingAdapter({"averiguacoes", "listener"})
    public static void setAveriguacoes(RecyclerView view, List<Averiguacao> items, OnAveriguacaoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AveriguacaoRecyclerAdapter adapter = (AveriguacaoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AveriguacaoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }



    @BindingAdapter({"averiguacaoRegistos", "listener"})
    public static void setAveriguacoesRegistos(RecyclerView view, List<AveriguacaoRegisto> items, OnAveriguacaoListener.OnAveriguacaoItemListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AveriguacaoRegistoRecyclerAdapter adapter = (AveriguacaoRegistoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AveriguacaoRegistoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }


    @BindingAdapter({"completudeItem"})
    public static void setCompletudeItem(ImageView view, Averiguacao item) {

        if (item == null) {
            return;
        }

        if(item.numeroRegistos == item.total){
            view.setImageResource(R.drawable.ic_validado);
            view.setColorFilter(view.getContext().getResources().getColor(R.color.cor_executado));
        }
        else{
            view.setImageResource(R.drawable.ic_registo_incompleto);
            view.clearColorFilter();
        }
    }


    @BindingAdapter({"idRelatorio", "estado"})
    public static void setEstado(Chip view, int idRelatorio, boolean estado) {

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        if(idRelatorio == 0){
            view.setVisibility(View.GONE);
            return;
        }


        view.setVisibility(View.VISIBLE);

        if(estado == true){

            view.setText(view.getContext().getString(R.string.implentado));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_completo);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_validado));
            view.getChipIcon().setTint(view.getContext().getResources().getColor(R.color.cor_branco));


        }
        else{
            view.setText(view.getContext().getString(R.string.nao_implentado));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_incompleto);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_nao_executado_24dp));
        }
    }

}
