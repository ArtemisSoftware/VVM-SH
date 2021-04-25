package com.vvm.sh.di.atividadesPendentes.formacao;

import android.app.Application;

import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.di.ocorrencias.OcorrenciasScope;
import com.vvm.sh.repositorios.FormacaoRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class FormacaoModule {

    @FormacaoScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }


    @FormacaoScope
    @Provides
    static FormandoDao provideFormandoDao(VvmshBaseDados vvmshBaseDados){

        FormandoDao dao = vvmshBaseDados.obterFormandoDao();
        return dao;
    }


    @FormacaoScope
    @Provides
    static AcaoFormacaoDao provideAcaoFormacao(VvmshBaseDados vvmshBaseDados){

        AcaoFormacaoDao dao = vvmshBaseDados.obterAcaoFormacaoDao();
        return dao;
    }


    @FormacaoScope
    @Provides
    static AtividadePendenteDao provideAtividadePendenteResultadoDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteDao dao = vvmshBaseDados.obterAtividadePendenteDao();
        return dao;
    }

    @FormacaoScope
    @Provides
    FormacaoRepositorio provideFormacaoRepositorio(@Named("idApi_") int idApi, FormandoDao formandoDao, AcaoFormacaoDao acaoFormacaoDao,
                                                   AtividadePendenteDao atividadePendenteDao, ImagemDao imagemDao,
                                                   TipoDao tipoDao, ResultadoDao resultadoDao) {

        FormacaoRepositorio repositorio = new FormacaoRepositorio(idApi, formandoDao, acaoFormacaoDao, atividadePendenteDao, imagemDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
