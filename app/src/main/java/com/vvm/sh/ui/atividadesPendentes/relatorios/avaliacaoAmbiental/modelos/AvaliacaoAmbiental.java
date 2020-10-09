package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;

import java.util.List;

public class AvaliacaoAmbiental {

    @Embedded
    public AvaliacaoAmbientalResultado resultado;

    @ColumnInfo(name = "necessitaMedidas")
    public boolean necessitaMedidas;

    @ColumnInfo(name = "valido")
    public boolean valido;


    @ColumnInfo(name = "area")
    public String area;

    @ColumnInfo(name = "local")
    public String local;


    @ColumnInfo(name = "descricaoTipoIluminacao")
    public String tipoIluminacao;

    @Ignore
    public List<Tipo> categoriasProfissionais;

    @Ignore
    public List<Tipo> medidas;

    public String obterLocal() {
        return local.replaceAll("(\\r|\\n|\\t)", "");
    }
}
