package com.vvm.sh.databinding;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.registoVisita.adaptadores.OnRegistoVisitaListener;
import com.vvm.sh.ui.registoVisita.adaptadores.TrabalhoRealizadoRecyclerAdaptor;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;

import java.util.List;

public class RegistoVisitaBinding {

    @BindingAdapter({"trabalhos" , "listener"})
    public static void setTrabalhosRealizados(RecyclerView view, List<TrabalhoRealizado> items, OnRegistoVisitaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TrabalhoRealizadoRecyclerAdaptor adapter = (TrabalhoRealizadoRecyclerAdaptor) view.getAdapter();

        if(adapter == null){
            adapter = new TrabalhoRealizadoRecyclerAdaptor(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"completudeSincronizacao"})
    public static void setCompletudeSincronizacao(Chip view, boolean estado) {

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        if(estado == true){

            view.setText(view.getContext().getString(R.string.sincronizado));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_enviado);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_enviado));
            view.getChipIcon().setTint(view.getContext().getResources().getColor(R.color.cor_branco));
            view.setVisibility(View.VISIBLE);

        }
        else{
            view.setText(view.getContext().getString(R.string.nao_sincronizado));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_nao_sincronizado);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_sincronizado_24dp));
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter({"sincronizacao"})
    public static void setSincronizacao(CardView view, boolean estado) {

        if(estado == true){
            view.setEnabled(false);
        }
        else{
            view.setEnabled(true);
        }
    }
}
