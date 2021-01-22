package com.vvm.sh.api.modelos.bd;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;

public class CertificadoBd {

    @Embedded
    public CertificadoTratamentoResultado resultado;


    @ColumnInfo(name = "idImagem")
    public int idImagem;
}
