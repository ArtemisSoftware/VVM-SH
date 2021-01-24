package com.vvm.sh.di.atividadesPendentes.certificadoTratamento;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.CertificadoTratamentoDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoScope;
import com.vvm.sh.di.registoVisita.RegistoVisitaScope;
import com.vvm.sh.repositorios.CertificadoTratamentoRepositorio;
import com.vvm.sh.repositorios.FormacaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class CertificadoTratamentoModule {



    @CertificadoTratamentoScope
    @Provides
    static CertificadoTratamentoDao provideCertificadoTratamentoDao(VvmshBaseDados vvmshBaseDados){

        CertificadoTratamentoDao dao = vvmshBaseDados.obterCertificadoTratamentoDao();
        return dao;
    }



    @RegistoVisitaScope
    @Provides
    static PdfDao providePdfDao(VvmshBaseDados vvmshBaseDados){

        PdfDao dao = vvmshBaseDados.obterPdfDao();
        return dao;
    }



    @CertificadoTratamentoScope
    @Provides
    CertificadoTratamentoRepositorio provideCertificadoTratamentoRepositorio(int idApi, CertificadoTratamentoDao certificadoTratamentoDao, ImagemDao imagemDao,
                                                                             ResultadoDao resultadoDao, PdfDao pdfDao) {

        CertificadoTratamentoRepositorio repositorio = new CertificadoTratamentoRepositorio(idApi, certificadoTratamentoDao, imagemDao, pdfDao, resultadoDao);
        return repositorio;
    }

}
