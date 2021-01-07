package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.InformacaoSstDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.ui.informacaoSst.modelos.RelatorioInformacaoSst;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class InformacaoSstRepositorio implements Repositorio<ObrigacaoLegalResultado>{

    private final InformacaoSstDao informacaoSstDao;
    private final ImagemDao imagemDao;
    private final PdfDao pdfDao;
    public final ResultadoDao resultadoDao;
    private final int api;


    public InformacaoSstRepositorio(int api, @NonNull InformacaoSstDao informacaoSstDao,
                                    @NonNull ImagemDao imagemDao, @NonNull PdfDao pdfDao,
                                    @NonNull ResultadoDao resultadoDao) {

        this.informacaoSstDao = informacaoSstDao;
        this.resultadoDao = resultadoDao;
        this.imagemDao = imagemDao;
        this.pdfDao = pdfDao;
        this.api = api;
    }

    @Override
    public Single<Long> inserir(ObrigacaoLegalResultado item) {
        return informacaoSstDao.inserir(item);
    }

    public Single<Integer> remover(int idTarefa, int id){
        return informacaoSstDao.remover(idTarefa, id);
    }


    public Observable<List<ObrigacaoLegal>> obterObrigacoesLegais(int idTarefa){
        return informacaoSstDao.obterObrigacoesLegais(idTarefa, api);
    }


    public Observable<RelatorioInformacaoSst> obterRelatorioInformacaoSst(int idTarefa){
        return informacaoSstDao.obterRelatorioInformacaoSst(idTarefa);
    }





    @Override
    public Single<Integer> atualizar(ObrigacaoLegalResultado item) {
        return null;
    }

    @Override
    public Single<Integer> remover(ObrigacaoLegalResultado item) {
        return null;
    }
}
