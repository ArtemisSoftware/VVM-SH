package com.vvm.sh.ui.agenda.adaptadores;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.OpcaoCliente;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

public class OpcaoClienteRecyclerAdapter extends ItemRecyclerAdapter {

    public static final int OPCAO_INFORMACAO = 1;
    public static final int OPCAO_CROSS_SELLING = 2;
    public static final int OPCAO_SINISTRALIDADE = 3;
    public static final int OPCAO_EXTINTORES = 4;
    public static final int OPCAO_EMAIL = 5;

    private OnItemListener onItemListener;


    public OpcaoClienteRecyclerAdapter(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;

    }

    @Override
    protected int obterLayout(int viewType) {
        return R.layout.opcao_cliente_list_card_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new OpcaoClienteViewHolder(view, this.onItemListener);
    }


    /**
     * Metodo que permite gerar as opções do cliente
     */
    public void gerarOpcoes(){

        this.registos.add(new OpcaoCliente(OPCAO_INFORMACAO, "Informação"));
        this.registos.add(new OpcaoCliente(OPCAO_CROSS_SELLING, "Cross Selling"));
        this.registos.add(new OpcaoCliente(OPCAO_SINISTRALIDADE, "Sinistralidade"));
        this.registos.add(new OpcaoCliente(OPCAO_EXTINTORES, "Extintores"));
        this.registos.add(new OpcaoCliente(OPCAO_EMAIL, "Email"));
        notifyDataSetChanged();
    }
}
