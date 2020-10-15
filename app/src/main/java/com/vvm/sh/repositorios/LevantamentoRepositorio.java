package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.MedidaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.RiscoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.RelatorioLevantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

public class LevantamentoRepositorio {


    private final int idApi;
    private final LevantamentoDao levantamentoDao;
    private final CategoriaProfissionalDao categoriaProfissionalDao;
    private final RiscoDao riscoDao;
    private final MedidaDao medidaDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;


    public LevantamentoRepositorio(int idApi, @NonNull LevantamentoDao levantamentoDao, @NonNull CategoriaProfissionalDao categoriaProfissionalDao,
                                   @NonNull RiscoDao riscoDao, @NonNull MedidaDao medidaDao,
                                   @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;

        this.levantamentoDao = levantamentoDao;
        this.riscoDao = riscoDao;
        this.categoriaProfissionalDao = categoriaProfissionalDao;


        this.medidaDao = medidaDao;
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
    public Observable<List<Levantamento>> obterLevantamentos(int idAtividade){
        return levantamentoDao.obterLevantamentos(idAtividade);
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

    public Flowable<Integer> remover(Levantamento levantamento){

        return Single.concatArray(
                medidaDao.removerMedidasRisco(levantamento.resultado.id),
                riscoDao.removerRiscos(levantamento.resultado.id),
                categoriaProfissionalDao.remover(levantamento.resultado.id, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS),
                levantamentoDao.remover(levantamento.resultado)
        );
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


    public Observable inserir(int idRegisto, List<Integer> medidasExistentes, List<Integer> medidasRecomendadas) {

        List<MedidaResultado> registosMedidasExistentes = new ArrayList<>();

        for(int idMedida : medidasExistentes){
            registosMedidasExistentes.add(new MedidaResultado(idRegisto, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS, idMedida));
        }

        List<MedidaResultado> registosMedidasRecomendadas = new ArrayList<>();

        for(int idMedida : medidasRecomendadas){
            registosMedidasRecomendadas.add(new MedidaResultado(idRegisto, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS, idMedida));
        }

        return Observable.zip(
                medidaDao.inserir(registosMedidasRecomendadas).toObservable(),
                medidaDao.inserir(registosMedidasExistentes).toObservable(),
                new BiFunction<List<Long>, List<Long>, Object>() {
                    @Override
                    public Object apply(List<Long> longs, List<Long> longs2) throws Exception {
                        return longs;
                    }
                }
        );
    }

    public Flowable<Object> atualizarRisco(RiscoResultado registo, List<MedidaResultado> medidasExistentesRegistas, List<MedidaResultado> medidasRecomendadasRegistas) {

            Flowable<Object> single = Single.merge(Arrays.asList(riscoDao.atualizar(registo),
                    medidaDao.remover(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS),
                    medidaDao.remover(registo.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS),
                    medidaDao.inserir(medidasExistentesRegistas),
                    medidaDao.inserir(medidasRecomendadasRegistas)));

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
                medidaDao.obterTipoMedidas(id, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS),
                medidaDao.obterTipoMedidas(id, TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS),
                new Function3<RiscoResultado, List<Tipo>, List<Tipo>, Risco>() {
                    @Override
                    public Risco apply(RiscoResultado risco, List<Tipo> medidasRecomendadas, List<Tipo> medidasExistentes) throws Exception {
                        Risco resultado = new Risco();

                        resultado.resultado = risco;
                        resultado.medidasRecomendadas = medidasRecomendadas;
                        resultado.medidasExistentes = medidasExistentes;

                        return resultado;
                    }
                });

        return maybe;
    }



        public Single<Object> duplicar(int idLevantamentoOriginal, LevantamentoRiscoResultado resultado) {

        return levantamentoDao.inserir(resultado)
                .map(new Function<Long, Object>() {
                    @Override
                    public Object apply(Long aLong) throws Exception {
                        return categoriaProfissionalDao.duplicarCategorias(idLevantamentoOriginal, ConversorUtil.converter_long_Para_int(aLong));
                    }
                })
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        return riscoDao.obterRiscos(idLevantamentoOriginal, idApi);
                    }
                });
    }

}
