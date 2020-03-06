package com.vvm.sh.util.constantes;

import android.content.Context;

import com.vvm.sh.util.adaptadores.SpinnerAdaptador;
import com.vvm.sh.util.itens.ItemSpinner;

import java.util.ArrayList;
import java.util.List;

public class Testes {


    public static SpinnerAdaptador obterUtilizadores(Context contexto){

        ArrayList<ItemSpinner> resultado = new ArrayList();

        resultado.add(new ItemSpinner(1, "Super administrador", "3090"));
        resultado.add(new ItemSpinner(2, "Etelvina Verediana", "657547"));
        resultado.add(new ItemSpinner(3, "Dinorá Jacinta", "21212"));

        return new SpinnerAdaptador(contexto, resultado);
    }

}
