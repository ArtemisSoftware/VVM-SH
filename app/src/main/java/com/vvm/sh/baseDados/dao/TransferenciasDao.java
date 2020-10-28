package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.api.modelos.bd.AreaBd;
import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.bd.AtividadePlanoAcaoBd;
import com.vvm.sh.api.modelos.bd.ColaboradorBd;
import com.vvm.sh.api.modelos.bd.FormandoBd;
import com.vvm.sh.api.modelos.bd.RegistoVisitaBd;
import com.vvm.sh.api.modelos.bd.RelatorioAmbientalBd;
import com.vvm.sh.api.modelos.envio.Checklist;
import com.vvm.sh.api.modelos.envio.RegistoVisita;
import com.vvm.sh.api.modelos.envio.RelatorioAmbiental;
import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class TransferenciasDao implements BaseDao<Resultado> {




    @Transaction
    @Query("SELECT * " +
            "FROM clientes as cl " +
            "LEFT JOIN (" +
            "SELECT idTarefa, IFNULL(SUM(ct_atp_res), 0) as ct_realizado FROM (" +
            "SELECT * FROM atividadesPendentes as atp " +
            "LEFT JOIN (SELECT id, COUNT(*) as ct_atp_res FROM atividadesPendentesResultado GROUP BY id) as atp_res ON atp.id = atp_res.id" +
            ") as pend GROUP BY idTarefa" +
            ") as pend ON cl.idTarefa = pend.idTarefa " +
            "WHERE cl.idTarefa IN (SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador) AND ct_realizado = 0")
    abstract public Maybe<List<Pendencia>> obterPendencias(String idUtilizador);


    @Transaction
    @Query("SELECT * " +
            "FROM clientes as cl " +
            "LEFT JOIN (" +
            "SELECT idTarefa, IFNULL(SUM(ct_atp_res), 0) as ct_realizado FROM (" +
            "SELECT * FROM atividadesPendentes as atp " +
            "LEFT JOIN (SELECT id, COUNT(*) as ct_atp_res FROM atividadesPendentesResultado GROUP BY id) as atp_res ON atp.id = atp_res.id" +
            ") as pend GROUP BY idTarefa" +
            ") as pend ON cl.idTarefa = pend.idTarefa " +
            "WHERE cl.idTarefa IN (SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador AND data =:data) AND ct_realizado = 0")
    abstract public Maybe<List<Pendencia>> obterPendencias(String idUtilizador, long data);






    @Transaction
    @Query("SELECT *,  " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " as sincronizado " +
            "FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(sincronizado) as ct FROM resultados WHERE sincronizado = :sincronizado GROUP BY idTarefa) as res " +
            "ON trf.idTarefa = res.idTarefa " +
            "WHERE ct > 0 AND idUtilizador = :idUtilizador")
    abstract public Maybe<List<Upload>> obterUploads(String idUtilizador, boolean sincronizado);



    @Transaction
    @Query("SELECT *, " +
            "CASE " +
            "WHEN IFNULL(ct_sinc_total, 0) = 0 THEN " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " " +
            "WHEN IFNULL(ct_sinc, 0) = IFNULL(ct_sinc_total, 0) THEN " + Identificadores.Sincronizacao.SINCRONIZADO + " " +
            "ELSE  " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " END as sincronizado " +
            "FROM tarefas  as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(sincronizado) as ct_sinc_total FROM resultados GROUP BY idTarefa) as res_sinc_total " +
            "ON trf.idTarefa = res_sinc_total.idTarefa " +
            "LEFT JOIN (SELECT idTarefa, COUNT(sincronizado) as ct_sinc FROM resultados WHERE sincronizado = "+ Identificadores.Sincronizacao.SINCRONIZADO +" GROUP BY idTarefa) as res_sinc " +
            "ON trf.idTarefa = res_sinc.idTarefa " +
            "WHERE ct_sinc_total > 0 AND idUtilizador = :idUtilizador AND data = :data")
    abstract public Maybe<List<Upload>> obterUploads(String idUtilizador, long data);






    //-------------------
    //RESULTADOS
    //-------------------



    @Query("SELECT * FROM emailsResultado WHERE idTarefa = :idTarefa")
    abstract public EmailResultado obterEmail(int idTarefa);


    @Query("SELECT * FROM anomaliasResultado WHERE idTarefa = :idTarefa")
    abstract public List<AnomaliaResultado> obterAnomalias(int idTarefa);


    @Query("SELECT * FROM crossSellingResultado WHERE idTarefa = :idTarefa")
    abstract public List<CrossSellingResultado> obterCrossSelling(int idTarefa);


    @Query("SELECT * FROM ocorrenciaResultado WHERE idTarefa = :idTarefa")
    abstract public List<OcorrenciaResultado> obterOcorrencias(int idTarefa);


    @Transaction
    @Query("SELECT * " +
            "FROM atividadesPendentesResultado as at_pend_res " +
            "LEFT JOIN (SELECT idTarefa, id as idAtividade FROM atividadesPendentes) as at_pend ON at_pend_res.id = at_pend.idAtividade " +
            "WHERE at_pend.idTarefa = :idTarefa")
    abstract public List<AtividadePendenteBd> obterAtividadesPendentes(int idTarefa);


    @Query("SELECT * FROM acoesFormacaoResultado WHERE idAtividade = :idAtividade")
    abstract public AcaoFormacaoResultado obterAcaoFormacao(int idAtividade);

    @Query("SELECT * " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id, idImagem FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO + ") as img " +
            "ON frm_res.id = img.id " +
            "WHERE idAtividade = :idAtividade AND selecionado = 1")
    abstract public List<FormandoBd> obterFormandos(int idAtividade);


    @Query("SELECT * FROM tarefas WHERE idTarefa = :idTarefa")
    abstract public Tarefa obterTarefa(int idTarefa);


    @Query("SELECT * FROM imagensResultado WHERE idImagem IN(:ids)")
    abstract public List<ImagemResultado> obterImagens(List<Integer> ids);

    @Query("SELECT * FROM imagensResultado WHERE id = :id AND origem =:origem")
    abstract public List<ImagemResultado> obterImagens(int id, int origem);

    @Query("SELECT idImagem FROM imagensResultado WHERE id = :id AND origem =:origem")
    abstract public List<Integer> obterImagens_(int id, int origem);


    @Query("SELECT * " +
            "FROM registoVisitaResultado as rg_vist_res " +
            "LEFT JOIN (SELECT idTarefa, idImagem FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_REGISTO_VISITA + " ) as img " +
            "ON rg_vist_res.idTarefa = img.idTarefa " +
            "WHERE rg_vist_res.idTarefa = :idTarefa")
    abstract public RegistoVisitaBd obterRegistoVisita(int idTarefa);

    @Query("SELECT * FROM trabalhoRealizadoResultado WHERE idTarefa = :idTarefa")
    abstract public List<TrabalhoRealizadoResultado> obterTrabalhoRealizado(int idTarefa);


    @Query("SELECT tp.* " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idChecklist FROM areasChecklistResultado WHERE idAtividade =:idAtividade) as ar_chk_res " +
            "ON tp.id = ar_chk_res.idChecklist " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_CHECKLIST +"'")
    public abstract Tipo obterChecklist(int idAtividade);


    @Query("SELECT *, descricao " +
            "FROM areasChecklistResultado as ar_chk_res " +
            "LEFT JOIN (SELECT idChecklist, idArea, descricao FROM areasChecklist) as chk_ar " +
            "ON ar_chk_res.idChecklist = chk_ar.idChecklist AND ar_chk_res.idArea = chk_ar.idArea AND idAtividade =:idAtividade")
    public abstract List<AreaBd> obterAreas(int idAtividade);


    @Query("SELECT DISTINCT idSeccao FROM questionarioChecklistResultado  WHERE idArea =:idRegistoArea")
    public abstract List<String> obterSeccoes(int idRegistoArea);



    @Query("SELECT * FROM questionarioChecklistResultado  WHERE idArea =:idRegistoArea AND idSeccao = :idSeccao AND tipo =:tipo")
    public abstract List<QuestionarioChecklistResultado> obterItens(int idRegistoArea, String idSeccao, String tipo);







    @Query("SELECT * FROM trabalhadoresVulneraveisResultado WHERE idAtividade = :idAtividade")
    public abstract List<TrabalhadorVulneravelResultado> obterTrabalhadoresVulneraveis(int idAtividade);


    @Query("SELECT DISTINCT IFNULL(homens, 0) as ct_homens FROM categoriasProfissionaisResultado WHERE id = :id AND origem = :origem")
    public abstract int obterNumeroHomens_TrabalhadoresVulneraveis(int id, int origem);


    @Query("SELECT DISTINCT IFNULL(mulheres, 0) as ct_mulheres FROM categoriasProfissionaisResultado WHERE id = :id AND origem = :origem")
    public abstract int obterNumeroMulheres_TrabalhadoresVulneraveis(int id, int origem);

    @Query("SELECT idCategoriaProfissional FROM categoriasProfissionaisResultado WHERE idRegisto = :id AND origem = :origem")
    public abstract List<Integer> obterCategoriasProfissionais_TrabalhadoresVulneraveis(int id, int origem);





    @Query("SELECT *, idMedidaRecomendada " +
            "FROM relatorioAmbientalResultado as rel_amb_res " +

            "LEFT JOIN(  " +
            "SELECT CASE WHEN SUM(VALIDO) > 0 AND COUNT(VALIDO) > 0 THEN 3 ELSE 4 END as  idMedidaRecomendada, idRelatorio " +
            "FROM ( " +
            "SELECT idRelatorio, " +
            "CASE " +
            "WHEN (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) OR CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70 THEN 0 " +
            "ELSE 1 END as VALIDO " +
            "FROM avaliacoesAmbientaisResultado  " +
            ") as medidas  " +
            "GROUP BY idRelatorio " +
            ") as med ON rel_amb_res.id = med.idRelatorio " +

            "WHERE idAtividade = :idAtividade AND tipo = "+ Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE + " ")
    public abstract RelatorioAmbientalBd obterRelatorioTemperaturaHumidade(int idAtividade);


    @Query("SELECT *, idMedidaRecomendada " +
            "FROM relatorioAmbientalResultado as rel_amb_res " +
            "LEFT JOIN ( " +
            "SELECT idRelatorio, CASE WHEN SUM(VALIDO) > 0 AND COUNT(VALIDO) > 0 THEN 2 ELSE 1 END as idMedidaRecomendada " +
            "FROM ( " +
            "SELECT CASE WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) THEN 1  ELSE 0 END as VALIDO,  idRelatorio " +
            "FROM avaliacoesAmbientaisResultado " +
            ") as medidas " +
            "GROUP BY idRelatorio " +
            ") as med ON rel_amb_res.id = med.idRelatorio  " +
            "WHERE idAtividade = :idAtividade AND tipo = "+ Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO + " ")
    public abstract RelatorioAmbientalBd obterRelatorioIluminacao(int idAtividade);





    @Query("SELECT * FROM avaliacoesAmbientaisResultado WHERE idRelatorio = :idRelatorio")
    public abstract List<AvaliacaoAmbientalResultado> obterAvaliacoesAmbiental(int idRelatorio);



    @Query("SELECT idMedida FROM medidasResultado WHERE id = :id AND origem = :origem")
    public abstract List<Integer> obterMedidas(int id, int origem);

    @Query("SELECT idCategoriaProfissional FROM categoriasProfissionaisResultado WHERE id = :id AND origem = :origem")
    public abstract List<Integer> obterCategoriasProfissionais(int id, int origem);




    @Query("SELECT " +
            "CASE  " +
            "WHEN codigo = " + Identificadores.ESTADO_PENDENTE + " THEN '" + Identificadores.Codigos.EQUIPAMENTO  + "' || idEquipamento " +
            "ELSE idEquipamento END as equipamento " +
            "FROM verificacaoEquipamentosResultado WHERE idAtividade = :idAtividade")
    public abstract List<String> obterEquipamentos(int idAtividade);


    @Query("SELECT tp.* " +
            "FROM tiposNovos as tp " +
            "LEFT JOIN (SELECT idEquipamento, codigo, idAtividade FROM verificacaoEquipamentosResultado) as ve_res ON tp.id = ve_res.idEquipamento " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND estado = " + Identificadores.ESTADO_PENDENTE + " " +
            "AND ve_res.codigo = " + Identificadores.ESTADO_PENDENTE + " " +
            "AND idAtividade IN (SELECT id FROM atividadesPendentes WHERE idTarefa IN(:idsTarefas))  ")
    public abstract List<TipoNovo> obterNovosEquipamentos(List<Integer> idsTarefas);



    @Query("SELECT descricao FROM processosProdutivosResultado WHERE id =:idAtividade")
    public abstract String obterProcessoProdutivo(int idAtividade);


    @Query("SELECT * FROM levantamentosRiscoResultado WHERE id =:idAtividade")
    public abstract List<LevantamentoRiscoResultado> obterLevantamentos(int idAtividade);

    @Query("SELECT * FROM riscosResultado WHERE id =:idAtividade")
    public abstract List<RiscoResultado> obterRiscos(int idAtividade);


    @Query("SELECT DISTINCT idMedida FROM propostaPlanoAcaoResultado WHERE idAtividade =:idAtividade AND selecionado = 1 AND origem = " + Identificadores.Origens.PROPOSTA_MEDIDAS_AVALIACAO + " ")
    public abstract List<Integer> obterPropostaPlanoAcao(int idAtividade);

    @Query("SELECT idImagem FROM imagensResultado WHERE idTarefa = :idTarefa AND capaRelatorio = 1")
    public abstract Integer obterCapaRelatorio(int idTarefa);




    @Query("SELECT IFNULL(plano_acao_res.servId, '') as servId, data, servicoTp, ordem " +
            "FROM planoAccaoResultado as plano_acao_res " +
            "LEFT JOIN (SELECT idTarefa, servicoTp FROM clientes) as clt ON plano_acao_res.idTarefa = clt.idTarefa " +
            "LEFT JOIN (SELECT DISTINCT servId, ordem  FROM tiposAtividadesPlaneaveis) as act_pln ON plano_acao_res.servId = act_pln.servId " +
            "WHERE plano_acao_res.idTarefa = :idTarefa ")
    public abstract List<AtividadePlanoAcaoBd> obterPlanoAcao(int idTarefa);

    @Query("SELECT * FROM sinistralidadesResultado WHERE idTarefa = :idTarefa ")
    public abstract SinistralidadeResultado obterSinistralidade(int idTarefa);




    @Query("SELECT clb_res.idRegisto as id, " +
            "IFNULL(clb_res.nome, clb.nome) as nome, " +
            "idMorada, sexo, " +
            "IFNULL(clb_res.estado, clb.estado) as estado , dataNascimento, dataAdmissao, dataAdmissaoFuncao, idCategoriaProfissional, posto, profissao " +
            "FROM colaboradoresResultado as clb_res " +
            "LEFT JOIN (SELECT nome, id, estado FROM colaboradores) as clb ON clb_res.id = clb.id " +
            "WHERE idTarefa = :idTarefa ")
    public abstract List<ColaboradorBd> obterColaboradores(int idTarefa);

    //-------------------
    //TRABALHO
    //-------------------


    @Insert
    abstract public Long inserirRegisto(Tarefa tarefa);

    @Insert
    abstract public void inserirRegisto(Cliente registo);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public List<Long> inserirAtividadesExecutadas(List<AtividadeExecutada> registos);

    @Insert
    abstract public List<Long> inserirAtividadesPendentes(List<AtividadePendente> registos);



    @Insert
    abstract public List<Long> inserirAnomalias(List<Anomalia> registos);


    @Insert
    abstract public Long inserirRegisto(Ocorrencia registo);

    @Insert
    abstract public List<Long> inserir(List<OcorrenciaHistorico> registos);



    @Insert
    abstract public List<Long> inserirMoradas(List<Morada> registos);

    @Insert
    abstract public List<Long> inserirTipoExtintor(List<TipoExtintor> registos);

    @Insert
    abstract public List<Long> inserirParqueExtintores(List<ParqueExtintor> registos);

    @Insert
    abstract public List<Long>  inserirQuadroPessoal(List<Colaborador> registos);


    @Insert
    abstract public Long inserirPlanoAcao(PlanoAcao registo);

    @Insert
    abstract public List<Long>  inserirPlanoAtividades(List<PlanoAcaoAtividade> registos);



    @Query("DELETE FROM tarefas WHERE idTarefa = :idTarefa")
    abstract public void removerTarefa(int idTarefa);


    @Query("DELETE FROM tarefas WHERE idTarefa IN(SELECT idTarefa FROM tarefas WHERE idUtilizador = :idUtilizador AND data = :data)")
    abstract public void removerTrabalho(String idUtilizador, long data);



}
