package com.vvm.sh.util.metodos;

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
    }


    private static class MetodosTiposSH{

        public static final String CATEGORIAS_PROFISSIONAIS = "GetCategoriasProfissionais";
        public static final String CONCLUSAO_MEDIDAS_RECOMENDADAS = "GetConclusaoMedidasRecomendadas";
        public static final String CONDICOES_CLIMATERICAS = "GetCondicoesClimatericas";
        public static final String CURSOS = "getCoursesInfo";

        public static final String TIPOS_ILUMINACAO = "GetTiposIluminacao";
        public static final String TIPOS_AREA = "GetTiposArea";
        public static final String ILUMINANCIA = "GetIluminancia";


    }



    public static class MetodosTipos{

        public static final String CROSS_SELLING_PRODUTOS = "CrossSelling_Produtos";
        public static final String CROSS_SELLING_DIMENSAO = "CrossSelling_Dimensao";
        public static final String CROSS_SELLING_TIPO = "CrossSelling_Tipo";
        public static final String ANOMALIAS = "Anomalias";
        public static final String TIPIFICACAO_OCORRENCIA = "Tipificacoes_Ocorrencia";


        public static final String CATEGORIAS_PROFISSIONAIS = "Categorias_Profissionais";
        public static final String CONDICOES_CLIMATERICAS = "Condicoes_Climatericas";
        public static final String CONCLUSAO_MEDIDAS_RECOMENDADAS = "Conclusao_Medidas_Recomendadas";
        public static final String CURSOS = "Cursos";

        public static final String TIPOS_AREA = "Tipos_Area";
        public static final String TIPOS_ILUMINACAO = "Tipos_Iluminacao";
        public static final String ILUMINANCIA = "Iluminancia";

        public static final MetodoApi METODO_CATEGORIAS_PROFISSIONAIS = new MetodoApi(CATEGORIAS_PROFISSIONAIS, null, MetodosTiposSH.CATEGORIAS_PROFISSIONAIS);
        public static final MetodoApi METODO_CONDICOES_CLIMATERICAS = new MetodoApi(CONDICOES_CLIMATERICAS, null, MetodosTiposSH.CONDICOES_CLIMATERICAS);
        public static final MetodoApi METODO_CONCLUSAO_MEDIDAS_RECOMENDADAS = new MetodoApi(CONCLUSAO_MEDIDAS_RECOMENDADAS, null, MetodosTiposSH.CONCLUSAO_MEDIDAS_RECOMENDADAS);
        public static final MetodoApi METODO_CURSOS = new MetodoApi(CURSOS, MetodosTiposSA.CURSOS, MetodosTiposSH.CURSOS);
        public static final MetodoApi METODO_ILUMINANCIA = new MetodoApi(ILUMINANCIA, null, MetodosTiposSH.ILUMINANCIA);
        public static final MetodoApi METODO_TIPOS_ILUMINACAO = new MetodoApi(TIPOS_ILUMINACAO, null, MetodosTiposSH.TIPOS_ILUMINACAO);
        public static final MetodoApi METODO_TIPOS_AREA = new MetodoApi(TIPOS_AREA, null, MetodosTiposSH.TIPOS_AREA);
//
//public static final MetodoApi METODO_ = new MetodoApi(MetodosTiposSA., MetodosTiposSH.);
//public static final MetodoApi METODO_ = new MetodoApi(MetodosTiposSA., MetodosTiposSH.);
//public static final MetodoApi METODO_ = new MetodoApi(MetodosTiposSA., MetodosTiposSH.);



        public static final MetodoApi TIPOS [] = new MetodoApi []{


                METODO_CATEGORIAS_PROFISSIONAIS,
                METODO_CONDICOES_CLIMATERICAS,
                METODO_CONCLUSAO_MEDIDAS_RECOMENDADAS,
                METODO_CURSOS,

                METODO_ILUMINANCIA,
                METODO_TIPOS_AREA,
                METODO_TIPOS_ILUMINACAO
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
                metodo = obterMetodos(metodo).descricao;

            }
        }
        catch(IndexOutOfBoundsException e){}

        return metodo;
    }



    /**
     * Metodo que permite obter os metodos associados a um tipo
     * @param descricao a descricao do tipo como metodo do ws
     * @return um metodo
     * @throws TipoInexistenteException
     */
    public static MetodoApi obterMetodos(String descricao) throws TipoInexistenteException {

        MetodoApi metodo = null;


        switch (descricao){

            case MetodosTipos.CROSS_SELLING_PRODUTOS:

                metodo.sa = MetodosTiposSA.CROSS_SELLING_PRODUTOS;
                break;

            case MetodosTipos.CROSS_SELLING_DIMENSAO:

                metodo.sa = MetodosTiposSA.CROSS_SELLING_DIMENSAO;
                break;

            case MetodosTipos.CROSS_SELLING_TIPO:

                metodo.sa = MetodosTiposSA.CROSS_SELLING_TIPO;
                break;

            case MetodosTipos.ANOMALIAS:

                metodo.sa = MetodosTiposSA.TIPOS_ANOMALIA;
                break;

            case MetodosTipos.TIPIFICACAO_OCORRENCIA:

                metodo.sa = MetodosTiposSA.TIPIFICACAO_OCORRENCIA;
                break;






            case MetodosTiposSH.CATEGORIAS_PROFISSIONAIS:
            case MetodosTipos.CATEGORIAS_PROFISSIONAIS:

                metodo = MetodosTipos.METODO_CATEGORIAS_PROFISSIONAIS;
                break;


            case MetodosTiposSH.CONDICOES_CLIMATERICAS:
            case MetodosTipos.CONDICOES_CLIMATERICAS:

                metodo = MetodosTipos.METODO_CONDICOES_CLIMATERICAS;
                break;

            case MetodosTiposSH.CONCLUSAO_MEDIDAS_RECOMENDADAS:
            case MetodosTipos.CONCLUSAO_MEDIDAS_RECOMENDADAS:

                metodo = MetodosTipos.METODO_CONCLUSAO_MEDIDAS_RECOMENDADAS;
                break;

            case MetodosTiposSH.CURSOS:
            case MetodosTipos.CURSOS:

                metodo = MetodosTipos.METODO_CURSOS;
                break;


            case MetodosTiposSH.ILUMINANCIA:
            case MetodosTipos.ILUMINANCIA:

                metodo = MetodosTipos.METODO_ILUMINANCIA;
                break;


            case MetodosTiposSH.TIPOS_AREA:
            case MetodosTipos.TIPOS_AREA:

                metodo = MetodosTipos.METODO_TIPOS_AREA;
                break;


            case MetodosTiposSH.TIPOS_ILUMINACAO:
            case MetodosTipos.TIPOS_ILUMINACAO:

                metodo = MetodosTipos.METODO_TIPOS_ILUMINACAO;
                break;

            default:
                throw new TipoInexistenteException(descricao);

        }


        return metodo;
    }




    /**
     * Metodo que permite obter os metodos associados a tipos de uma api
     * @return uma lista de metodos para tipos
     */
    public static MetodoApi[] obterMetodos() throws TipoInexistenteException {

        for (MetodoApi item : MetodosTipos.TIPOS) {

            if(item.descricao == null){
                throw new TipoInexistenteException();
            }

            MetodoApi metodo = obterMetodos(item.descricao);

            if(metodo.sht == null & metodo.sa == null){
                throw new TipoInexistenteException();
            }

            if(metodo.sht == null){
                if(metodo.sa.equals("") == true){
                    throw new TipoInexistenteException();
                }
            }


            if(metodo.sa == null){
                if(metodo.sht.equals("") == true){
                    throw new TipoInexistenteException();
                }
            }
        }

        return MetodosTipos.TIPOS;
    }



    public static class MetodoApi{

        public String descricao, sa, sht;

        public MetodoApi() {
            this.descricao = null;
            this.sht = null;
            this.sa = null;
        }

        public MetodoApi(String descricao, String sa, String sht) {
            this.descricao = descricao;
            this.sht = sht;
            this.sa = sa;
        }


    }

}
