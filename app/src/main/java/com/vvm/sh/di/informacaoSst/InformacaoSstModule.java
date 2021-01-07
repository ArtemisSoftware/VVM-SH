package com.vvm.sh.di.informacaoSst;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.InformacaoSstDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.di.registoVisita.RegistoVisitaScope;
import com.vvm.sh.repositorios.InformacaoSstRepositorio;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class InformacaoSstModule {


    @InformacaoSstScope
    @Provides
    static InformacaoSstDao provideInformacaoSstDao(VvmshBaseDados vvmshBaseDados){

        InformacaoSstDao dao = vvmshBaseDados.obterInformacaoSstDao();
        return dao;
    }


    @InformacaoSstScope
    @Provides
    static PdfDao providePdfDao(VvmshBaseDados vvmshBaseDados){

        PdfDao dao = vvmshBaseDados.obterPdfDao();
        return dao;
    }



    @InformacaoSstScope
    @Provides
    InformacaoSstRepositorio provideInformacaoSstRepositorio(int api, InformacaoSstDao informacaoSstDao, ImagemDao imagemDao, PdfDao pdfDao, ResultadoDao resultadoDao) {

        InformacaoSstRepositorio repositorio = new InformacaoSstRepositorio(api, informacaoSstDao, imagemDao, pdfDao, resultadoDao);
        return repositorio;
    }

}
