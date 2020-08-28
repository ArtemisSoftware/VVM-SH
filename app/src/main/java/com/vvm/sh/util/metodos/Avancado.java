package com.vvm.sh.util.metodos;

import android.app.Activity;
import android.os.Environment;

import com.google.android.material.snackbar.Snackbar;
import com.vvm.sh.baseDados.BaseDadosContantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Avancado {


    /**
     * Metodo que permite exportar a base de dados da aplicação
     * @param atividade
     */
    public static void exportarBaseDados(Activity atividade) {

        String nomeCopia = BaseDadosContantes.NOME + "__" + Preferencias.obterIdUtilizador(atividade) + "_" + DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY) + "_" + BaseDadosContantes.VERSAO + BaseDadosContantes.EXTENSAO;

        try {
            File storageDirectory = Environment.getExternalStorageDirectory();

            if (storageDirectory.canWrite()) {

                String caminhoBdAtual = "/data/data/" + atividade.getPackageName() + "//databases//" + BaseDadosContantes.NOME;
                File bdAtual = new File(caminhoBdAtual);

                String caminhoBdCopia = DiretoriasUtil.BASE_DADOS + File.separator + nomeCopia;
                File bdCopia = new File(storageDirectory, caminhoBdCopia);
                boolean copiaCriada = bdCopia.createNewFile();

                if (bdAtual.exists() & copiaCriada) {
                    FileChannel origem = new FileInputStream(bdAtual).getChannel();
                    FileChannel destino = new FileOutputStream(bdCopia).getChannel();
                    destino.transferFrom(origem, 0, origem.size());
                    origem.close();
                    destino.close();

                    MensagensUtil.snack(atividade, "Exportar bd - " + caminhoBdCopia);
                }
            }
        }
        catch (Exception e) {
            MensagensUtil.snack(atividade, "Exportar bd - " +  e.toString());
        }

    }


    /**
     * MEtodo que permite importar uma nova base de dados de forma a substituir a atual
     */
    /*
    public static void importarBaseDados(Context contexto, String nomeCopia){

        GestorBaseDados gestor = new GestorBaseDados(contexto);
        gestor.apagarDadosBaseDados();

        String mensagem = "Importar bd - ";
        String novaBd = Environment.getExternalStorageDirectory() + "/" + AppConfigIF.DIRETORIA_BASE_DADOS_ + "/" + nomeCopia;

        File dados = Environment.getDataDirectory();
        String caminhoBdAtual = "//dados//" + contexto.getPackageName() + "//databases//" + AtualizacaoIF.BASE_DADOS_NOME + "";
        File bdAtual = new File(dados, caminhoBdAtual);

        try {
            gestor.importarBaseDados(novaBd, bdAtual);

            if(AppConfigIF.VERSAO_TESTE){
                mensagem += novaBd.toString() + " <" + novaBd.length() + ">";
            }
            else{
                mensagem +=  nomeCopia + " <" + novaBd.length() + ">";
            }
        }
        catch (IOException e) {
            mensagem += e.toString();
        }

        MetodosMensagens.gerarToast(contexto, mensagem);
    }
*/

}
