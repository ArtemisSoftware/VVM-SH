package com.vvm.sh.di.atividadesPendentes.formacao;

import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
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
    static AtividadePendenteDao provideAtividadePendenteResultadoDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteDao dao = vvmshBaseDados.obterAtividadePendenteDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }

    @FormacaoScope
    @Provides
    FormacaoRepositorio provideFormacaoRepositorio(FormandoDao formandoDao, AcaoFormacaoDao acaoFormacaoDao,
                                                   AtividadePendenteDao atividadePendenteDao, ImagemDao imagemDao,
                                                   TipoDao tipoDao, ResultadoDao resultadoDao) {

        FormacaoRepositorio repositorio = new FormacaoRepositorio(formandoDao, acaoFormacaoDao, atividadePendenteDao, imagemDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
