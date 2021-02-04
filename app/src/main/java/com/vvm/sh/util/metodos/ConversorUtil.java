package com.vvm.sh.util.metodos;

import android.util.Base64;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.Sintaxe;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ConversorUtil {

    /**
     * Metodo que permite converter um inteiro para booleano
     * @param valor o valor inteiro a converter
     * @return true caso seja um valor igual ou superior a 1 ou false caso contrario
     */
    public static boolean converter_Integer_Para_Boolean(int valor){

        try{

            return (valor != 0);

        }
        catch(NullPointerException e){
            return false;
        }
    }


    /**
     * Metodo que permite converter um boolean para um inteiro
     * @param valor
     * @return
     */
    public static int converter_Boolean_Para_Integer(boolean valor){
        return Boolean.compare(valor, false);
    }


    public static boolean converter_String_Para_Boolean(String valor){


        if(valor.equals("0") == true || valor.equals("1") == true){
            return converter_Integer_Para_Boolean(Integer.parseInt(valor));
        }

        return Boolean.parseBoolean(valor);
    }

    public static int converter_long_Para_int(long valor) {

        Long registo = new Long(valor);
        return registo.intValue();
    }




    /**
     * Metodo que permite obter um vector com os valores NP, NR, NI descricao, NI id
     * @param nd
     * @param ne
     * @param nc
     * @param ni
     * @return  um vector [NP, NR, NI descricao, NI id]
     */
    public static String [] obterNP_NR_NI(Tipo nd, Tipo ne, Tipo nc, List<Tipo> ni){

        String valores[] = new String[4];
        int np, nr;

        try{

            np = Integer.parseInt(nd.obterDescricao()) * Integer.parseInt(ne.obterDescricao());
            nr = Integer.parseInt(nc.obterDescricao()) * np;


            int index = 0;

            for(int posicao = 0; posicao < ni.size(); ++posicao){

                String limite = ni.get(posicao).detalhe;

                if(nr >= Integer.parseInt(limite.split("-")[0]) & nr <= Integer.parseInt(limite.split("-")[1])){
                    break;
                }

                ++index;
            }


            valores[0] = np + "";
            valores[1] = nr + "";
            valores[2] =  ni.get(index).descricao;
            valores[3] = ni.get(index).id + "";


        }
        catch(NumberFormatException e){}

        return valores;
    }



    public static String obterNI(String nd, String ne, String nc, List<Tipo> ni){

        String valor = "";
        int np, nr;

        try{

            np = Integer.parseInt(nd) * Integer.parseInt(ne);
            nr = Integer.parseInt(nc) * np;

            int index = 0;

            for(int posicao = 0; posicao < ni.size(); ++posicao){

                String limite = ni.get(posicao).detalhe;

                if(nr >= Integer.parseInt(limite.split("-")[0]) & nr <= Integer.parseInt(limite.split("-")[1])){
                    break;
                }

                ++index;
            }

            valor =  ni.get(index).id + "";
        }
        catch(NumberFormatException e){}

        return valor;
    }







    public static String converterGenero(int sexo) {

        if(sexo == 1){
            return Sintaxe.Codigos.MASCULINO;
        }
        else{
            return  Sintaxe.Codigos.FEMININO;
        }
    }


    public static String convertPdf__StringBase64(String caminhoPdf) throws IOException {

        File file = new File(caminhoPdf);

        byte[] bytes = converterFicheiro__Base64(file);
        String base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
        return base64;
    }


    public static byte[] converterFicheiro__Base64(File file) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data=new byte[(int) file.length()];

        BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
        bufferedInputStream.read(data,0,data.length);

        return data;
    }



}
