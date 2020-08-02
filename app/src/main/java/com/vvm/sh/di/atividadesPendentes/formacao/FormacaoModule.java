package com.vvm.sh.di.atividadesPendentes.formacao;

import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.AtividadePendenteResultadoDao;
import com.vvm.sh.baseDados.FormandoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesScope;
import com.vvm.sh.di.atividadesPendentes.FormacaoScope;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
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
    FormacaoRepositorio provideFormacaoRepositorio(FormandoDao formandoDao) {

        FormacaoRepositorio repositorio = new FormacaoRepositorio(formandoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
