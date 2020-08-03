package com.vvm.sh.ui.tarefa.modelos;

import androidx.annotation.NonNull;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.constantes.Identificadores;
import static com.vvm.sh.util.constantes.Identificadores.OpcoesCliente.*;

public class OpcaoCliente {


    public int id, icon;
    public String descricao, detalhe;
    public boolean ativo;

    public OpcaoCliente(int id, String descricao, int icon) {
        this.id = id;
        this.descricao = descricao;
        this.icon = icon;
        this.ativo = false;
    }


    public static OpcaoCliente email() {
        return new OpcaoCliente(OPCAO_EMAIL, EMAIL, EMAIL_ICON);
    }
}
