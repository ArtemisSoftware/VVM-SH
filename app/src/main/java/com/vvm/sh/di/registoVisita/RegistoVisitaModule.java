package com.vvm.sh.di.registoVisita;

import android.app.Application;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.di.pesquisa.PesquisaScope;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class RegistoVisitaModule {

    @RegistoVisitaScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @RegistoVisitaScope
    @Provides
    static RegistoVisitaDao provideRegistoVisitaDao(VvmshBaseDados vvmshBaseDados){

        RegistoVisitaDao dao = vvmshBaseDados.obterRegistoVisitaDao();
        return dao;
    }


    @RegistoVisitaScope
    @Provides
    static TrabalhosRealizadosDao providTrabalhosRealizadosDao(VvmshBaseDados vvmshBaseDados){

        TrabalhosRealizadosDao dao = vvmshBaseDados.obterTrabalhosRealizadosDao();
        return dao;
    }


    @RegistoVisitaScope
    @Provides
    static PdfDao providePdfDao(VvmshBaseDados vvmshBaseDados){

        PdfDao dao = vvmshBaseDados.obterPdfDao();
        return dao;
    }



    @RegistoVisitaScope
    @Provides
    RegistoVisitaRepositorio provideRegistoVisitaRepositorio(@Named("idApi_") int api, RegistoVisitaDao registoVisitaDao,
                                                             TrabalhosRealizadosDao trabalhosRealizadosDao, ImagemDao imagemDao, PdfDao pdfDao, ResultadoDao resultadoDao) {

        RegistoVisitaRepositorio repositorio = new RegistoVisitaRepositorio(api, registoVisitaDao, trabalhosRealizadosDao, imagemDao, pdfDao, resultadoDao);
        return repositorio;
    }

}
