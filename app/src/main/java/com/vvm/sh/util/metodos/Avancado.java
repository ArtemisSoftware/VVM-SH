package com.vvm.sh.util.metodos;

import android.content.Context;
import android.os.Environment;

import com.vvm.sh.util.constantes.BaseDados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Avancado {


    /**
     * Metodo que permite exportar a base de dados da aplicação
     */
    public static void exportarBaseDados(Context contexto) {

        String mensagem = "Exportar bd - ";

        String nomeCopia = Preferencias.obterIdUtilizador(contexto) + "_" + DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY) + "_" + BaseDados.VERSAO;

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {

                String caminhoBdAtual = "//dados//" + contexto.getPackageName() + "//databases//" + BaseDados.NOME + "";
                String caminhoBdCopia = DiretoriasUtil.BASE_DADOS + "/" + BaseDados.NOME + "__" + nomeCopia + BaseDados.EXTENSAO;
                File bdAtual = new File(data, caminhoBdAtual);
                File bdCopia = new File(sd, caminhoBdCopia);

                if (bdAtual.exists()) {
                    FileChannel origem = new FileInputStream(bdAtual).getChannel();
                    FileChannel destino = new FileOutputStream(bdCopia).getChannel();
                    destino.transferFrom(origem, 0, origem.size());
                    origem.close();
                    destino.close();

                    mensagem += BaseDados.NOME + "__" + nomeCopia;
                }
            }
        }
        catch (Exception e) {
            mensagem += e.toString();
        }

        //--MetodosDialogo.gerarToast(contexto, mensagem);
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
