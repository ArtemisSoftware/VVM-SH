package com.vvm.sh.di.transferencias;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDadosDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.TipoNovoDao;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.dao.UploadDao;
import com.vvm.sh.di.opcoes.OpcoesScope;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.repositorios.RedeRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.repositorios.UploadRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class TransferenciasModule {



    @TransferenciasScope
    @Provides
    static TransferenciasDao provideTransferenciasDaoo(VvmshBaseDados vvmshBaseDados){

        TransferenciasDao dao = vvmshBaseDados.obterTransferenciasDao();
        return dao;
    }


    @TransferenciasScope
    @Provides
    static UploadDao provideUploadDao(VvmshBaseDados vvmshBaseDados){

        UploadDao dao = vvmshBaseDados.obterUploadDao();
        return dao;
    }


    @TransferenciasScope
    @Provides
    static AtualizacaoDao provideAtualizacaoDao(VvmshBaseDados vvmshBaseDados){

        AtualizacaoDao dao = vvmshBaseDados.obterAtualizacaoDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }

    @TransferenciasScope
    @Provides
    static TipoNovoDao provideTipoNovoDao(VvmshBaseDados vvmshBaseDados){

        TipoNovoDao dao = vvmshBaseDados.obterTipoNovoDao();
        return dao;
    }



    @TransferenciasScope
    @Provides
    TransferenciasRepositorio provideTransferenciasRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, SegurancaTrabalhoApi segurancaTrabalhoApi,
                                                       TransferenciasDao transferenciasDao) {

        TransferenciasRepositorio repositorio = new TransferenciasRepositorio(segurancaAlimentarApi, segurancaTrabalhoApi, transferenciasDao);
        return repositorio;
    }


    @TransferenciasScope
    @Provides
    TiposRepositorio provideTiposRepositorio(SegurancaAlimentarApi segurancaAlimentarApi, SegurancaTrabalhoApi segurancaTrabalhoApi, AtualizacaoDao atualizacaoDao, TipoDao tipoDao) {

        TiposRepositorio repositorio = new TiposRepositorio(segurancaAlimentarApi, segurancaTrabalhoApi, atualizacaoDao, tipoDao);
        return repositorio;
    }


    @TransferenciasScope
    @Provides
    UploadRepositorio provideUploadRepositorio(UploadDao uploadDao) {

        UploadRepositorio repositorio = new UploadRepositorio(uploadDao);
        return repositorio;
    }

    @TransferenciasScope
    @Provides
    CarregamentoTiposRepositorio provideCarregamentoTiposRepositorio(AtualizacaoDao atualizacaoDao, TipoDao tipoDao, TipoDadosDao tipoDadosDao, TipoNovoDao tipoNovoDao) {

        CarregamentoTiposRepositorio repositorio = new CarregamentoTiposRepositorio(atualizacaoDao, tipoDao, tipoDadosDao, tipoNovoDao);
        return repositorio;
    }


    @TransferenciasScope
    @Provides
    DownloadTrabalhoRepositorio provideDownloadTrabalhoRepositorio(TransferenciasDao transferenciasDao) {

        DownloadTrabalhoRepositorio repositorio = new DownloadTrabalhoRepositorio(transferenciasDao);
        return repositorio;
    }

}
