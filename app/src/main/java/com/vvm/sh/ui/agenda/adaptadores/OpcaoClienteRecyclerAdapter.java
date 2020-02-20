package com.vvm.sh.ui.agenda.adaptadores;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

public class OpcaoClienteRecyclerAdapter extends ItemRecyclerAdapter {

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
}
