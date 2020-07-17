package com.vvm.sh.databinding;

import android.text.Spannable;
import android.text.SpannableString;

import androidx.databinding.BindingAdapter;

import com.vvm.sh.ui.opcoes.modelos.Tipo;

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


}
