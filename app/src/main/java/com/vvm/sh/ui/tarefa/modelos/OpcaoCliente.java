package com.vvm.sh.ui.tarefa.modelos;

import androidx.annotation.NonNull;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.Objects;

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
        return new OpcaoCliente(OPCAO_EMAIL, EMAIL, ICON_EMAIL);
    }
    public static OpcaoCliente crossSelling() {
        return new OpcaoCliente(OPCAO_CROSS_SELLING, CROSS_SELLING, ICON_CROSS_SELLING);
    }
    public static OpcaoCliente informacao() {
        return new OpcaoCliente(OPCAO_INFORMACAO, INFORMACAO, ICON_INFORMACAO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpcaoCliente that = (OpcaoCliente) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
