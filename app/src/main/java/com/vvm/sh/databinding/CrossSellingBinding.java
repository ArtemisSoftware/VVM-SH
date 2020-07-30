package com.vvm.sh.databinding;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.ui.crossSelling.adaptadores.CrossSellingRecyclerAdapter;
import com.vvm.sh.ui.crossSelling.adaptadores.OnCrossSellingListener;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;

public class CrossSellingBinding {

    @BindingAdapter({"tipos"})
    public static void setTipos(NiceSpinner view, List<Tipo> registos) {

        if (registos == null)
            return;


        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<Tipo>() {
            @Override
            public Spannable format(Tipo tipo) {

                //TODO: ver ids na versao de testes

                return new SpannableString(tipo.descricao);
            }
        };

        view.setSpinnerTextFormatter(textFormatter);
        view.setSelectedTextFormatter(textFormatter);
        /*
        view.setSelectedTextFormatter(new SpinnerTextFormatter<Tipo>() {
            @Override
            public Spannable format(Tipo tipo) {
                return new SpannableString(tipo.descricao);
            }
        });
        */
        view.attachDataSource(registos);
    }



    @BindingAdapter({"registos", "checkBox"})
    public static void setRegistos(RecyclerView view, List<Tipo> items, OnCrossSellingListener listener) {

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
