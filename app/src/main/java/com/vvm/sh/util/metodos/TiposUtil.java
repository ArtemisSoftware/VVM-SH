package com.vvm.sh.util.metodos;

import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TiposUtil {




    public static class MetodosTiposChecklist{

        public static String CHECKLISTS [] = {"Industria", "Restauração", "Comércio"};
        public static String ID_CHECKLISTS [] = {"1", "2", "3"};

        public static final String CHECKLIST = "GetCheckListNovo";
    }


    private static class MetodosTiposSA{

        public static final String CROSS_SELLING_PRODUTOS = "GetCrossSellingProdutos";
        public static final String CROSS_SELLING_DIMENSAO = "GetCrossSellingTpDimensao";
        public static final String CROSS_SELLING_TIPO = "GetCrossSellingTpTipo";
        public static final String TIPOS_ANOMALIA = "GetTiposAnomalia";
        public static final String TIPIFICACAO_OCORRENCIA = "GetTipificacoesOcorrencia_New";
        public static final String CURSOS = "getCoursesInfo";
    }


    private static class MetodosTiposSH{


        public static final String ATIVIDADES_PLANEAVEIS = "GetActivPlaneaveis";

        public static final String ATIVIDADES_RELATORIO_VISITA = "ObterActividadesRelatorioVisita";

        public static final String CATEGORIAS_PROFISSIONAIS = "GetCategoriasProfissionais";
        public static final String CHECKLIST = "GetCheckListNovo";
        public static final String CONCLUSAO_MEDIDAS_RECOMENDADAS = "GetConclusaoMedidasRecomendadas";
        public static final String CONDICOES_CLIMATERICAS = "GetCondicoesClimatericas";

        public static final String CROSS_SELLING_PRODUTOS = "GetCrossSellingProdutos";
        public static final String CROSS_SELLING_DIMENSAO = "GetCrossSellingTpDimensao";
        public static final String CROSS_SELLING_TIPO = "GetCrossSellingTpTipo";

        public static final String CURSOS = "getCoursesInfo";


        public static final String FRASES_APOIO = "Obter_FrasesApoio";

        public static final String MEDIDAS_ILUMINACAO_TERMICO = "GetMedidasIluminacaoTermico";
        public static final String MEDIDAS_PREVENCAO_RECOMENDADAS = "GetMedidasPrevencaoRecomendadas";
        public static final String MEDIDAS_PREVENCAO_ADOPTADAS = "GetMedidasPrevencaoAdoptadas";

        public static final String RISCOS = "GetRiscos";
        public static final String RISCOS_ESPECIFICOS = "GetRiscosEspecificos";

        public static final String TEMPLATE_AVALIACAO_RISCOS = "ObterTemplatesAVR";
        public static final String TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS = "ObterTemplatesAVR_Levantamentos";
        public static final String TEMPLATE_AVALIACAO_RISCOS_RISCOS = "ObterTemplatesAVR_Riscos";


        public static final String TIPIFICACAO_OCORRENCIA = "GetTipificacoesOcorrencia_New";
        public static final String TIPOS_ANOMALIA = "GetTiposAnomalia";
        public static final String TIPOS_AREA = "GetTiposArea";
        public static final String TIPOS_CHECKLIST = "GetTiposChecklistNovo";
        public static final String TIPOS_ILUMINACAO = "GetTiposIluminacao";
        public static final String TIPOS_MAQUINA = "GetTiposMaquina";
        public static final String TIPOS_NC = "GetTiposNC";
        public static final String TIPOS_ND = "GetTiposND";
        public static final String TIPOS_NE = "GetTiposNE";
        public static final String TIPOS_NI = "GetTiposNI";
        public static final String TIPOS_UTS = "GetTiposUT";
        public static final String TIPOS_VULNERABILIDADES =  "GetTiposVulnerabilidade";
        public static final String ILUMINANCIA = "GetIluminancia";
    }




    public static class MetodosTipos{


        public static final String ATIVIDADES_RELATORIO_VISITA = "Atividades_Relatorio_Visita";

        public static final String CROSS_SELLING_PRODUTOS = "CrossSelling_Produtos";
        public static final String CROSS_SELLING_DIMENSAO = "CrossSelling_Dimensao";
        public static final String CROSS_SELLING_TIPO = "CrossSelling_Tipo";
        public static final String ANOMALIAS = "Anomalias";
        public static final String TIPIFICACAO_OCORRENCIA = "Tipificacoes_Ocorrencia";

        
        public static final String CATEGORIAS_PROFISSIONAIS = "Categorias_Profissionais";
        public static final String CONDICOES_CLIMATERICAS = "Condicoes_Climatericas";
        public static final String CONCLUSAO_MEDIDAS_RECOMENDADAS = "Conclusao_Medidas_Recomendadas";
        public static final String CURSOS = "Cursos";

        public static final String FRASES_APOIO = "Frases_Apoio";

        public static final String MEDIDAS_ILUMINACAO_TERMICO = "Medidas_Iluminacao_Termico";
        public static final String MEDIDAS_PREVENCAO_RECOMENDADAS = "Medidas_Prevencao_Recomendadas";
        public static final String MEDIDAS_PREVENCAO_ADOPTADAS = "Medidas_Prevencao_Adoptadas";

        public static final String RISCOS = "Riscos";
        public static final String RISCOS_ESPECIFICOS = "Riscos_Especificos";

        public static final String TEMPLATE_AVALIACAO_RISCOS = "Template_Avaliação_Riscos";


        public static final String TIPOS_AREA = "Tipos_Area";
        public static final String TIPOS_ANOMALIAS = "Tipos_Anomalias";
        public static final String TIPOS_CHECKLIST = "Tipos_Checklist";
        public static final String TIPOS_ILUMINACAO = "Tipos_Iluminacao";
        public static final String TIPOS_NI = "Tipos_NI";
        public static final String TIPOS_ND = "Tipos_ND";
        public static final String TIPOS_NE = "Tipos_NE";
        public static final String TIPOS_NC = "Tipos_NC";
        public static final String TIPOS_MAQUINA = "Tipos_Maquina";
        public static final String TIPOS_UTS = "Tipos_Uts";
        public static final String TIPOS_VULNERABILIDADES =  "Tipos_Vulnerabilidade";

        public static final String ILUMINANCIA = "Iluminancia";






        public static class Checklist {

            public static final String CHECKLIST = "CheckList";
            public static final MetodoApi METODO_CHECKLIST = new MetodoApi(CHECKLIST, null, MetodosTiposChecklist.CHECKLIST);


            public static final MetodoApi TIPOS [] = new MetodoApi []{
                    METODO_CHECKLIST
            };
        }


        public static class AtividadesPlaneaveis {

            public static final String ATIVIDADES_PLANEAVEIS = "Actividades_Planeaveis";

            public static final MetodoApi METODO_ATIVIDADES_PLANEAVEIS = new MetodoApi(ATIVIDADES_PLANEAVEIS, null, MetodosTiposSH.ATIVIDADES_PLANEAVEIS);

            public static final MetodoApi TIPOS [] = new MetodoApi []{
                    METODO_ATIVIDADES_PLANEAVEIS
            };
        }


        public static class TemplateAvr {

            public static final String TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS = "Template_Avaliação_Riscos_Levantamentos";
            public static final String TEMPLATE_AVALIACAO_RISCOS_RISCOS = "Template_Avaliação_Riscos_Riscos";

            public static final MetodoApi METODO_TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS = new MetodoApi(TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS, null, MetodosTiposSH.TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS);
            public static final MetodoApi METODO_TEMPLATE_AVALIACAO_RISCOS_RISCOS = new MetodoApi(TEMPLATE_AVALIACAO_RISCOS_RISCOS, null, MetodosTiposSH.TEMPLATE_AVALIACAO_RISCOS_RISCOS);

            public static final MetodoApi TIPOS [] = new MetodoApi []{
                    METODO_TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS,
                    METODO_TEMPLATE_AVALIACAO_RISCOS_RISCOS
            };
        }


        protected static class Tipos {
            
            public static final MetodoApi METODO_ATIVIDADES_RELATORIO_VISITA = new MetodoApi(ATIVIDADES_RELATORIO_VISITA, null, MetodosTiposSH.ATIVIDADES_RELATORIO_VISITA);

            public static final MetodoApi METODO_CATEGORIAS_PROFISSIONAIS = new MetodoApi(CATEGORIAS_PROFISSIONAIS, null, MetodosTiposSH.CATEGORIAS_PROFISSIONAIS);


            public static final MetodoApi METODO_CROSS_SELLING_DIMENSAO = new MetodoApi(CROSS_SELLING_DIMENSAO, MetodosTiposSA.CROSS_SELLING_DIMENSAO, MetodosTiposSH.CROSS_SELLING_DIMENSAO);
            public static final MetodoApi METODO_CROSS_SELLING_PRODUTOS = new MetodoApi(CROSS_SELLING_PRODUTOS, MetodosTiposSA.CROSS_SELLING_PRODUTOS, MetodosTiposSH.CROSS_SELLING_PRODUTOS);
            public static final MetodoApi METODO_CROSS_SELLING_TIPO = new MetodoApi(CROSS_SELLING_TIPO, MetodosTiposSA.CROSS_SELLING_TIPO, MetodosTiposSH.CROSS_SELLING_TIPO);


            public static final MetodoApi METODO_CONDICOES_CLIMATERICAS = new MetodoApi(CONDICOES_CLIMATERICAS, null, MetodosTiposSH.CONDICOES_CLIMATERICAS);
            public static final MetodoApi METODO_CONCLUSAO_MEDIDAS_RECOMENDADAS = new MetodoApi(CONCLUSAO_MEDIDAS_RECOMENDADAS, null, MetodosTiposSH.CONCLUSAO_MEDIDAS_RECOMENDADAS);
            public static final MetodoApi METODO_CURSOS = new MetodoApi(CURSOS, MetodosTiposSA.CURSOS, MetodosTiposSH.CURSOS);

            public static final MetodoApi METODO_FRASES_APOIO = new MetodoApi(FRASES_APOIO, null, MetodosTiposSH.FRASES_APOIO);


            public static final MetodoApi METODO_ILUMINANCIA = new MetodoApi(ILUMINANCIA, null, MetodosTiposSH.ILUMINANCIA);



            public static final MetodoApi METODO_MEDIDAS_ILUMINACAO_TERMICO = new MetodoApi(MEDIDAS_ILUMINACAO_TERMICO, null, MetodosTiposSH.MEDIDAS_ILUMINACAO_TERMICO);
            public static final MetodoApi METODO_MEDIDAS_PREVENCAO_RECOMENDADAS = new MetodoApi(MEDIDAS_PREVENCAO_RECOMENDADAS, null, MetodosTiposSH.MEDIDAS_PREVENCAO_RECOMENDADAS);
            public static final MetodoApi METODO_MEDIDAS_PREVENCAO_ADOPTADAS = new MetodoApi(MEDIDAS_PREVENCAO_ADOPTADAS, null, MetodosTiposSH.MEDIDAS_PREVENCAO_ADOPTADAS);


            public static final MetodoApi METODO_RISCOS = new MetodoApi(RISCOS, null, MetodosTiposSH.RISCOS);
            public static final MetodoApi METODO_RISCOS_ESPECIFICOS = new MetodoApi(RISCOS_ESPECIFICOS, null, MetodosTiposSH.RISCOS_ESPECIFICOS);


            public static final MetodoApi METODO_TEMPLATE_AVALIACAO_RISCOS = new MetodoApi(TEMPLATE_AVALIACAO_RISCOS, null, MetodosTiposSH.TEMPLATE_AVALIACAO_RISCOS);
            
            public static final MetodoApi METODO_TIPOS_ANOMALIA = new MetodoApi(TIPOS_ANOMALIAS, MetodosTiposSA.TIPOS_ANOMALIA, MetodosTiposSH.TIPOS_ANOMALIA);
            public static final MetodoApi METODO_TIPIFICACAO_OCORRENCIA = new MetodoApi(TIPIFICACAO_OCORRENCIA, MetodosTiposSA.TIPIFICACAO_OCORRENCIA, MetodosTiposSH.TIPIFICACAO_OCORRENCIA);
            
            public static final MetodoApi METODO_TIPOS_AREA = new MetodoApi(TIPOS_AREA, null, MetodosTiposSH.TIPOS_AREA);
            public static final MetodoApi METODO_TIPOS_CHECKLIST = new MetodoApi(TIPOS_CHECKLIST, null, MetodosTiposSH.TIPOS_CHECKLIST);
            public static final MetodoApi METODO_TIPOS_ILUMINACAO = new MetodoApi(TIPOS_ILUMINACAO, null, MetodosTiposSH.TIPOS_ILUMINACAO);
            public static final MetodoApi METODO_TIPOS_NC = new MetodoApi(TIPOS_NC, null, MetodosTiposSH.TIPOS_NC);
            public static final MetodoApi METODO_TIPOS_ND = new MetodoApi(TIPOS_ND, null, MetodosTiposSH.TIPOS_ND);
            public static final MetodoApi METODO_TIPOS_NE = new MetodoApi(TIPOS_NE, null, MetodosTiposSH.TIPOS_NE);
            public static final MetodoApi METODO_TIPOS_NI = new MetodoApi(TIPOS_NI, null, MetodosTiposSH.TIPOS_NI);
            public static final MetodoApi METODO_TIPOS_MAQUINA = new MetodoApi(TIPOS_MAQUINA, null, MetodosTiposSH.TIPOS_MAQUINA);
            public static final MetodoApi METODO_TIPOS_UTS = new MetodoApi(TIPOS_UTS, null, MetodosTiposSH.TIPOS_UTS);
            public static final MetodoApi METODO_TIPOS_VULNERABILIDADES = new MetodoApi(TIPOS_VULNERABILIDADES, null, MetodosTiposSH.TIPOS_VULNERABILIDADES);




            public static final MetodoApi TIPOS [] = new MetodoApi []{

                    METODO_ATIVIDADES_RELATORIO_VISITA,

                    METODO_CATEGORIAS_PROFISSIONAIS,
                    METODO_CONDICOES_CLIMATERICAS,
                    METODO_CONCLUSAO_MEDIDAS_RECOMENDADAS,





                    /**/
                    METODO_CROSS_SELLING_PRODUTOS,
                    METODO_CROSS_SELLING_DIMENSAO,
                    METODO_CROSS_SELLING_TIPO,
                    METODO_CURSOS,




                    METODO_FRASES_APOIO,

                    METODO_ILUMINANCIA,

                    METODO_MEDIDAS_ILUMINACAO_TERMICO,
                    METODO_MEDIDAS_PREVENCAO_RECOMENDADAS,
                    METODO_MEDIDAS_PREVENCAO_ADOPTADAS,

                    METODO_RISCOS,
                    METODO_RISCOS_ESPECIFICOS,

                    METODO_TEMPLATE_AVALIACAO_RISCOS,





                    /**/
                    METODO_TIPOS_ANOMALIA,
                    METODO_TIPIFICACAO_OCORRENCIA,




                    
                    METODO_TIPOS_AREA,
                    METODO_TIPOS_CHECKLIST,
                    METODO_TIPOS_ILUMINACAO,
                    METODO_TIPOS_NC,
                    METODO_TIPOS_ND,
                    METODO_TIPOS_NE,
                    METODO_TIPOS_NI,
                    METODO_TIPOS_MAQUINA,
                    METODO_TIPOS_UTS,
                    METODO_TIPOS_VULNERABILIDADES
            };
        }
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

        for (MetodoApi item : MetodosTipos.Tipos.TIPOS) {

            try {
                if (item.registado(descricao) == true) {
                    return item;
                }
            }
            catch (TipoInexistenteException e){}
        }

        for (MetodoApi item : MetodosTipos.Checklist.TIPOS) {

            try {
                if (item.registado(descricao) == true) {
                    return item;
                }
            }
            catch (TipoInexistenteException e){}
        }

        for (MetodoApi item : MetodosTipos.TemplateAvr.TIPOS) {

            try {
                if (item.registado(descricao) == true) {
                    return item;
                }
            }
            catch (TipoInexistenteException e){}
        }


        for (MetodoApi item : MetodosTipos.AtividadesPlaneaveis.TIPOS) {

            try {
                if (item.registado(descricao) == true) {
                    return item;
                }
            }
            catch (TipoInexistenteException e){}
        }


        throw new TipoInexistenteException(descricao);

    }



    /**
     * Metodo que permite obter os metodos associados a tipos de uma api
     * @return uma lista de metodos para tipos
     */
    public static MetodoApi[] obterMetodos() throws TipoInexistenteException {

        for (MetodoApi item : MetodosTipos.Tipos.TIPOS) {

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

        return MetodosTipos.Tipos.TIPOS;
    }


    /**
     * Metodo que permite obter a descricao da checklist
     * @param id o identificador da checklist
     * @return uma descricao
     * @throws TipoInexistenteException
     */
    public static String obterDescricaoChecklist(int id) throws TipoInexistenteException{

        String descricao = null;

        for(int index = 0; index < MetodosTiposChecklist.ID_CHECKLISTS.length; ++index){

            if(MetodosTiposChecklist.ID_CHECKLISTS[index].equals(id + "") == true){
                descricao = MetodosTiposChecklist.CHECKLISTS[index];
            }
        }

        if(descricao == null){
            throw new TipoInexistenteException("Checklist inexistente");
        }

        return descricao;
    }


    public static List<MetodoApi>  fixarSeloTemporal(List<Atualizacao> atualizacoes){
        
        List<MetodoApi> registos = new ArrayList<>();

        for (Atualizacao item : atualizacoes) {

            try {

                MetodoApi registo = obterMetodos(item.descricao);
                registo.seloTemporal = item.seloTemporal;
                registos.add(registo);

            } catch (TipoInexistenteException e) {
                e.printStackTrace();
            }

        }
        
        if(registos.size() == 0){
            List<MetodoApi> tipos = new LinkedList(Arrays.asList(MetodosTipos.Tipos.TIPOS));
            return tipos;
        }

        return registos;
    }


    public static class MetodoApi{

        public String descricao, sa, sht;
        public String seloTemporal = "";

        public MetodoApi(String descricao, String sa, String sht) {
            this.descricao = descricao;
            this.sht = sht;
            this.sa = sa;
        }

        public boolean registado(String metodo) throws TipoInexistenteException{

            if(descricao.equals(metodo) == true){
                return true;
            }

            if(sht != null){

                if(sht.equals(metodo) == true){
                    return true;
                }
            }

            if(sa != null){

                if(sa.equals(metodo) == true){
                    return true;
                }
            }

            throw new TipoInexistenteException(descricao);
        }
    }

}
