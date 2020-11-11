package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.MedidaDao;
import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.RiscoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.RelatorioLevantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import kotlin.jvm.functions.Function2;

public class LevantamentoRepositorio {


    private final int idApi;
    private final LevantamentoDao levantamentoDao;
    private final CategoriaProfissionalDao categoriaProfissionalDao;

    private final PropostaPlanoAcaoDao propostaPlanoAcaoDao;
    private final RiscoDao riscoDao;
    private final MedidaDao medidaDao;

    private final ImagemDao imagemDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;


    public LevantamentoRepositorio(int idApi, @NonNull LevantamentoDao levantamentoDao, @NonNull CategoriaProfissionalDao categoriaProfissionalDao,
                                   @NonNull RiscoDao riscoDao, @NonNull MedidaDao medidaDao, @NonNull PropostaPlanoAcaoDao propostaPlanoAcaoDao,
                                   @NonNull ImagemDao imagemDao, @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;

        this.levantamentoDao = levantamentoDao;
        this.propostaPlanoAcaoDao = propostaPlanoAcaoDao;
        this.riscoDao = riscoDao;
        this.categoriaProfissionalDao = categoriaProfissionalDao;


        this.medidaDao = medidaDao;
        this.imagemDao = imagemDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }

    /**
     * Metodo que permite inserir um registo
     * @param registo os dados
     * @return o resultado da operação
     */
    public Single<Long> inserir(LevantamentoRiscoResultado registo){
        return levantamentoDao.inserir(registo);
    }

    public Single<Long> inserir(RiscoResultado registo){
        return riscoDao.inserir(registo);
    }


    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados
     * @return o resultado da operação
     */
    public Single<Integer> atualizar(LevantamentoRiscoResultado registo){
        return levantamentoDao.atualizar(registo);
    }


    /**
     * Metodo que permite inserir registos
     * @param registos os dados
     * @return o resultado da operação
     */
    public Single<List<Long>> inserir(List<CategoriaProfissionalResultado> registos){
        return categoriaProfissionalDao.inserir(registos);
    }

    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados
     * @return o resultado da operação
     */
    public Single<Integer> atualizar(CategoriaProfissionalResultado registo){
        return categoriaProfissionalDao.atualizar(registo);
    }



    /**
     * Metodo que permite obter os levantamentos
     * @param idAtividade o identificador da atividade
     * @return uma lista de registos
     */
    public Observable<List<Levantamento>> obterLevantamentos(int idAtividade, int pagina){
        return levantamentoDao.obterLevantamentos(idAtividade, idApi, pagina);
    }

    /**
     * Metodo que permite obter o levantamento
     * @param id o identificador do levantamento
     * @return um registo
     */
    public Maybe<LevantamentoRiscoResultado> obterLevantamento(int id){
        return levantamentoDao.obterLevantamento(id);
    }

    /**
     * Metodo que permite obter as categorias profissionais
     * @param id o identificador do registo ao qual as categorias profissionais pertencem
     * @return os registos
     */
    public Observable<List<CategoriaProfissional>> obterCategoriasProfissionais(int id){
        return categoriaProfissionalDao.obterCategoriasProfissionais(id, idApi, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS) ;
    }


    public Single<List<Tipo>> obterTipoCategoriasProfissionais(List<Integer> ids){
        return categoriaProfissionalDao.obterTiposCategorias(ids, idApi) ;
    }

    public Observable<RelatorioLevantamento> obterRelatorio(int id){
        return levantamentoDao.obterRelatorio(id);
    }


    public Observable<List<Risco>> obterRiscos(int id){
        return riscoDao.obterRiscos(id, idApi);
    }





    /**
     * Metodo que permite obter a listagem de modelos
     * @return uma lista
     */
    public Single<List<Tipo>> obterModelos(int idAtividade){
        return levantamentoDao.obterModelos(idAtividade, TiposUtil.MetodosTipos.TEMPLATE_AVALIACAO_RISCOS, idApi);
    }

    public Single<Integer> remover(CategoriaProfissionalResultado categoria) {
        return categoriaProfissionalDao.remover(categoria);
    }

    public Single<List<Integer>> removerLevantamento(Levantamento levantamento){

        return Single.concatArray(
                riscoDao.removerImagemLevantamento(levantamento.resultado.id),
                propostaPlanoAcaoDao.remover(levantamento.resultado.id, Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO),
                medidaDao.removerMedidasRisco_Levantamento(levantamento.resultado.id),
                riscoDao.removerRiscos(levantamento.resultado.id),
                categoriaProfissionalDao.remover(levantamento.resultado.id, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS),
                levantamentoDao.remover(levantamento.resultado)
        )
        .toList();
    }


    public Single<List<Integer>> removerLevantamentos(List<Integer> idsLevantamentos){

        return Single.concatArray(
                riscoDao.removerImagemLevantamento(idsLevantamentos),
                propostaPlanoAcaoDao.remover(idsLevantamentos, Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO),
                medidaDao.removerMedidasRisco_Levantamento(idsLevantamentos),
                riscoDao.removerRiscos(idsLevantamentos),
                categoriaProfissionalDao.remover(idsLevantamentos, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS),
                levantamentoDao.remover(idsLevantamentos)
        )
                .toList();
    }




    public Single<List<Integer>> removerRisco(RiscoResultado risco){

        return Single.concatArray(
                imagemDao.remover(risco.id, Identificadores.Imagens.IMAGEM_RISCO),
                propostaPlanoAcaoDao.removerRisco(risco.id, Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO),
                medidaDao.removerMedidasRisco(risco.id),
                riscoDao.remover(risco)
        )
                .toList();
    }



    public Single<List<Tipo>> obterTipos(String tipo){
        return tipoDao.obterTipos_(tipo, idApi);
    }

    public Single<List<Tipo>> obterTipos(String tipo, int idPai){
        return tipoDao.obterTipos_(tipo, idApi, idPai + "");
    }



    public Flowable<List<Tipo>> obterTipos_Incluir(String metodo, List<Integer> resultado){
        return tipoDao.obterTipos_Incluir(metodo, resultado, idApi);
    }


    public Observable inserir(int idAtividade, int idRegisto, List<Integer> medidasExistentes, List<Integer> medidasRecomendadas, ImagemResultado imagemResultado) {

        List<MedidaResultado> registosMedidas = new ArrayList<>();

        for(int idMedida : medidasExistentes){
            registosMedidas.add(new MedidaResultado(idRegisto, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS, idMedida));
        }

        for(int idMedida : medidasRecomendadas){
            registosMedidas.add(new MedidaResultado(idRegisto, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS, idMedida));
        }


        List<PropostaPlanoAcaoResultado> propostaPlanoAcaoResultados = new ArrayList<>();

        for(int idMedida : medidasRecomendadas){
            propostaPlanoAcaoResultados.add(new PropostaPlanoAcaoResultado(idAtividade, idRegisto, idMedida));
        }


        if(imagemResultado.imagem == null) {

            return Observable.zip(
                    propostaPlanoAcaoDao.inserir(propostaPlanoAcaoResultados).toObservable(),
                    medidaDao.inserir(registosMedidas).toObservable(),
                    new BiFunction<List<Long>, List<Long>, Object>() {
                        @Override
                        public Object apply(List<Long> longs, List<Long> longs2) throws Exception {
                            return longs2;
                        }
                    }
            );

        }

        if(imagemResultado.imagem.length == 0) {

            return Observable.zip(
                    propostaPlanoAcaoDao.inserir(propostaPlanoAcaoResultados).toObservable(),
                    medidaDao.inserir(registosMedidas).toObservable(),
                    new BiFunction<List<Long>, List<Long>, Object>() {
                        @Override
                        public Object apply(List<Long> longs, List<Long> longs2) throws Exception {
                            return longs2;
                        }
                    }
            );

        }
        else{
            return Observable.zip(
                    imagemDao.inserir(imagemResultado).toObservable(),
                    propostaPlanoAcaoDao.inserir(propostaPlanoAcaoResultados).toObservable(),
                    medidaDao.inserir(registosMedidas).toObservable(),
                    new Function3<Long, List<Long>, List<Long>, Object>() {
                        @Override
                        public Object apply(Long aLong, List<Long> longs, List<Long> longs2) throws Exception {
                            return longs2;
                        }
                    }
            );
        }
    }

    public Flowable<Object> atualizarRisco(RiscoResultado registo, List<MedidaResultado> medidasRegistas, List<PropostaPlanoAcaoResultado> propostasRegistas, ImagemResultado imagemResultado) {

        List<Single<?>> acoes = new LinkedList<>(Arrays.asList(
                imagemDao.remover(registo.id, Identificadores.Imagens.IMAGEM_RISCO),
                riscoDao.atualizar(registo),
                propostaPlanoAcaoDao.removerQuestoes(registo.id, Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO),
                medidaDao.remover(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS),
                medidaDao.remover(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS),
                medidaDao.inserir(medidasRegistas),
                propostaPlanoAcaoDao.inserir(propostasRegistas)));



        if(imagemResultado.imagem != null) {
            if(imagemResultado.imagem.length != 0){
                acoes.add(imagemDao.inserir(imagemResultado));
            }
        }

        Flowable<Object> single = Single.merge(acoes);

        return single;

    }


    /**
     * Metodo que permite obter as medidas
     * @param id o identificador do registo
     * @param origem a origem do relatorio
     * @return uma lista de medidas
     */
    public Maybe<List<Tipo>> obterMedidas(int id, String tipo, int origem){
        return medidaDao.obterTipoMedidas(id, tipo, origem);
    }


    public Maybe<Risco> obterRisco(int id) {

        Maybe<Risco> maybe = Maybe.zip(
                riscoDao.obterRisco(id),
                imagemDao.obterImagem(id, Identificadores.Imagens.IMAGEM_RISCO),
                medidaDao.obterTipoMedidas(id, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS),
                medidaDao.obterTipoMedidas(id, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS),
                new Function4<RiscoResultado, List<ImagemResultado>, List<Tipo>, List<Tipo>, Risco>() {
                    @Override
                    public Risco apply(RiscoResultado risco, List<ImagemResultado> imagens, List<Tipo> medidasRecomendadas, List<Tipo> medidasExistentes) throws Exception {
                        Risco resultado = new Risco();

                        resultado.resultado = risco;
                        resultado.imagem = imagens;
                        resultado.medidasRecomendadas = medidasRecomendadas;
                        resultado.medidasExistentes = medidasExistentes;

                        return resultado;
                    }
                }
        );

        return maybe;
    }


    public Completable inserirModelo(int idAtividade, int idModelo) {

        Completable completable = Completable.merge(Arrays.asList(
                levantamentoDao.inserirModelo(idAtividade, idModelo),
                riscoDao.inserirModelo(idAtividade, idModelo),
                medidaDao.inserirMedidasRisco(idAtividade, idModelo, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES, idApi),
                medidaDao.inserirMedidasRisco(idAtividade, idModelo, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS, idApi),
                propostaPlanoAcaoDao.inserirModelo(idAtividade, idModelo)));

        return completable;

    }


    public Completable removerModelo(int idAtividade, int idModelo) {

        Completable completable = Completable.merge(Arrays.asList(

                propostaPlanoAcaoDao.removerModelo(idAtividade, idModelo, Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO),
                medidaDao.removerMedidasModelo(idAtividade, idModelo),
                categoriaProfissionalDao.removerModelo(idAtividade, idModelo),
                riscoDao.removerImagemModelo(idAtividade, idModelo),
                levantamentoDao.removerModelo(idAtividade, idModelo)));

        return completable;

    }


    public Completable inserirModeloCategoriasProfissionais(int idAtividade, int idModelo, List<CategoriaProfissionalResultado> resultado) {

        CompletableSource[] lolo = new CompletableSource[resultado.size()];

        for(int index = 0; index < resultado.size(); ++index){
            lolo[index] = categoriaProfissionalDao.inserirModeloCategoriaProfissional(idAtividade, idModelo, resultado.get(index).origem, resultado.get(index).idCategoriaProfissional, resultado.get(index).homens, resultado.get(index).mulheres);
        }

        Completable completable = Completable.merge(Arrays.asList(lolo));

        return completable;

    }


}
