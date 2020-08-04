package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.Tarefa_;
import com.vvm.sh.baseDados.dao.UploadDao;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;

import java.util.List;

import io.reactivex.Maybe;

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
