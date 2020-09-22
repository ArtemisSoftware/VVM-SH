package com.vvm.sh.di.registoVisita;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class RegistoVisitaModule {


    @RegistoVisitaScope
    @Provides
    static RegistoVisitaDao provideRegistoVisitaDao(VvmshBaseDados vvmshBaseDados){

        RegistoVisitaDao dao = vvmshBaseDados.obterRegistoVisitaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @RegistoVisitaScope
    @Provides
    static TrabalhosRealizadosDao providTrabalhosRealizadosDao(VvmshBaseDados vvmshBaseDados){

        TrabalhosRealizadosDao dao = vvmshBaseDados.obterTrabalhosRealizadosDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }



    @RegistoVisitaScope
    @Provides
    RegistoVisitaRepositorio provideRegistoVisitaRepositorio(int api, RegistoVisitaDao registoVisitaDao,
                                                             TrabalhosRealizadosDao trabalhosRealizadosDao, ImagemDao imagemDao, ResultadoDao resultadoDao) {

        RegistoVisitaRepositorio repositorio = new RegistoVisitaRepositorio(api, registoVisitaDao, trabalhosRealizadosDao, imagemDao,resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}