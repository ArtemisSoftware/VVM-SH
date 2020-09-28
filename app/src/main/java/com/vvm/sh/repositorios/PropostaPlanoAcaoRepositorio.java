package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.QuestionarioChecklistDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;

public class PropostaPlanoAcaoRepositorio {


    public final ResultadoDao resultadoDao;


    public PropostaPlanoAcaoRepositorio(
                                        @NonNull ResultadoDao resultadoDao) {

        this.resultadoDao = resultadoDao;
    }
}
