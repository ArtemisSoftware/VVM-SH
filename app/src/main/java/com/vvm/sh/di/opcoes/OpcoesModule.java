package com.vvm.sh.di.opcoes;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaHigieneApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;

import dagger.Module;
import dagger.Provides;


@Module
public class OpcoesModule {


    @OpcoesScope
    @Provides
    VersaoAppRepositorio provideVersaoAppRepositorio(SegurancaHigieneApi segurancaHigieneApi) {

        VersaoAppRepositorio repositorio = new VersaoAppRepositorio(segurancaHigieneApi);
        return repositorio;
    }



    @OpcoesScope
    @Provides
    static AtualizacaoDao provideAtualizacaoDao(VvmshBaseDados vvmshBaseDados){

        AtualizacaoDao dao = vvmshBaseDados.obterAtualizacaoDao();
        return dao;
    }




    @OpcoesScope
    @Provides
    TiposRepositorio provideTiposRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, SegurancaTrabalhoApi segurancaTrabalhoApi,
                                             AtualizacaoDao atualizacaoDao, TipoDao tipoDao) {

        TiposRepositorio repositorio = new TiposRepositorio(segurancaAlimentarApi, segurancaTrabalhoApi, atualizacaoDao, tipoDao);
        return repositorio;
    }

    @OpcoesScope
    @Provides
    CarregamentoTiposRepositorio provideCarregamentoTiposRepositorio(AtualizacaoDao atualizacaoDao, TipoDao tipoDao) {

        CarregamentoTiposRepositorio repositorio = new CarregamentoTiposRepositorio(atualizacaoDao, tipoDao);
        return repositorio;
    }
}
