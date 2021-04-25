package com.vvm.sh.di.atividadesPendentes.checklist;

import android.app.Application;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AreaChecklistDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.QuestionarioChecklistDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental.AvaliacaoAmbientalScope;
import com.vvm.sh.repositorios.ChecklistRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ChecklistModule {

    @ChecklistScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @ChecklistScope
    @Provides
    static PropostaPlanoAcaoDao providePropostaPlanoAcaoDao(VvmshBaseDados vvmshBaseDados){

        PropostaPlanoAcaoDao dao = vvmshBaseDados.obterPropostaPlanoAcaoDao();
        return dao;
    }

    @ChecklistScope
    @Provides
    static AreaChecklistDao provideAreaChecklistDao(VvmshBaseDados vvmshBaseDados){

        AreaChecklistDao dao = vvmshBaseDados.obterAreaChecklistDao();
        return dao;
    }

    @ChecklistScope
    @Provides
    static QuestionarioChecklistDao provideQuestionarioChecklistDao(VvmshBaseDados vvmshBaseDados){

        QuestionarioChecklistDao dao = vvmshBaseDados.obterQuestionarioChecklistDao();
        return dao;
    }

    @ChecklistScope
    @Provides
    ChecklistRepositorio provideChecklistRepositorio(@Named("idApi_") int idApi, AreaChecklistDao areaChecklistDao, QuestionarioChecklistDao questionarioChecklistDao,
                                                     PropostaPlanoAcaoDao propostaPlanoAcaoDao, ImagemDao imagemDao,
                                                     TipoDao tipoDao, ResultadoDao resultadoDao) {

        ChecklistRepositorio repositorio = new ChecklistRepositorio(idApi, areaChecklistDao, questionarioChecklistDao, propostaPlanoAcaoDao, imagemDao, tipoDao, resultadoDao);
        return repositorio;
    }

}
