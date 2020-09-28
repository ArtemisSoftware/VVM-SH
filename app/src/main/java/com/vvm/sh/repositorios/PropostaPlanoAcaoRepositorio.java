package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.QuestionarioChecklistDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;

public class PropostaPlanoAcaoRepositorio {

    public final PropostaPlanoAcaoDao propostaPlanoAcaoDao;
    public final ResultadoDao resultadoDao;


    public PropostaPlanoAcaoRepositorio(PropostaPlanoAcaoDao propostaPlanoAcaoDao,
                                        @NonNull ResultadoDao resultadoDao) {

        this.propostaPlanoAcaoDao = propostaPlanoAcaoDao;
        this.resultadoDao = resultadoDao;
    }
}
