package com.vvm.sh.di.imagens;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoScope;
import com.vvm.sh.repositorios.FormacaoRepositorio;
import com.vvm.sh.repositorios.ImagemRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class ImagensModule {


    @ImagensScope
    @Provides
    ImagemRepositorio provideFormacaoRepositorio(ImagemDao imagemDao, ResultadoDao resultadoDao) {

        ImagemRepositorio repositorio = new ImagemRepositorio(imagemDao, resultadoDao);
        return repositorio;
    }

}
