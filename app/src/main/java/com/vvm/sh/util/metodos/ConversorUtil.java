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

        return Boolean.parseBoolean(valor);
    }

}
