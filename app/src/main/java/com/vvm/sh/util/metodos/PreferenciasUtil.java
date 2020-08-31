package com.vvm.sh.util.metodos;

import android.content.Context;
import android.content.SharedPreferences;

import com.vvm.sh.BuildConfig;
import com.vvm.sh.util.constantes.Identificadores;

public class PreferenciasUtil {


    private static final String PRIMEIRA_UTILIZACAO = "primeira_utilizacao";
    private static final String VERSAO_APP = "versao_app";
    private static final String ID_UTILIZADOR = "id_utilizador";
    private static final String ID_TAREFA = "id_tarefa";
    private static final String TAREFA_ATIVA = "tarefa_ativa";
    private static final String DATA_VALIDADE_AUTENTICACAO = "validade_autenticacao";
    private static final String TOKEN = "token";

    private static final String PREFERENCIAS_UTILIZADOR [] = { ID_UTILIZADOR, ID_TAREFA, TAREFA_ATIVA, DATA_VALIDADE_AUTENTICACAO, TOKEN };


    /**
     * Metodo que permite fixar os dados da primeira utilizacao da app
     * @param contexto
     * @param primeiraUtilizacao boolean que indica se é a primeira utilizacao da app
     */
    public static void fixarPrimeiraUtilizacao(Context contexto, boolean primeiraUtilizacao){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(PRIMEIRA_UTILIZACAO, primeiraUtilizacao);
        editor.putString(VERSAO_APP, BuildConfig.VERSION_NAME);
        editor.commit();
    }


    /**
     * Metodo que permite fixar os dados do utilizador
     * @param contexto
     * @param idUtilizador o identificador do utilizador
     */
    public static void fixarDadosUtilizador(Context contexto, String idUtilizador){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(ID_UTILIZADOR, idUtilizador);
        editor.putLong (DATA_VALIDADE_AUTENTICACAO, DatasUtil.obterDataValidadeAutenticacao());
        editor.commit();
    }



    /**
     * Metodo que permite fixar a tarefa em curso
     * @param contexto
     * @param idTarefa o identificador da tarefa
     * @param ativo indica se a agenda está ativa para edicao
     */
    public static void fixarTarefa(Context contexto, int idTarefa, boolean ativo){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt(ID_TAREFA, idTarefa);
        editor.putInt(TAREFA_ATIVA, idTarefa);
        editor.putBoolean(TAREFA_ATIVA, ativo);

        editor.commit();
    }




    /**
     * Metodo que permite atualizar a versao da aplicacao
     * @param contexto
     */
    public static void atualizarVersaoApp(Context contexto){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(VERSAO_APP, BuildConfig.VERSION_NAME);
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


    /**
     * Metodo que permite indetificar se é necessario realizar a apresentacao da versão da app
     * @param contexto
     * @return true caso seja necessário ou false caso contrario
     */
    public static boolean realizarApresentacaoApp(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        String versao = preferencias.getString(VERSAO_APP, "0.0.0");

        return !(BuildConfig.VERSION_NAME.equals(versao));
    }


    /**
     * Metodo que permite obter o identificador da tarefa em curso
     * @param contexto
     * @return o identificador da tarefa em curso
     */
    public static int obterIdTarefa(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getInt(ID_TAREFA, 0);
    }



    /**
     * Metodo que permite obter o identificador do utilizador
     * @param contexto
     * @return o identificador do utilizador
     */
    public static String obterIdUtilizador(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getString(ID_UTILIZADOR, "12724"); //TODO: deve ser um id inválido
    }


    /**
     * Metodo que permite obter a data de término da validade da autenticacao
     * @param contexto
     * @return uma data
     */
    public static long obterDataValidadeAutenticacao(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getLong(DATA_VALIDADE_AUTENTICACAO, 0L);
    }


    /**
     * Metodo que permite obter a completude da agenda atual
     * @param contexto
     * @return true caso a agenda esteja ativa para edição ou false caso contrário
     */
    public static boolean obterCompletudeAgenda(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getBoolean(TAREFA_ATIVA, true);
    }


    /**
     * Metodo que permite obter o token do utilizador
     * @param contexto
     * @return o token do utilizador
     */
    public static String obterToken(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getString(TOKEN, "");
    }




    /**
     * Metodo que permite eliminar os dados do utilizador
     * @param contexto
     */
    public static void eliminarDadosUtilizador(Context contexto){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        for (String item : PREFERENCIAS_UTILIZADOR) {
            editor.remove(item);
        }
        editor.apply();
    }


}
