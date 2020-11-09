package com.vvm.sh.util.metodos;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.Sintaxe;

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
}
