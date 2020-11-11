package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.ui.imagens.modelos.ImagemRegisto;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ImagemRepositorio implements Repositorio<ImagemResultado>{


    private final ImagemDao imagemDao;
    public final ResultadoDao resultadoDao;

    public ImagemRepositorio(@NonNull ImagemDao imagemDao, @NonNull ResultadoDao resultadoDao) {

        this.imagemDao = imagemDao;
        this.resultadoDao = resultadoDao;
    }

    public Observable<List<ImagemRegisto>> obterImagemLevantamento(int idLevantamento) {
        return imagemDao.obterImagemLevantamento(idLevantamento);
    }

    public Observable<List<ImagemRegisto>> obterGaleria(int id, int origem) {
        return imagemDao.obterGaleria(id, origem);
    }

    public Observable<List<ImagemRegisto>> obterCapasRelatorio(int idTarefa) {
        return imagemDao.obterGaleria(idTarefa);
    }

    public Completable fixarCapaRelatorio(int idTarefa, int idImagem){

        Completable removerCapaRelatorio = imagemDao.removerCapaRelatorio(idTarefa);
        Completable inserirCapaRelatorio = imagemDao.fixarCapaRelatorio(idImagem);

        Completable completable = Completable.concatArray(removerCapaRelatorio, inserirCapaRelatorio);
        return completable;
    }

    public Completable removerCapaRelatorio(int idTarefa){
        return imagemDao.removerCapaRelatorio(idTarefa);
    }



    @Override
    public Single<Long> inserir(ImagemResultado item) {
        return null;
    }

    @Override
    public Single<Integer> atualizar(ImagemResultado item) {
        return null;
    }

    @Override
    public Single<Integer> remover(ImagemResultado item) {
        return imagemDao.remover(item);
    }
}
