package com.vvm.sh.databinding;

import android.text.Spannable;
import android.text.SpannableString;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.crossSelling.CrossSellingRecyclerAdapter;
import com.vvm.sh.ui.crossSelling.OnCrossSellingListener;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;

public class CrossSellingBinding {

    @BindingAdapter({"produtos"})
    public static void setProdutos(NiceSpinner view, List<Tipo> registos) {

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

}
