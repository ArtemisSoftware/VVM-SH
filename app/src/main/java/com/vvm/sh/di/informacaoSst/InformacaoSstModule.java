package com.vvm.sh.di.informacaoSst;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.InformacaoSstDao;
import com.vvm.sh.baseDados.dao.ObrigacoesLegaisDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.di.atividadesPendentes.certificadoTratamento.CertificadoTratamentoScope;
import com.vvm.sh.di.registoVisita.RegistoVisitaScope;
import com.vvm.sh.repositorios.InformacaoSstRepositorio;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.util.ResultadoId;

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
    static ObrigacoesLegaisDao provideObrigacoesLegaisDao(VvmshBaseDados vvmshBaseDados){

        ObrigacoesLegaisDao dao = vvmshBaseDados.obterObrigacoesLegaisDao();
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
    static TransferenciasDao provideTransferenciasDao(VvmshBaseDados vvmshBaseDados){

        TransferenciasDao dao = vvmshBaseDados.obterTransferenciasDao();
        return dao;
    }



    @InformacaoSstScope
    @Provides
    TransferenciasRepositorio provideTransferenciasRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, SegurancaTrabalhoApi segurancaTrabalhoApi,
                                                               TransferenciasDao transferenciasDao) {

        TransferenciasRepositorio repositorio = new TransferenciasRepositorio(segurancaAlimentarApi, segurancaTrabalhoApi, transferenciasDao);
        return repositorio;
    }





    @InformacaoSstScope
    @Provides
    InformacaoSstRepositorio provideInformacaoSstRepositorio(int api, InformacaoSstDao informacaoSstDao, ObrigacoesLegaisDao obrigacoesLegaisDao, ImagemDao imagemDao, PdfDao pdfDao, ResultadoDao resultadoDao) {

        InformacaoSstRepositorio repositorio = new InformacaoSstRepositorio(api, informacaoSstDao, obrigacoesLegaisDao, imagemDao, pdfDao, resultadoDao, ResultadoId.INFORMCAO_SST);
        return repositorio;
    }

}
