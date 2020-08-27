package com.vvm.sh.databinding;

import android.text.Spannable;
import android.text.SpannableString;

import androidx.databinding.BindingAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.baseDados.entidades.Utilizador;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;

public class AutenticacaoBinding {

    @BindingAdapter({"utilizadores"})
    public static void setUtilizadores(MaterialSpinner view, List<Utilizador> registos) {


        if (registos == null)
            return;

        view.setItems(registos);
    }

}
