package com.vvm.sh.ui.pesquisa.modelos;

import com.vvm.sh.baseDados.entidades.Tipo;

import java.util.List;

public class PesquisaTipos {

    public List<Tipo> registos;
    public List<Tipo> registado;

    public PesquisaTipos(List<Tipo> registos, List<Tipo> registado) {
        this.registos = registos;
        this.registado = registado;
    }
}
