package com.vvm.sh.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.parqueExtintores.adaptadores.ExtintorRecyclerAdapter;
import com.vvm.sh.ui.parqueExtintores.adaptadores.OnExtintoresListener;
import com.vvm.sh.ui.parqueExtintores.modelos.ExtintorRegisto;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.List;

public class ParqueExtintorBinding {

    @BindingAdapter({"extintores", "listener"})
    public static void setParqueExtintor(RecyclerView view, List<ExtintorRegisto> items, OnExtintoresListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        ExtintorRecyclerAdapter adapter = (ExtintorRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new ExtintorRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }


    @BindingAdapter({"estatistica"})
    public static void setEstatisitica(Chip view, int estatistica) {

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();
        view.setText(estatistica + " " + view.getContext().getString(R.string.registos_validados) );
    }


    @BindingAdapter({"valido"})
    public static void setValido(ImageView view, ExtintorRegisto registo) {

        if(registo.resultado != null){

            if(registo.resultado.dataValidade.equals(registo.parqueExtintor.dataValidade) == false) {

                view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.cor_sincronizado), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
    }


    @BindingAdapter({"extintor"})
    public static void setDataValidade(TextView view, ExtintorRegisto registo) {

        if(registo.resultado == null){
            view.setText(DatasUtil.converterData(registo.parqueExtintor.dataValidade, DatasUtil.FORMATO_DD_MM_YYYY));
        }
        else{
            view.setText(DatasUtil.converterData(registo.resultado.dataValidade, DatasUtil.FORMATO_DD_MM_YYYY));
        }
    }


    @BindingAdapter({"extintorValidado"})
    public static void setValidadeExtintor(ImageView view, ExtintorRegisto registo) {

        if(registo.resultado == null){
            view.setVisibility(View.INVISIBLE);
        }
        else{
            view.setVisibility(View.VISIBLE);
        }
    }

}
