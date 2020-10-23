package com.vvm.sh.di.atividadesPendentes.checklist;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AreaChecklistDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.QuestionarioChecklistDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.repositorios.ChecklistRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class ChecklistModule {

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
    ChecklistRepositorio provideChecklistRepositorio(int idApi, AreaChecklistDao areaChecklistDao, QuestionarioChecklistDao questionarioChecklistDao,
                                                     PropostaPlanoAcaoDao propostaPlanoAcaoDao, ImagemDao imagemDao,
                                                     TipoDao tipoDao, ResultadoDao resultadoDao) {

        ChecklistRepositorio repositorio = new ChecklistRepositorio(idApi, areaChecklistDao, questionarioChecklistDao, propostaPlanoAcaoDao, imagemDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
