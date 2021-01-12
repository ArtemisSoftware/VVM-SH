package com.vvm.sh.di.atividadesPendentes.certificadoTratamento;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.CertificadoTratamentoDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoScope;
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





//    @CertificadoTratamentoScope
//    @Provides
//    CertificadoTratamentoRepositorio provideCertificadoTratamentoRepositorio(CertificadoTratamentoDao certificadoTratamentoDao, ImagemDao imagemDao,
//                                                   TipoDao tipoDao, ResultadoDao resultadoDao) {
//
//        CertificadoTratamentoRepositorio repositorio = new CertificadoTratamentoRepositorio(certificadoTratamentoDao, imagemDao, tipoDao, resultadoDao);
//        return repositorio;
//    }

}
