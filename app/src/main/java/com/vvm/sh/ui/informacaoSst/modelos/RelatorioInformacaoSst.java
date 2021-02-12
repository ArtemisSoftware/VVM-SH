package com.vvm.sh.ui.informacaoSst.modelos;

import androidx.room.ColumnInfo;

public class RelatorioInformacaoSst {

    @ColumnInfo(name = "numeroObrigacaoes")
    public int numeroObrigacaoes;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "responsavelRelatorio")
    public String responsavelRelatorio;

    @ColumnInfo(name = "responsavel")
    public String responsavel;



    @ColumnInfo(name = "obrigacaoValido")
    public boolean obrigacaoValido;

    @ColumnInfo(name = "assinaturaValido")
    public boolean assinaturaValido;

    @ColumnInfo(name = "dadosClienteValido")
    public boolean dadosClienteValido;



    @ColumnInfo(name = "podeAssinar")
    public boolean podeAssinar;

    @ColumnInfo(name = "valido")
    public boolean valido;

    @ColumnInfo(name = "sincronizacao")
    public boolean sincronizacao;


}
