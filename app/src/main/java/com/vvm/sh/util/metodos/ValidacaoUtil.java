package com.vvm.sh.util.metodos;

public class ValidacaoUtil {


    /**
     * Metodo que valida um nif
     * @param nif nif a ser validado
     * @return true caso o nif seja válido e false caso contrário
     */
    public static boolean validarNif(String nif){

        char[] nifTeste = nif.toCharArray();

        if(nifTeste.length != 9)
            return false;

        if(('1' == nifTeste[0]) == true  || ('2' == nifTeste[0]) == true  || ('3' == nifTeste[0]) == true || ('5' == nifTeste[0]) == true ||
                ('6' == nifTeste[0]) == true || ('7' == nifTeste[0]) == true || ('8' == nifTeste[0]) == true ||
                ('9' == nifTeste[0]) == true){

            int total = 0;
            for (int i = 0; i < 8; i++){
                total += Integer.parseInt(nifTeste[i] + "") * (9 - i);
            }

            int check = 11 - (total % 11);

            if (check >= 10){
                check = 0;
            }

            if (check == Integer.parseInt(nifTeste[8] + "")){
                return true;
            }
        }

        return false;
    }

}
