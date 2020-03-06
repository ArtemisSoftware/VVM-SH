package com.vvm.sh.util.adaptadores;

import android.content.Context;

import com.vvm.sh.util.itens.ItemSpinner;

import java.util.ArrayList;

public class SpinnerAdaptador extends SpinnerBaseAdaptador {

    public SpinnerAdaptador(Context contexto, ArrayList<ItemSpinner> registos) {
        super(contexto, registos);
    }

    public SpinnerAdaptador(Context contexto, String[] registos) {
        super(contexto, registos);
    }
}
