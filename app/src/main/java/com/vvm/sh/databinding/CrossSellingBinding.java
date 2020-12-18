package com.vvm.sh.databinding;

import android.text.Spannable;
import android.text.SpannableString;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.ui.crossSelling.adaptadores.CrossSellingRecyclerAdapter;
import com.vvm.sh.ui.crossSelling.adaptadores.OnCrossSellingListener;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;


import java.util.List;

public class CrossSellingBinding {


    @BindingAdapter({"registos", "checkBox"})
    public static void setRegistos(RecyclerView view, List<CrossSelling> items, OnCrossSellingListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        CrossSellingRecyclerAdapter adapter = (CrossSellingRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new CrossSellingRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"tipos_"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos) {

        if (registos == null)
            return;


        view.setItems(registos);
    }


}
