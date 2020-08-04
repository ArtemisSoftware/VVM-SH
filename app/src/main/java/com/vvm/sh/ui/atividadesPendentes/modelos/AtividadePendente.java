package com.vvm.sh.ui.atividadesPendentes.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "atividadesPendentes",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class AtividadePendente {


    public final static int RELATORIO_FORMACAO = 1;



    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "dataProgramada")
    public Date dataProgramada;

    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;

    @NonNull
    @ColumnInfo(name = "servId")
    public String servId;

    @NonNull
    @ColumnInfo(name = "formacao")
    public boolean formacao;




    @Ignore
    public AtividadePendente() {
    }


    public AtividadePendente(int idTarefa, @NonNull Date dataProgramada, @NonNull String descricao, @NonNull String servId, boolean formacao) {
        this.idTarefa = idTarefa;
        this.dataProgramada = dataProgramada;
        this.descricao = descricao;
        this.servId = servId;
        this.formacao = /*formacao*/true; //TODO: a true para teste
    }


    /**
     * Metodo que indica se existe um relatorio associado à atividade
     * @return true caso exista ou false caso contrario
     */
    public boolean existeRelatorio(){

        if(formacao == true){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Metodo que devolve a descricao do relatorio
     * @return uma descricao
     */
    public String obterRelatorio(){

        if(formacao == true){
            return Sintaxe.Palavras.FORMACAO;
        }
        else{
            return "";
        }
    }


    /**
     * Metodo que permite obter o identificador do relatorio
     * @return o identificador do relatorio
     */
    public int obterIdRelatorio(){


        if(formacao == true){
            return RELATORIO_FORMACAO;
        }
        else{
            return Identificadores.Estados.SEM_RELATORIO;
        }
    }







//    public static final int SEM_RELATORIO = -1;
//
//    private String data, anuidade, tempoExecucao, observacoes, dataExecucao, idAnomalia;
//
//    private String relatorio, checklistAVR;
//    private int idRelatorio, imagemRelatorio, imagemEstado, estado;
//
//
//
//    //private HashMap<Integer, Integer> avaliacoes;
//
//    public final int SECCAO_LEVANTAMENTO_RISCOS = 0, SECCAO_CHECKLIST = 1, SECCAO_PROCESSO_PRODUTIVO = 2,
//            SECCAO_VERIFICACAO_EQUIPAMENTOS = 3, SECCAO_VULNERABILIDADE = 4, SECCAO_CAPA_RELATORIO = 5,
//            SECCAO_PLANO_ACAO = 6;
//
//    public static final int RELATORIO = 1, AVALIACAO_RISCOS = 2;
//
//
//    private String idRelatorioAmbiental;
//
//    private boolean validacao;
//
//
//    public AtividadePendente(int id, String descricao, String data, String anuidade,
//                             int idRelatorio, String relatorio/*, String checklistAVR,
//                             int estado, String tempoExecucao, String dataExecucao, String idAnomalia, String observacoes,
//                             int validadeRelatorio, int validadeProcessoProdutivo, int validadeTrabalhadoresVulneraveis, int validadeEquipamentos,
//                             int validadePlanoAcao, int validadeLevantamentosRisco, int validadeChecklist, int validadeCapaRelatorio, String idRelatorioAmbiental*/) {
//        super(id, descricao);
//
//        this.data = data;
//        this.anuidade = anuidade;
//
//        this.idRelatorio = idRelatorio;
//        this.relatorio = relatorio;
//
//        //this.checklistAVR = checklistAVR;
//
//        //gerarInformacaoEstado(estado);
//        //imagemRelatorio = obterImagem(validadeRelatorio);
//
//        //gerarValidadeAtividade(idRelatorio, validadeRelatorio);
//
//        //gerarInformacaoAvaliacaoRisco(validadeProcessoProdutivo,validadeEquipamentos,validadeTrabalhadoresVulneraveis,validadeChecklist,validadePlanoAcao,validadeLevantamentosRisco, validadeCapaRelatorio);
//
//        //checkList(checklist, completudeChecklist, descricaoChecklist);
//
//        /*
//        this.estado = estado; //
//        this.tempoExecucao = tempoExecucao;
//        this.observacoes = observacoes; //
//        this.dataExecucao = dataExecucao;
//        this.idAnomalia = idAnomalia;//
//
//        this.idRelatorioAmbiental = idRelatorioAmbiental;
//        */
//    }
//
//
//    /**
//     * Metodo que permite obter a dados da atividade
//     * @return uma dados (dd-mm-yyyy)
//     */
//    public String obterData(){
//        return DatasUtil.converterData(data, DatasUtil.FORMATO_DD_MM_YYYY);
//    }
//
//
//    /**
//     * Metodo que permite obter a anuidade
//     * @return a anuidade
//     */
//    public String obterAnuidade(){
//        return anuidade;
//    }
//
//
//
//    /**
//     * Metodo que devolve o identificador do tipo do relatorio
//     * @return o identificador do tipo do relatorio
//     */
//    public int obterIdRelatorio(){
//        return idRelatorio;
//    }
//
//
//

//
//
//
//
//
//
//
//
//    /**
//     * Metodo que indica a validade da atividade de forma a esta poder ser marcada como executada
//     * @return true caso seja valida ou false caso contrario
//     */
//    public boolean obterValidade(){
//        return validacao;
//    }
//
//
//
//    /**
//     * Metodo que gera a validade da atividade
//     * @param idRelatorio o identificador do relatoriop
//     * @param validadeRelatorio true caso o relatorio seja valido ou false caso contrario
//     */
//    private void gerarValidadeAtividade(int idRelatorio, int validadeRelatorio){
///*
//        validacao = false;
//
//        if(idRelatorio == AppIF.SEM_REGISTO){
//            validacao = true;
//        }
//        else if(idRelatorio != AppIF.SEM_REGISTO & MetodosConversor.converter_Integer_Para_Boolean(validadeRelatorio) == true){
//            validacao = true;
//        }
//        */
//    }
//
//
//
//    /**
//     * Metodo que permite obter o identificador do relatorio ambiental criado pelo utilizador
//     * @return o identificador
//     */
//    public String obterIdRelatorioAmbiental(){
//        return idRelatorioAmbiental;
//    }
//
//
//
//
//
//    /**
//     * Metodo que permite obter o identificador da checklist de avaliacao de riscos
//     * @return o identificador
//     */
//    public String obterIdCheckListAVR(){
//        return checklistAVR;
//    }
//
//
//
//    /**
//     * Metodo que permite gerar a informacao do estado
//     * @param estado o identificador do estado da atividade
//     */
//    private void gerarInformacaoEstado(int estado){
///*
//        switch (estado) {
//
//            case IdentificadoresIF.ID_ACTIVIDADE_EXECUTADA:
//
//                this.imagemEstado = R.drawable.icon_executado;
//                break;
//
//            case IdentificadoresIF.ID_ACTIVIDADE_NAO_EXECUTADA:
//
//                this.imagemEstado = R.drawable.icon_nao_executado;
//                break;
//
//            default:
//
//                this.imagemEstado = R.drawable.icon_sem_execucao;
//                break;
//        }
//        */
//    }
//
//
//
//    /**
//     * Metodo que permite gerar a avaliacao de riscos
//     * @param processoProdutivo
//     * @param numeroEquipamentos
//     * @param numeroVulnerabilidades
//     * @param completudeChecklist
//     * @param numeroMedidas
//     * @param totalLevantamentosValidos
//     * @param capa
//     */
//    private void gerarInformacaoAvaliacaoRisco(int processoProdutivo, int numeroEquipamentos, int numeroVulnerabilidades, int completudeChecklist, int numeroMedidas,
//                                               int totalLevantamentosValidos, int capa){
//
///*
//        if(this.idRelatorio == IdentificadoresIF.ID_AVALIACAO_RISCOS){
//
//            avaliacoes = new HashMap<Integer, Integer>();
//
//            avaliacoes.put(SECCAO_PROCESSO_PRODUTIVO, obterImagem(processoProdutivo));
//            avaliacoes.put(SECCAO_VERIFICACAO_EQUIPAMENTOS, obterImagem(numeroEquipamentos));
//            avaliacoes.put(SECCAO_VULNERABILIDADE, obterImagem(numeroVulnerabilidades));
//            avaliacoes.put(SECCAO_CHECKLIST, obterImagem(completudeChecklist));
//            avaliacoes.put(SECCAO_PLANO_ACAO, obterImagem(numeroMedidas));
//            avaliacoes.put(SECCAO_LEVANTAMENTO_RISCOS, obterImagem(totalLevantamentosValidos));
//            avaliacoes.put(SECCAO_CAPA_RELATORIO, obterImagemCapa(capa));
//        }*/
//    }
//
//
//
//    /**
//     * Metodo que permite obter uma imagem a partir da completude
//     * @param completude a completure 0/1
//     * @return uma imagem
//     *//*
//    private int obterImagem(int completude){
//
//        int imagem;
//
//        if(MetodosConversor.converter_Integer_Para_Boolean(completude) == true){
//            imagem = R.drawable.icon_completo;
//        }
//        else{
//            imagem = R.drawable.icon_incompleto;
//        }
//
//        return imagem;
//    }
//*/
//
//    /**
//     * Metodo que permite obter uma imagem de capa de relatorio a partir da completude
//     * @param completude a completure 0/1
//     * @return uma imagem
//     */
//    /*
//    private int obterImagemCapa(int completude){
//
//        int imagem;
//
//        if(MetodosConversor.converter_Integer_Para_Boolean(completude) == true){
//            imagem = R.drawable.icon_capa_relatorio;
//        }
//        else{
//            imagem = R.drawable.icon_sem_capa_relatorio;
//        }
//
//        return imagem;
//    }
//*/
//
//
//
//

//
//
//
//
//
//    /**
//     * Metodo que devolve a imagem de completude do relatorio
//     * @return uma imagem
//     */
//    public int obterImagemRelatorio(){
//        return imagemRelatorio;
//    }
//
//
//
//
//
//    /**
//     * Metodo que permite obter a imagem de uma avaliacao
//     * @param idAvaliacao o identificador da avaliacao
//     * @return uma imagem
//     */
//  /*
//    public int obterImagemAvaliacao(int idAvaliacao) {
//        return avaliacoes.get(idAvaliacao);
//    }
//*/
//
//    /**
//     * MEtodo que permite obter a imagem do estado da atividade
//     * @return uma imagem
//     */
//    public int obterImagemEstado(){
//        return imagemEstado;
//    }
//
//
//    /**
//     * Metodo que permite obter o estado da atividade
//     * @return um identificador
//     */
//    public int obterEstado() {
//        return estado;
//    }
//
//
//    /**
//     * Metodo que devolve o tempo de execucao
//     * @return o tempo de execucao
//     */
//    public String obterTempoExecucao() {
//        return tempoExecucao;
//    }
//
//
//    /**
//     * MEtodo que permite obter as observacaoes
//     * @return as observacaoes
//     */
//    public String obterObservacoes() {
//        return observacoes;
//    }
//
//
//    /**
//     * Metodo que permite obter a dados de execucao
//     * @return a dados (yyyy-mm-dd)
//     */
//    public String obterDataExecucao() {
//        return dataExecucao;
//    }
//
//
//    /**
//     * Metodo que permite obter o identificador da anomalia
//     * @return o identificador
//     */
//    public String obterIdAnomalia() {
//        return idAnomalia;
//    }
//



}
