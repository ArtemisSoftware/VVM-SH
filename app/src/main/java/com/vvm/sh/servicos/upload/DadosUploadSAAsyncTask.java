package com.vvm.sh.servicos.upload;

import android.os.Handler;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.Anomalia;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.sa.Email;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteNaoExecutada;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.mapeamento.UploadSAMapping;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_ANOMALIA_CLIENTE;

public class DadosUploadSAAsyncTask extends DadosUploadAsyncTask {


    public DadosUploadSAAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, UploadRepositorio repositorio, String idUtilizador) {
        super(vvmshBaseDados, handler, repositorio, idUtilizador);
    }

    @Override
    protected void obterDados(DadosFormulario dadosFormulario, Resultado resultado) {
        switch (resultado.id){


            case ID_ANOMALIA_CLIENTE:

                dadosFormulario.fixarAnomalias(obterAnomaliaCliente(resultado.idTarefa));
                break;



            default:
                break;
        }
    }

    @Override
    protected List<AtividadePendente> obterAtividadesPendentes(int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for (AtividadePendenteBd item : repositorio.obterAtividadesPendentes(idTarefa)) {

            if (item.resultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO) {

                AtividadePendenteExecutada registo = UploadSAMapping.INSTANCE.mapAtividadeExecutada(item);

//                switch (item.atividade.idRelatorio){
//
//                    case Identificadores.Relatorios.ID_RELATORIO_FORMACAO:
//
//                        registo.formacao = obterAcaoFormacao(item.resultado.id);
//                        break;

//                    default:
//                        break;
//                }


                registos.add(registo);
            }
            else {
                AtividadePendenteNaoExecutada registo = UploadSAMapping.INSTANCE.mapAtividadeNaoExecutada(item);
                registos.add(registo);
            }
        }
        return registos;
    }



    /**
     * Metodo que permite obter as anomalias de um cliente
     * @param idTarefa o identificador da tarefa
     * @return os dados das anomalias
     */
    private List<Anomalia> obterAnomaliaCliente(int idTarefa) {

        List<Anomalia> registos = new ArrayList<>();

        for (AnomaliaResultado item : repositorio.obterAnomalias(idTarefa)) {
            registos.add(UploadMapping.INSTANCE.map(item));
        }

        return registos;
    }



    @Override
    protected Email obterEmail(int idTarefa) {

        Email email = UploadSAMapping.INSTANCE.map(repositorio.obterEmail(idTarefa));
        return email;
    }






    @Override
    protected void adicionarCamposAdicionais(List<Upload> resposta) {

    }
}
