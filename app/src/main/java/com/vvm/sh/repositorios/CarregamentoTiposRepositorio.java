package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;

import java.util.List;

public class CarregamentoTiposRepositorio {


    private final AtualizacaoDao atualizacaoDao;
    private final TipoDao tipoDao;

    public CarregamentoTiposRepositorio(@NonNull AtualizacaoDao atualizacaoDao, @NonNull TipoDao tipoDao) {

        this.atualizacaoDao = atualizacaoDao;
        this.tipoDao = tipoDao;
    }



    /**
     * Metodo que permite atualizar um tipo<br>
     *     1->Atualizar timestamp<br>
     *     2->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param tiposNovos tipos novos a inserir
     * @param tiposAlterados tipos a alterar
     */
    public void atualizarTipo(Atualizacao atualizacao, List<Tipo> tiposNovos, List<Tipo> tiposAlterados){

        if(atualizacaoDao.existeRegisto(atualizacao.descricao) == true){
            atualizacaoDao.atualizarRegisto(atualizacao);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacao);
        }

        tipoDao.inserir(tiposNovos);
        tipoDao.atualizar(tiposAlterados);
    }


    /**
     * Metodo que permite carregar um tipo<br>
     *     1->Remover a atualizacao e os dados<br>
     *     2->Inserir novo timestamp<br>
     *     3->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param dadosNovos os dados a inserir
     * @param dadosAlteradaos os dados a alterar
     */
    public void carregarTipo(Atualizacao atualizacao, List<Tipo> dadosNovos, List<Tipo> dadosAlteradaos){

        atualizacaoDao.remover(atualizacao.descricao);
        tipoDao.removerTipo(atualizacao.descricao);

        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserir(dadosNovos);
        tipoDao.atualizar(dadosAlteradaos);
    }



    /**
     * Metodo que permite atualizar um tipo<br>
     *     1->Atualizar timestamp<br>
     *     2->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param tiposNovos tipos novos a inserir
     * @param tiposAlterados tipos a alterar
     */
    public void atualizarTipoAtividadesPlaneaveis(Atualizacao atualizacao, List<TipoAtividadePlaneavel> tiposNovos, List<TipoAtividadePlaneavel> tiposAlterados){

        if(atualizacaoDao.existeRegisto(atualizacao.descricao) == true){
            atualizacaoDao.atualizarRegisto(atualizacao);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacao);
        }

        tipoDao.inserirAtividadesPlaneaiveis(tiposNovos);
        tipoDao.atualizarAtividadesPlaneaiveis(tiposAlterados);
    }


}
