package com.vvm.sh.util.metodos;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;

import java.util.List;

public class TiposUtil {




    private static class MetodosTiposSA{

        public static final String CROSS_SELLING_PRODUTOS = "GetCrossSellingProdutos";
        public static final String CROSS_SELLING_DIMENSAO = "GetCrossSellingTpDimensao";
        public static final String CROSS_SELLING_TIPO = "GetCrossSellingTpTipo";
        public static final String TIPOS_ANOMALIA = "GetTiposAnomalia";
        public static final String TIPIFICACAO_OCORRENCIA = "GetTipificacoesOcorrencia_New";
        public static final String CURSOS = "getCoursesInfo";

        public static final String TIPOS [] = new String []{

                CROSS_SELLING_PRODUTOS, CROSS_SELLING_DIMENSAO, CROSS_SELLING_TIPO,
                TIPOS_ANOMALIA,
                TIPIFICACAO_OCORRENCIA,
                CURSOS
        };
    }



    public static class MetodosTipos{

        public static final String CROSS_SELLING_PRODUTOS = "CrossSelling_Produtos";
        public static final String CROSS_SELLING_DIMENSAO = "CrossSelling_Dimensao";
        public static final String CROSS_SELLING_TIPO = "CrossSelling_Tipo";
        public static final String ANOMALIAS = "Anomalias";
        public static final String TIPIFICACAO_OCORRENCIA = "Tipificacoes_Ocorrencia";
        public static final String CURSOS = "Cursos";

        public static final String TIPOS [] = new String []{

                CROSS_SELLING_PRODUTOS, CROSS_SELLING_DIMENSAO, CROSS_SELLING_TIPO,
                ANOMALIAS,
                TIPIFICACAO_OCORRENCIA,
                CURSOS
        };
    }


    /**
     * Metodo que permite obter o metodo do tipo associado caso exista ou apenas o metodo
     * @param pathSegments
     * @param header os dados do header
     * @return um metodo
     * @throws TipoInexistenteException
     */
    public static String obterMetodo(List<String> pathSegments, String header) throws TipoInexistenteException{

        String metodo = "";

        try {

            if(header == null){
                metodo  = pathSegments.get(2);
            }
            else if(header.equals("tipo") == true){

                metodo  = pathSegments.get(2);


                switch (metodo){

                    case MetodosTiposSA.CROSS_SELLING_PRODUTOS:

                        metodo = MetodosTipos.CROSS_SELLING_PRODUTOS;
                        break;

                    case MetodosTiposSA.CROSS_SELLING_DIMENSAO:

                        metodo = MetodosTipos.CROSS_SELLING_DIMENSAO;
                        break;

                    case MetodosTiposSA.CROSS_SELLING_TIPO:

                        metodo = MetodosTipos.CROSS_SELLING_TIPO;
                        break;

                    case MetodosTiposSA.TIPOS_ANOMALIA:

                        metodo = MetodosTipos.ANOMALIAS;
                        break;

                    case MetodosTiposSA.TIPIFICACAO_OCORRENCIA:

                        metodo = MetodosTipos.TIPIFICACAO_OCORRENCIA;
                        break;

                    case MetodosTiposSA.CURSOS:

                        metodo = MetodosTipos.CURSOS;
                        break;

                    default:
                        throw new TipoInexistenteException(metodo);

                }
            }
        }
        catch(IndexOutOfBoundsException e){}

        return metodo;
    }


    /**
     * Metodo que permite obter os metodos associados a um tipo
     * @param descricao a descricao do tipo
     * @return uma lista de metodos
     */
    public static String [] obterMetodos(String descricao){

        String metodos [] = new String []{};


        switch (descricao){

            case MetodosTipos.CROSS_SELLING_PRODUTOS:

                metodos = new String [] { MetodosTiposSA.CROSS_SELLING_PRODUTOS };
                break;

            case MetodosTipos.CROSS_SELLING_DIMENSAO:

                metodos = new String [] { MetodosTiposSA.CROSS_SELLING_DIMENSAO };
                break;

            case MetodosTipos.CROSS_SELLING_TIPO:

                metodos = new String [] { MetodosTiposSA.CROSS_SELLING_TIPO };
                break;

            case MetodosTipos.ANOMALIAS:

                metodos = new String [] { MetodosTiposSA.TIPOS_ANOMALIA };
                break;

            case MetodosTipos.TIPIFICACAO_OCORRENCIA:

                metodos = new String [] { MetodosTiposSA.TIPIFICACAO_OCORRENCIA };
                break;

            case MetodosTipos.CURSOS:

                metodos = new String [] { MetodosTiposSA.CURSOS };
                break;

            default:
                //throw new TipoInexistenteException(metodo);
                break;
        }


        return metodos;
    }



    /**
     * Metodo que permite obter os metodos associados a tipos de uma api
     * @param api o identificador da api
     * @return uma lista de metodos para tipos
     */
    public static String[] obterMetodos(int api){


        if(api == Identificadores.App.APP_SA){
            return MetodosTiposSA.TIPOS;
        }
        else{
            //TODO: POR aqui lista de SHT
            return new String []{};
        }

    }

//
//    public static String[] obterMetodos(){
//
//
//        String TIPOS [] = MetodosTiposSA.TIPOS
//
//        if(api == Identificadores.App.APP_SA){
//            return MetodosTiposSA.TIPOS;
//        }
//        else{
//            //TODO: POR aqui lista de SHT
//            return new String []{};
//        }
//
//    }


}
