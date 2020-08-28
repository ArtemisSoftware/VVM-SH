package com.vvm.sh.util.metodos;

import android.app.Activity;
import android.os.Environment;

import com.vvm.sh.baseDados.BaseDadosContantes;
import com.vvm.sh.util.constantes.Sintaxe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class BaseDadosUtil {


    /**
     * Metodo que permite exportar a base de dados da aplicação
     * @param atividade
     */
    public static void exportarBaseDados(Activity atividade) {

        String nomeCopia = BaseDadosContantes.NOME + "__" + PreferenciasUtil.obterIdUtilizador(atividade) + "_" + DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY) + "_" + BaseDadosContantes.VERSAO + BaseDadosContantes.EXTENSAO;

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

                    MensagensUtil.snack(atividade, Sintaxe.Alertas.SUCESSO_EXPORTAR_BD + caminhoBdCopia);
                }
            }
        }
        catch (Exception e) {
            MensagensUtil.snack(atividade, Sintaxe.Alertas.ERRO_EXPORTAR_BD + e.getMessage());
        }

    }



    /**
     * Metodo que permite importar uma nova base de dados de forma a substituir a atual
     * @param atividade
     * @param nomeCopia o nome da base de dados a copiar
     */
    public static void importarBaseDados(Activity atividade, String nomeCopia){

        File novaBd = new File(Environment.getExternalStorageDirectory(), DiretoriasUtil.BASE_DADOS + File.separator + nomeCopia);
        File bdAtual = new File("/data/data/" + atividade.getPackageName() + "//databases//" + BaseDadosContantes.NOME);

        if (novaBd.exists()) {

            FileChannel canalPartida = null;
            FileChannel canalChegada = null;

            try {
                try {
                    canalPartida = new FileInputStream(novaBd).getChannel();
                    canalChegada = new FileOutputStream (bdAtual).getChannel();
                    canalPartida.transferTo(0, canalPartida.size(), canalChegada);

                    MensagensUtil.snack(atividade, Sintaxe.Alertas.SUCESSO_IMPORTAR_BD);
                }
                finally {
                    try {
                        if (canalPartida != null) {
                            canalPartida.close();
                        }
                    } finally {
                        if (canalChegada != null) {
                            canalChegada.close();
                        }
                    }
                }
            }
            catch (Exception e) {
                MensagensUtil.snack(atividade, Sintaxe.Alertas.ERRO_IMPORTAR_BD + e.getMessage());
            }
        }
    }



    /**
     * Metodo que permite obter uma lista de ficheiros de base de dados existentes
     * @return uma lista de ficheiros de base de dados existentes
     */
    public static List<String> obterFicheirosBds(){

        List<String> ficheirosBd = new ArrayList<>();

        File directoria = new File(Environment.getExternalStorageDirectory(), DiretoriasUtil.BASE_DADOS);
        File ficheiros[] = directoria.listFiles();

        for (File ficheiro : ficheiros) {
            ficheirosBd.add(ficheiro.getName());
        }

        return ficheirosBd;
    }

}
