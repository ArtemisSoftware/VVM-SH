package com.vvm.sh.ui.atividadesPendentes;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

public class AtividadePendenteRecyclerAdapter extends ItemRecyclerAdapter {

    private OnItemListener onItemListener;


    public AtividadePendenteRecyclerAdapter(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected int obterLayout() {
        return R.layout.atividade_pendente_list_card_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view) {
        return new AtividadePendenteViewHolder(view, this.onItemListener);
    }
}
