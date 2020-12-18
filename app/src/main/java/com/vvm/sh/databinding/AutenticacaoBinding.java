package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.baseDados.entidades.Utilizador;


import java.util.List;

public class AutenticacaoBinding {

    @BindingAdapter({"utilizadores"})
    public static void setUtilizadores(MaterialSpinner view, List<Utilizador> registos) {


        if (registos == null)
            return;

        view.setItems(registos);
    }

}
