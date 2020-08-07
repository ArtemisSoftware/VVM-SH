package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.dao.UploadDao;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.upload.modelos.Pendencia;
import com.vvm.sh.ui.upload.modelos.Upload;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class UploadRepositorio {


    private final SegurancaAlimentarApi api;

    private final UploadDao uploadDao;


    public UploadRepositorio(@NonNull SegurancaAlimentarApi api, @NonNull UploadDao uploadDao) {
        this.api = api;
        this.uploadDao = uploadDao;
    }

    public Maybe<List<Resultado>> obterResultados(String idUtilizador){
        return uploadDao.obterResultados(idUtilizador, false);
    }

    public Maybe<List<Upload>> obterUploads(String idUtilizador){
        return uploadDao.obterUploads(idUtilizador, false);
    }


    public Maybe<List<Pendencia>> obterPendencias(String idUtilizador){
        return uploadDao.obterPendencias(idUtilizador);
    }



    public Single<Integer> sincronizar(List<Upload> uploads){

        List<Resultado> resultados = new ArrayList<>();

        for(int index = 0; index < uploads.size(); ++index){

            for(int posicao = 0; posicao < uploads.get(index).resultados.size(); ++posicao){

                Resultado item = uploads.get(index).resultados.get(posicao);
                item.sincronizado = true;
                resultados.add(item);
            }
        }

        Resultado registos[] = (Resultado[]) resultados.toArray();

        return uploadDao.atualizar(registos);
    }



    public EmailResultado obterEmail(int idTarefa){
        return uploadDao.obterEmail(idTarefa);
    }

    public List<AnomaliaResultado> obterAnomalias(int idTarefa){
        return uploadDao.obterAnomalias(idTarefa);
    }

    public List<CrossSellingResultado> obterCrossSelling(int idTarefa){
        return uploadDao.obterCrossSelling(idTarefa);
    }

    public List<OcorrenciaResultado> obterOcorrencias(int idTarefa) {
        return uploadDao.obterOcorrencias(idTarefa);
    }


    public List<AtividadePendenteResultado_> obterAtividadesPendentes(int idTarefa) {
        return uploadDao.obterAtividadesPendentes(idTarefa);
    }

    public AcaoFormacaoResultado obterAcaoFormacao(int idAtividade) {
        return uploadDao.obterAcaoFormacao(idAtividade);
    }

    public List<FormandoResultado> obterFormandos(int idAtividade) {
        return uploadDao.obterFormandos(idAtividade);
    }

    public Tarefa obterTarefa(int idTarefa) {
        return uploadDao.obterTarefa(idTarefa);
    }
}
