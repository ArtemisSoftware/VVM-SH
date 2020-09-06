package com.vvm.sh.util.metodos;

import android.text.Html;

import com.vvm.sh.util.constantes.Sintaxe;

public class StringUtil {


    /**
     * Metodo que permite converter um inteiro para String
     * @param valor o valor a converter
     * @return o valor convertido
     */
    public static String converterString(int valor){
        return valor + "";
    }


    /**
     * Metodo que permite formatar uma string com caracters html
     * @param valor o valor a formatar
     * @return o valor formatado
     */
    public static String formatarHtml(String valor){

        if(valor == null){
            return Sintaxe.SEM_TEXTO;
        }

        return Html.fromHtml(valor).toString();
    }


}
