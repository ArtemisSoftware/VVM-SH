package com.vvm.sh.ui.registoVisita.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class RelatorioRegistoVisita {

    @ColumnInfo(name = "clienteValido")
    public boolean clienteValido;

    @ColumnInfo(name = "trabalhoValido")
    public boolean trabalhoValido;

    @ColumnInfo(name = "numeroTrabalhos")
    public int numeroTrabalhos;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "assinaturaValido")
    public boolean assinaturaValido;

    @ColumnInfo(name = "valido")
    public boolean valido;

    @ColumnInfo(name = "sincronizacao")
    public boolean sincronizacao;

}
