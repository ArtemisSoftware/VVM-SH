package com.vvm.sh.databinding;

import android.text.Spannable;
import android.text.SpannableString;

import androidx.databinding.BindingAdapter;

import com.vvm.sh.autenticacao.Utilizador;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;

public class AutenticacaoBinding {

    @BindingAdapter({"utilizadores"})
    public static void setTipos(NiceSpinner view, List<Utilizador> registos) {

        if (registos == null)
            return;


        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<Utilizador>() {
            @Override
            public Spannable format(Utilizador utilizador) {

                return new SpannableString(utilizador.id + " - " + utilizador.nome);
            }
        };

        view.setSpinnerTextFormatter(textFormatter);
        view.setSelectedTextFormatter(textFormatter);
        view.attachDataSource(registos);
    }

}
