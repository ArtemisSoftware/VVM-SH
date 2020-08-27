package com.vvm.sh.ui.ocorrencias.modelos;

import androidx.room.Ignore;

import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;

public class OcorrenciaBase {

    @Ignore
    public OcorrenciaResultado resultado;

    @Ignore
    public Tipo tipo;

    public int ultimoRegisto;

    public int id, idResultado, idTarefa;
    public String descricao, codigo, detalhe, observacao;
    public boolean fiscalizado;

    public OcorrenciaBase(int id, String descricao, String codigo, String detalhe, int idTarefa,
                          int idResultado, String observacao, boolean fiscalizado, int ultimoRegisto) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.detalhe = detalhe;
        this.idResultado = idResultado;
        this.observacao = observacao;
        this.fiscalizado = fiscalizado;
        this.ultimoRegisto = ultimoRegisto;


        tipo = new Tipo(id, descricao, codigo, "", 1, detalhe, "");
        resultado = new OcorrenciaResultado(idTarefa, idResultado, observacao, fiscalizado, "");

    }
}
