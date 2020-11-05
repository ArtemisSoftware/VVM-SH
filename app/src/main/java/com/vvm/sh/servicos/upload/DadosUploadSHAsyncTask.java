package com.vvm.sh.servicos.upload;

import android.os.Handler;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.bd.RegistoVisitaBd;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.api.modelos.envio.RegistoVisita;
import com.vvm.sh.api.modelos.envio.Sinistralidade;
import com.vvm.sh.api.modelos.envio.TrabalhoRealizado;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteNaoExecutada;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.mapeamento.UploadSHMapping;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_ATIVIDADE_PENDENTE;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_REGISTO_VISITA;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_SINISTRALIDADE;

public class DadosUploadSHAsyncTask extends DadosUploadAsyncTask {

    public DadosUploadSHAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, UploadRepositorio repositorio, String idUtilizador) {
        super(vvmshBaseDados, handler, repositorio, idUtilizador);
    }

    @Override
    protected void obterDados(DadosFormulario dadosFormulario, Resultado resultado) {

        switch (resultado.id){


            case ID_ATIVIDADE_PENDENTE:

                dadosFormulario.fixarAtividadesPendentes(obterAtividadesPendentes(resultado.idTarefa));
                //dadosFormulario.fixarAveriguacao(obterAveriguacao(resultado.idTarefa));
                break;


            case ID_REGISTO_VISITA:

                dadosFormulario.registoVisita = obterRegistoVisita(resultado.idTarefa);
                break;

            case ID_SINISTRALIDADE:

                dadosFormulario.sinistralidade = obterSinistralidade(resultado.idTarefa);
                break;

            default:
                break;
        }
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



    protected List<AtividadePendente> obterAtividadesPendentes(int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for (AtividadePendenteBd item : repositorio.obterAtividadesPendentes(idTarefa)) {

            if(item.resultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){

                AtividadePendenteExecutada registo = UploadSHMapping.INSTANCE.mapeamentoAtividadeExecutada(item);

//                switch (item.atividade.idRelatorio){
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
//
//                    case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:
//
//                        registo.iluminacao = obterRelatorioIluminacao(item.resultado.id);
//                        break;
//
//                    case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:
//
//                        registo.temperaturaHumidade = obterRelatorioTemperaturaHumidade(item.resultado.id);
//                        break;
//
//                    default:
//                        break;
//                }


                registos.add(registo);
            }
            else {
                AtividadePendenteNaoExecutada registo = UploadSHMapping.INSTANCE.mapAtividadeNaoExecutada(item);
                registos.add(registo);
            }
        }

        return registos;
    }

}
