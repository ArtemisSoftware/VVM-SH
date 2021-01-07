package com.vvm.sh.ui.informacaoSst.modelos;

import androidx.room.ColumnInfo;

public class RelatorioInformacaoSst {

//    @ColumnInfo(name = "clienteValido")
//    public boolean clienteValido;
//
//    @ColumnInfo(name = "trabalhoValido")
//    public boolean trabalhoValido;
//
//    @ColumnInfo(name = "numeroTrabalhos")
//    public int numeroTrabalhos;
//
//    @ColumnInfo(name = "email")
//    public String email;

    @ColumnInfo(name = "assinaturaValido")
    public boolean assinaturaValido;

    @ColumnInfo(name = "valido")
    public boolean valido;

    @ColumnInfo(name = "sincronizacao")
    public boolean sincronizacao;

}
