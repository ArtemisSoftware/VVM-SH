package com.vvm.sh.servicos.upload;

import android.os.Handler;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteNaoExecutada;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.servicos.DadosUploadAsyncTask;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.UploadSAMapping;
import com.vvm.sh.util.mapeamento.UploadSHMapping;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_ATIVIDADE_PENDENTE;

public class DadosUploadSAAsyncTask extends DadosUploadAsyncTask {


    public DadosUploadSAAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, UploadRepositorio repositorio, String idUtilizador) {
        super(vvmshBaseDados, handler, repositorio, idUtilizador);
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
}
