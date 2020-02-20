package com.vvm.sh.ui.agenda.adaptadores;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.OpcaoCliente;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

public class OpcaoClienteRecyclerAdapter extends ItemRecyclerAdapter {

    public static final int OPCAO_INFORMCACAO = 1;
    public static final int OPCAO_CROSS_SELLING = 2;
    public static final int OPCAO_SINISTRALIDADE = 3;

    private OnItemListener onItemListener;


    public OpcaoClienteRecyclerAdapter(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;

        gerarOpcoes();
    }

    @Override
    protected int obterLayout(int viewType) {
        return R.layout.opcao_cliente_list_card_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new OpcaoClienteViewHolder(view, this.onItemListener);
    }


    private void gerarOpcoes(){

        this.registos.add(new OpcaoCliente(OPCAO_INFORMCACAO, "Informação"));
        this.registos.add(new OpcaoCliente(OPCAO_CROSS_SELLING, "Cross Selling"));
        this.registos.add(new OpcaoCliente(OPCAO_SINISTRALIDADE, "Sinistralidade"));
    }
}
