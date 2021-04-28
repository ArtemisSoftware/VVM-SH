package com.vvm.sh.servicos.upload;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.Anomalia;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteNaoExecutada;
import com.vvm.sh.api.modelos.envio.sa.Email;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.DadosUploadSA;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.mapeamento.UploadSAMapping;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_ANOMALIA_CLIENTE;

public class GeradorDadosUploadSA extends GeradorDadosUpload{


    public GeradorDadosUploadSA(OnTransferenciaListener.OnUploadListener listener, UploadRepositorio repositorio, List<Upload> uploads, String idUtilizador) {
        super(listener, repositorio, uploads, idUtilizador, Identificadores.App.APP_SA);
    }


    @Override
    protected DadosUpload obterDadosUpload(String idUtilizador) {
        return new DadosUploadSA(idUtilizador);
    }

    @Override
    protected void obterDados(DadosFormulario dadosFormulario, Resultado resultado) {
        switch (resultado.id){




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

                switch (item.atividade.idRelatorio){

                    case Identificadores.Relatorios.ID_RELATORIO_FORMACAO:

                        registo.formacao = obterAcaoFormacao(item.resultado.id);
                        break;

                    case Identificadores.Relatorios.ID_RELATORIO_CERTIFICADO_TRATAMENTO:

                        registo.certificadoTratamento = obterCertificadoTratamento(item.resultado.id);
                        break;

                    default:
                        break;
                }


                registos.add(registo);
            }
            else {
                AtividadePendenteNaoExecutada registo = UploadSAMapping.INSTANCE.mapAtividadeNaoExecutada(item);
                registos.add(registo);
            }
        }
        return registos;
    }







    @Override
    protected com.vvm.sh.api.modelos.envio.sa.Email obterEmail(int idTarefa) {

        Email email = UploadSAMapping.INSTANCE.map(repositorio.obterEmail(idTarefa));
        return email;
    }






    @Override
    protected void camposAdicionais(List<Upload> resposta) {

    }
}
