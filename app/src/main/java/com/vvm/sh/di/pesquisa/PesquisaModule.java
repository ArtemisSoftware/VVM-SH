package com.vvm.sh.di.pesquisa;

import android.app.Application;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaHigieneApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.PesquisaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.dao.TipoNovoDao;
import com.vvm.sh.baseDados.dao.VerificacaoEquipamentoDao;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;
import com.vvm.sh.di.informacaoSst.InformacaoSstScope;
import com.vvm.sh.di.opcoes.OpcoesScope;
import com.vvm.sh.repositorios.EquipamentoRepositorio;
import com.vvm.sh.repositorios.PesquisaRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.VersaoAppRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class PesquisaModule {

    @PesquisaScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @PesquisaScope
    @Provides
    static AtualizacaoDao provideAtualizacaoDao(VvmshBaseDados vvmshBaseDados){

        AtualizacaoDao dao = vvmshBaseDados.obterAtualizacaoDao();
        return dao;
    }



    @PesquisaScope
    @Provides
    static TipoNovoDao provideTipoNovoDao(VvmshBaseDados vvmshBaseDados){

        TipoNovoDao dao = vvmshBaseDados.obterTipoNovoDao();
        return dao;
    }



    @PesquisaScope
    @Provides
    static VerificacaoEquipamentoDao provideVerificacaoEquipamentoDao(VvmshBaseDados vvmshBaseDados){

        VerificacaoEquipamentoDao dao = vvmshBaseDados.obterVerificacaoEquipamentoDao();
        return dao;
    }


    @PesquisaScope
    @Provides
    static PesquisaDao providePesquisaDao(VvmshBaseDados vvmshBaseDados){

        PesquisaDao dao = vvmshBaseDados.obterPesquisaDao();
        return dao;
    }



    @PesquisaScope
    @Provides
    EquipamentoRepositorio provideEquipamentoRepositorio(@Named("idApi_") int idApi, TipoNovoDao tipoNovoDao, TipoDao tipoDao, VerificacaoEquipamentoDao verificacaoEquipamentoDao,
                                                         ResultadoDao resultadoDao) {

        EquipamentoRepositorio repositorio = new EquipamentoRepositorio(idApi, tipoNovoDao, tipoDao, verificacaoEquipamentoDao, resultadoDao);
        return repositorio;
    }






    @PesquisaScope
    @Provides
    VersaoAppRepositorio provideVersaoAppRepositorio(SegurancaHigieneApi segurancaAlimentarApi) {

        VersaoAppRepositorio repositorio = new VersaoAppRepositorio(segurancaAlimentarApi);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }






    @PesquisaScope
    @Provides
    TiposRepositorio provideTiposRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, SegurancaTrabalhoApi segurancaTrabalhoApi, AtualizacaoDao atualizacaoDao, TipoDao tipoDao) {

        TiposRepositorio repositorio = new TiposRepositorio(segurancaAlimentarApi, segurancaTrabalhoApi, atualizacaoDao, tipoDao);
        return repositorio;
    }



    @PesquisaScope
    @Provides
    PesquisaRepositorio providePesquisaRepositorio(@Named("idApi_") int idApi, PesquisaDao pesquisaDao) {

        PesquisaRepositorio repositorio = new PesquisaRepositorio(idApi, pesquisaDao);
        return repositorio;
    }

}
