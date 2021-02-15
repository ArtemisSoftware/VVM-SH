package com.vvm.sh.util.metodos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.vvm.sh.BuildConfig;
import com.vvm.sh.api.modelos.pedido.IContagemTipoMaquina;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.apresentacao.ApresentacaoActivity;
import com.vvm.sh.util.constantes.Identificadores;

public class PreferenciasUtil {


    private static final String PRIMEIRA_UTILIZACAO = "primeira_utilizacao";
    private static final String VERSAO_APP = "versao_app";
    private static final String ID_UTILIZADOR = "id_utilizador";
    private static final String ID_TAREFA = "id_tarefa";
    private static final String ID_API = "id_api";
    private static final String AGENDA_EDITAVEL = "tarefa_ativa";
    private static final String DATA_VALIDADE_AUTENTICACAO = "validade_autenticacao";
    private static final String TOKEN = "token";
    private static final String ID_CONTAGEM_MAQUINA = "id_contagem_maquinas";

    private static final String PREFERENCIAS_UTILIZADOR [] = { ID_UTILIZADOR, ID_TAREFA, AGENDA_EDITAVEL, DATA_VALIDADE_AUTENTICACAO, TOKEN , ID_API };


    /**
     * Metodo que permite fixar os dados da primeira utilizacao da api
     * @param contexto
     * @param primeiraUtilizacao boolean que indica se é a primeira utilizacao da api
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
     * @param tarefa  a tarefa
     */
    public static void fixarTarefa(Context contexto, Tarefa tarefa){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt(ID_TAREFA, tarefa.idTarefa);
        editor.putInt(ID_API, tarefa.api);

        editor.commit();
    }


    /**
     * Metodo que permite fixar a contagem de maquinas
     * @param contexto
     * @param contagemTipoMaquina os dados da contagem
     */
    public static void fixarContagemMaquina(Context contexto, IContagemTipoMaquina contagemTipoMaquina){

        if(contagemTipoMaquina == null){
            return;
        }

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt(ID_CONTAGEM_MAQUINA, contagemTipoMaquina.contagem);

        editor.commit();
    }



    /**
     * Metodo que permite obter a contagem de maquinas
     * @param contexto
     * @return o numero a usar no equipamento a inserir
     */
    public static int obterContagemMaquina(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getInt(ID_CONTAGEM_MAQUINA, 0);
    }

    /**
     * Metodo que permite incrementar a contagem de maquinas
     * @param contexto
     */
    public static void incrementarContagemMaquina(Context contexto) {

        int id = obterContagemMaquina(contexto);
        IContagemTipoMaquina contagemTipoMaquina = new IContagemTipoMaquina();
        contagemTipoMaquina.contagem = (id + 1);

        fixarContagemMaquina(contexto, contagemTipoMaquina);
    }



    /**
     * Metodo que permite fixar a versao da api
     * @param contexto
     */
    public static void fixarVersao(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(VERSAO_APP, BuildConfig.VERSION_NAME);

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
     * Metodo que permite indetificar se é necessario realizar a apresentacao da versão da api
     * @param contexto
     */
    public static void realizarApresentacaoApp(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        String versao = preferencias.getString(VERSAO_APP, "0.0.0");

        if(BuildConfig.VERSION_NAME.equals(versao) == false){

            //--PreferenciasUtil.fixarVersao(contexto);
            Intent intent = new Intent(contexto, ApresentacaoActivity.class);
            contexto.startActivity(intent);
        }
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
     * Metodo que permite obter o id da api em curso
     * @param contexto
     * @return o identificador
     */
    public static int obterIdApi(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getInt(ID_API, 0);
    }


    /**
     * Metodo que permite obter o identificador do utilizador
     * @param contexto
     * @return o identificador do utilizador
     */
    public static String obterIdUtilizador(Context contexto) {

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getString(ID_UTILIZADOR, Identificadores.UTILIZADOR_INVALIDO);
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
     * Metodo que permite fixar a completude da agenda
     * @param contexto
     * @param ativo true caso a agenda esteja ativa para edicao ou false caso contrario
     */
    public static void fixarCompletudeAgenda(Context contexto, int ativo){

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = preferencias.edit();

        if(Identificadores.Sincronizacao.TRANCADO == ativo){
            editor.putBoolean(AGENDA_EDITAVEL, false);
        }
        else{
            editor.putBoolean(AGENDA_EDITAVEL, true);
        }

        editor.commit();
    }



    /**
     * Metodo que permite obter a completude da agenda atual
     * @param contexto
     * @return true caso a agenda esteja ativa para edição ou false caso contrário
     */
    public static boolean agendaEditavel(Context contexto) {

        if(BuildConfig.DEBUG){
            return true;
        }

        String pacote = contexto.getPackageName();

        SharedPreferences preferencias = contexto.getSharedPreferences(pacote, Context.MODE_PRIVATE);
        return preferencias.getBoolean(AGENDA_EDITAVEL, true);
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
