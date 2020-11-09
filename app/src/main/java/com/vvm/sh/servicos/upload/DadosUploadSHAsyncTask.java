package com.vvm.sh.servicos.upload;

import android.os.Handler;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.bd.AtividadePlanoAcaoBd;
import com.vvm.sh.api.modelos.bd.ColaboradorBd;
import com.vvm.sh.api.modelos.bd.ExtintorBd;
import com.vvm.sh.api.modelos.bd.RegistoVisitaBd;
import com.vvm.sh.api.modelos.bd.RelatorioAmbientalBd;
import com.vvm.sh.api.modelos.bd.RelatorioAveriguacaoBd;
import com.vvm.sh.api.modelos.envio.Anomalia;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.AtividadePlanoAcao;
import com.vvm.sh.api.modelos.envio.AvaliacaoIluminacao;
import com.vvm.sh.api.modelos.envio.AvaliacaoTemperaturaHumidade;
import com.vvm.sh.api.modelos.envio.Colaborador;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Equipamento;
import com.vvm.sh.api.modelos.envio.Extintor;
import com.vvm.sh.api.modelos.envio.MedidaAveriguacao;
import com.vvm.sh.api.modelos.envio.ParqueExtintor;
import com.vvm.sh.api.modelos.envio.RelatorioAmbiental;
import com.vvm.sh.api.modelos.envio.RelatorioAveriguacao;
import com.vvm.sh.api.modelos.envio.sht.Email;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.api.modelos.envio.RegistoVisita;
import com.vvm.sh.api.modelos.envio.Sinistralidade;
import com.vvm.sh.api.modelos.envio.TrabalhoRealizado;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteNaoExecutada;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.mapeamento.UploadSHMapping;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_ATIVIDADE_PENDENTE;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_REGISTO_VISITA;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_SINISTRALIDADE;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_PLANO_ACAO;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_QUADRO_PESSOAL;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_PARQUE_EXTINTOR;

public class DadosUploadSHAsyncTask extends DadosUploadAsyncTask {

    public DadosUploadSHAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, UploadRepositorio repositorio, String idUtilizador) {
        super(vvmshBaseDados, handler, repositorio, idUtilizador);
    }



    @Override
    protected void obterDados(DadosFormulario dadosFormulario, Resultado resultado) {

        switch (resultado.id){






            case ID_ATIVIDADE_PENDENTE:

                dadosFormulario.fixarAveriguacao(obterAveriguacao(resultado.idTarefa));
                break;


//            case ID_REGISTO_VISITA:
//
//                dadosFormulario.registoVisita = obterRegistoVisita(resultado.idTarefa);
//                break;

            case ID_SINISTRALIDADE:

                dadosFormulario.sinistralidade = obterSinistralidade(resultado.idTarefa);
                break;


            case ID_QUADRO_PESSOAL:

                dadosFormulario.fixarQuadroPessoal(obterQuadroPessoal(resultado.idTarefa));
                break;



            case ID_PLANO_ACAO:

                dadosFormulario.fixarAtividadePlanoAcao(obterPlanoAcao(resultado.idTarefa));
                break;


            case ID_PARQUE_EXTINTOR:

                dadosFormulario.parqueExtintor = obterParqueExtintor(resultado.idTarefa);
                break;

            default:
                break;
        }
    }





    @Override
    protected List<AtividadePendente> obterAtividadesPendentes(int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for (AtividadePendenteBd item : repositorio.obterAtividadesPendentes(idTarefa)) {

            if(item.resultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){

                AtividadePendenteExecutada registo = UploadSHMapping.INSTANCE.mapAtividadeExecutada(item);

                switch (item.atividade.idRelatorio){
//
//                    case Identificadores.Relatorios.ID_RELATORIO_FORMACAO:
//
//                        registo.formacao = obterAcaoFormacao(item.resultado.id);
//                        break;
//
//                    case Identificadores.Relatorios.ID_RELATORIO_AVALIACAO_RISCO:
//
//                        registo.avaliacaoRiscos = obterAvaliacaoRiscos(idTarefa, item.resultado.id);
//                        break;

                    case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                        registo.iluminacao = obterRelatorioIluminacao(item.resultado.id);
                        break;

                    case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                        registo.temperaturaHumidade = obterRelatorioTemperaturaHumidade(item.resultado.id);
                        break;

                    default:
                        break;
                }


                registos.add(registo);
            }
            else {
                AtividadePendenteNaoExecutada registo = UploadSHMapping.INSTANCE.mapAtividadeNaoExecutada(item);
                registos.add(registo);
            }
        }

        return registos;
    }



    //--------------------------
    //Relatorios
    //---------------------------


    private List<RelatorioAveriguacao> obterAveriguacao(int idTarefa) {

        List<RelatorioAveriguacao> registos = new ArrayList<>();

        for (RelatorioAveriguacaoBd item : repositorio.obterRelatorioAveriguacao(idTarefa)) {

            RelatorioAveriguacao registo = UploadMapping.INSTANCE.map(item);
            registo.dataRelatorio = DatasUtil.converterData(Long.parseLong(item.data), DatasUtil.FORMATO_YYYY_MM_DD);

            List<MedidaAveriguacao> medidas = new ArrayList<>();

            for(RelatorioAveriguacaoResultado medida : repositorio.obterMedidasAveriguacao(item.idRelatorio)){
                medidas.add(UploadMapping.INSTANCE.map(medida));
            }

            registo.medidas = medidas;
            registos.add(registo);
        }



        return registos;
    }







    private RelatorioAmbiental obterRelatorioTemperaturaHumidade(int idAtividade) {

        RelatorioAmbientalBd registo = repositorio.obterRelatorioTemperaturaHumidade(idAtividade);
        RelatorioAmbiental relatorioAmbiental = UploadMapping.INSTANCE.map(registo);
        relatorioAmbiental.inicio = DatasUtil.converterData(registo.resultado.inicio, DatasUtil.HORA_FORMATO_HH_MM);
        relatorioAmbiental.termino =  DatasUtil.converterData(registo.resultado.termino, DatasUtil.HORA_FORMATO_HH_MM);
        relatorioAmbiental.dataAvaliacao = DatasUtil.converterData(registo.resultado.data, DatasUtil.FORMATO_YYYY_MM_DD);
        relatorioAmbiental.equipamento = Sintaxe.Palavras.EQUIPAMENTO_RELATORIO_TEMPERATURA_HUMIDADE;

        for(AvaliacaoAmbientalResultado item : repositorio.obterAvaliacoesAmbiental(registo.resultado.id)){

            List<Integer> categoriasProfissionais = repositorio.obterCategoriasProfissionais(item.id, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE);
            List<Integer> medidas = repositorio.obterMedidas(item.id, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS);

            AvaliacaoTemperaturaHumidade avaliacao = UploadMapping.INSTANCE.map(item);
            avaliacao.categoriasProfissionais = categoriasProfissionais;
            avaliacao.medidasRecomendadas = medidas;

            relatorioAmbiental.avaliacoes.add(avaliacao);
        }

        return relatorioAmbiental;
    }


    private RelatorioAmbiental obterRelatorioIluminacao(int idAtividade) {

        RelatorioAmbientalBd registo = repositorio.obterRelatorioIluminacao(idAtividade);
        RelatorioAmbiental relatorioAmbiental = UploadMapping.INSTANCE.map(registo);
        relatorioAmbiental.inicio = DatasUtil.converterData(registo.resultado.inicio, DatasUtil.HORA_FORMATO_HH_MM);
        relatorioAmbiental.termino =  DatasUtil.converterData(registo.resultado.termino, DatasUtil.HORA_FORMATO_HH_MM);
        relatorioAmbiental.dataAvaliacao = DatasUtil.converterData(registo.resultado.data, DatasUtil.FORMATO_YYYY_MM_DD);
        relatorioAmbiental.equipamento = Sintaxe.Palavras.EQUIPAMENTO_RELATORIO_ILUMINACAO;

        for(AvaliacaoAmbientalResultado item : repositorio.obterAvaliacoesAmbiental(registo.resultado.id)){

            List<Integer> categoriasProfissionais = repositorio.obterCategoriasProfissionais(item.id, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO);
            List<Integer> medidas = repositorio.obterMedidas(item.id, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS);

            AvaliacaoIluminacao avaliacao = UploadMapping.INSTANCE.mapeamentoIluminacao(item);
            avaliacao.sexo = ConversorUtil.converterGenero(item.sexo);
            avaliacao.categoriasProfissionais = categoriasProfissionais;
            avaliacao.medidasRecomendadas = medidas;

            relatorioAmbiental.avaliacoes.add(avaliacao);
        }

        return relatorioAmbiental;
    }

    //--------------------------
    //Adicionais
    //---------------------------


    @Override
    protected void camposAdicionais(List<Upload> resposta) {
        dadosUpload.equipamentos = obterEquipamentos(resposta);
    }


    private List<Equipamento> obterEquipamentos(List<Upload> resposta) {

        List<Integer> ids = new ArrayList<>();
        List<Equipamento> registos = new ArrayList<>();

        for(Upload item : resposta){
            ids.add(item.tarefa.idTarefa);
        }

        for (TipoNovo item : repositorio.obterNovosEquipamentos(ids)) {

            Equipamento equipamento = UploadMapping.INSTANCE.map(item);
            equipamento.id = Identificadores.Codigos.EQUIPAMENTO + item.idProvisorio;
            equipamento.idUtilizador = idUtilizador;
            registos.add(equipamento);
        }

        return registos;
    }



    //--------------------------
    //
    //---------------------------


    private ParqueExtintor obterParqueExtintor(int idTarefa) {

        List<Extintor> registos = new ArrayList<>();

        for (ExtintorBd item : repositorio.obterParqueExtintor(idTarefa)) {

            Extintor registo = UploadMapping.INSTANCE.map(item.extintor, item.resultado);
            registo.dataValidade = DatasUtil.converterData(item.resultado.dataValidade, DatasUtil.FORMATO_YYYY_MM_DD);
            registos.add(registo);
        }

        ParqueExtintor parqueExtintor = new ParqueExtintor();
        parqueExtintor.extintores = registos;
        parqueExtintor.inalterado = repositorio.obterNumeroExtintoresInalterados(idTarefa) + "";
        return parqueExtintor;
    }



    /**
     * Metodo que permite obter o quadro pessoal
     * @param idTarefa o identificador da tarefa
     * @return uma lista de colaboradores
     */
    private List<Colaborador> obterQuadroPessoal(int idTarefa) {

        List<Colaborador> registos = new ArrayList<>();

        for (ColaboradorBd item : repositorio.obterQuadroPessoal(idTarefa)) {

            Colaborador colaborador = UploadMapping.INSTANCE.map(item);
            colaborador.dataAdmissao = DatasUtil.converterData(item.dataAdmissao, DatasUtil.FORMATO_DD_MM_YYYY);
            colaborador.dataNascimento = DatasUtil.converterData(item.dataNascimento, DatasUtil.FORMATO_DD_MM_YYYY);
            colaborador.dataAdmissaoFuncao = DatasUtil.converterData(item.dataAdmissaoFuncao, DatasUtil.FORMATO_DD_MM_YYYY);
            registos.add(colaborador);
        }

        return registos;
    }





    /**
     * Metodo que permite obter o plano de acao
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades do plano
     */
    private List<AtividadePlanoAcao> obterPlanoAcao(int idTarefa) {

        List<AtividadePlanoAcao> registos = new ArrayList<>();

        for (AtividadePlanoAcaoBd item : repositorio.obterPlanoAcao(idTarefa)) {

            AtividadePlanoAcao registo = UploadMapping.INSTANCE.map(item);
            registo.data = DatasUtil.converterData(registo.data, DatasUtil.FORMATO_YYYY_MM_DD, DatasUtil.FORMATO_DD_MM_YYYY);
            registos.add(registo);
        }

        return registos;
    }


    /**
     * Metodo que permite obter a sinistralidade
     * @param idTarefa o identificador da tarefa
     * @return a sinistralidade
     */
    private Sinistralidade obterSinistralidade(int idTarefa) {
        return UploadMapping.INSTANCE.map(repositorio.obterSinistralidade(idTarefa));
    }



    /**
     * Metodo que permite obter o registo de visita
     * @param idTarefa o identificador da tarefa
     * @return os dados do registo
     */
    private RegistoVisita obterRegistoVisita(int idTarefa){

        RegistoVisitaBd registo = repositorio.obterRegistoVisita(idTarefa);

        RegistoVisita registoVisita = UploadMapping.INSTANCE.map(registo.resultado);
        Imagem imagem = new Imagem();
        imagem.idFoto = registo.idImagem + "";

        registoVisita.data = DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY__HH_MM);
        registoVisita.album = new ArrayList<>();
        registoVisita.album.add(imagem);
        idImagens.add(registo.idImagem);

        List<TrabalhoRealizado> registos = new ArrayList<>();

        for (TrabalhoRealizadoResultado item : repositorio.obterTrabalhoRealizado(idTarefa)) {

            TrabalhoRealizado trabalhoRealizado = UploadMapping.INSTANCE.map(item);
            registos.add(trabalhoRealizado);
        }

        registoVisita.trabalhosRealizados = registos;
        return registoVisita;
    }





    @Override
    protected Email obterEmail(int idTarefa) {

        Email email = UploadSHMapping.INSTANCE.map(repositorio.obterEmail(idTarefa));
        return email;
    }

}
