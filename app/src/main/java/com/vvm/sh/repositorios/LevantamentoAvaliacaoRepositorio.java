package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.LevantamentoAvaliacaoDao;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;

import java.util.List;

public class LevantamentoAvaliacaoRepositorio {


    private final LevantamentoAvaliacaoDao levantamentoAvaliacaoDao;


    public LevantamentoAvaliacaoRepositorio(@NonNull LevantamentoAvaliacaoDao levantamentoAvaliacaoDao) {

        this.levantamentoAvaliacaoDao = levantamentoAvaliacaoDao;
    }

    public void duplicarCategoriasProfissionais(int idLevantamentoOriginal, int idLevantamentoNovo){
        levantamentoAvaliacaoDao.duplicarCategorias(idLevantamentoOriginal, idLevantamentoNovo);
    }

    public void duplicarRiscos(int idLevantamentoOriginal, int idLevantamentoNovo){
        levantamentoAvaliacaoDao.duplicarRiscos(idLevantamentoOriginal, idLevantamentoNovo);
    }

    public List<MedidaResultado> obterMedidas(int idLevantamentoOriginal, int origem){
        return levantamentoAvaliacaoDao.obterMedidas(idLevantamentoOriginal, origem);
    }

    public List<RiscoResultado> obterRiscos(int idLevantamentoNovo){
        return levantamentoAvaliacaoDao.obterRiscos(idLevantamentoNovo);
    }

    public void duplicarMedidas(int id, int origem, int idMedida){
        levantamentoAvaliacaoDao.duplicarMedidas(id, origem, idMedida);
    }

    public void duplicarPlanoAcao(int idAtividade, int idLevantamentoNovo, int origem){
        levantamentoAvaliacaoDao.duplicarPlanoAcao(idAtividade, idLevantamentoNovo, origem);
    }
}
