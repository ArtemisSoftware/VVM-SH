package com.vvm.sh.databinding;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.cliente.extintores.adaptadores.ExtintorRecyclerAdapter;
import com.vvm.sh.ui.cliente.extintores.adaptadores.OnExtintoresListener;
import com.vvm.sh.ui.cliente.extintores.modelos.ExtintorRegisto;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.List;

public class ParqueExtintorBinding {

    @BindingAdapter({"extintores", "onItemClick"})
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

        view.setText(estatistica + " registos validados");
    }


    @BindingAdapter({"valido"})
    public static void setValido(ImageView view, ExtintorRegisto registo) {

        if(registo.resultado != null){
            view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.cor_sincronizado), android.graphics.PorterDuff.Mode.SRC_IN);
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
}
