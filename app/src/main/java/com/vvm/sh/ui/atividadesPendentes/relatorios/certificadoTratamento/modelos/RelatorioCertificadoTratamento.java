package com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.modelos;

import androidx.room.ColumnInfo;

public class RelatorioCertificadoTratamento {


    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "certificadoValido")
    public boolean certificadoValido;

    @ColumnInfo(name = "assinaturaValido")
    public boolean assinaturaValido;

    @ColumnInfo(name = "emailValido")
    public boolean emailValido;

    @ColumnInfo(name = "sincronizacao")
    public boolean sincronizacao;

    @ColumnInfo(name = "valido")
    public boolean valido;

    @ColumnInfo(name = "idAtividade")
    public int id;

}
