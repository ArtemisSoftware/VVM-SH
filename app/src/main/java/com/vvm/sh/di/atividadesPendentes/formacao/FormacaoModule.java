package com.vvm.sh.di.atividadesPendentes.formacao;

import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteResultadoDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ImagemResultadoDao;
import com.vvm.sh.repositorios.FormacaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class FormacaoModule {



    @FormacaoScope
    @Provides
    static FormandoDao provideFormandoDao(VvmshBaseDados vvmshBaseDados){

        FormandoDao dao = vvmshBaseDados.obterFormandoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @FormacaoScope
    @Provides
    static AcaoFormacaoDao provideAcaoFormacao(VvmshBaseDados vvmshBaseDados){

        AcaoFormacaoDao dao = vvmshBaseDados.obterAcaoFormacaoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @FormacaoScope
    @Provides
    static AtividadePendenteResultadoDao provideAtividadePendenteResultadoDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteResultadoDao dao = vvmshBaseDados.obterAtividadePendenteResultadoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }

    @FormacaoScope
    @Provides
    FormacaoRepositorio provideFormacaoRepositorio(FormandoDao formandoDao, AcaoFormacaoDao acaoFormacaoDao,
                                                   AtividadePendenteResultadoDao atividadePendenteResultadoDao, ImagemResultadoDao imagemResultadoDao,
                                                   TipoDao tipoDao, ResultadoDao resultadoDao) {

        FormacaoRepositorio repositorio = new FormacaoRepositorio(formandoDao, acaoFormacaoDao, atividadePendenteResultadoDao, imagemResultadoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
