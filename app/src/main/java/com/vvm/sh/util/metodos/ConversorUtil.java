package com.vvm.sh.util.metodos;

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





    /**
     * Metodo que permite obter um vector com os valores NP, NR, NI descricao, NI id
     * @param nd
     * @param ne
     * @param nc
     * @return um vector [NP, NR, NI descricao, NI id]
     */
    public static String [] obterNP_NR_NI(String nd, String ne, String nc/*, ObjDados ni*/){

        String valores[] = new String[4];
        String np, nr;

        //TODO completar metodo

//        try{
//            int resultado = Integer.parseInt(nd) * Integer.parseInt(ne);
//            np = resultado + "";
//
//            resultado = Integer.parseInt(nc) * Integer.parseInt(np);
//            nr = resultado + "";
//
//            int index = 0;
//
//            for(int posicao = 0; posicao < ni.tamanho(); ++posicao){
//
//                String limite = ni.obterDetalhe(posicao);
//
//                if(resultado >= Integer.parseInt(limite.split("-")[0]) & resultado <= Integer.parseInt(limite.split("-")[1])){
//                    break;
//                }
//
//                ++index;
//            }
//
//            valores[0] = np;
//            valores[1] = nr;
//            valores[2] = ni.obterDescricao(index);
//            valores[3] = ni.obterIdentificador(index);
//
//        }
//        catch(NumberFormatException e){}

        return valores;
    }






}
