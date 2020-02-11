package com.vvm.sh.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {


    private static final String PRIMEIRA_UTILIZACAO = "primeira_utilizacao";
    private static final String VERSAO_APP = "versao_app";


    /**
     * Metodo que permite fixar os dados da primeira utilizacao da app
     * @param contexto
     * @param primeiraUtilizacao boolean que indica se é a primeira utilizacao da app
     * @param versaoApp a versão da app
     */
    public static void fixarPrimeiraUtilizacao(Context contexto, boolean primeiraUtilizacao, String versaoApp){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(PRIMEIRA_UTILIZACAO, primeiraUtilizacao);
        editor.putString(VERSAO_APP, versaoApp);
        editor.commit();
    }


    /**
     * Metodo que permite obter o estado da primeira utilizacao
     * @param contexto
     * @return true caso seja a primeira utilizacao ou false caso contrario
     */
    public static boolean obterPrimeiraUtilizacao(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getBoolean(PRIMEIRA_UTILIZACAO, true);
    }

}
