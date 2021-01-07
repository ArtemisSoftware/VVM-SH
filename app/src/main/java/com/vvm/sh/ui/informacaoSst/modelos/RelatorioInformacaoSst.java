package com.vvm.sh.ui.informacaoSst.modelos;

import androidx.room.ColumnInfo;

public class RelatorioInformacaoSst {


    @ColumnInfo(name = "numeroObrigacaoes")
    public int numeroObrigacaoes;


    @ColumnInfo(name = "obrigacaoValido")
    public int obrigacaoValido;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "assinaturaValido")
    public boolean assinaturaValido;

    @ColumnInfo(name = "valido")
    public boolean valido;

//    @ColumnInfo(name = "sincronizacao")
//    public boolean sincronizacao;

}
