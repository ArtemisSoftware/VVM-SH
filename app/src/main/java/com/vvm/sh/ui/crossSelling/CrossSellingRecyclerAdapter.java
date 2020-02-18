package com.vvm.sh.ui.crossSelling;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

public class CrossSellingRecyclerAdapter extends ItemRecyclerAdapter {

    private OnCheckBoxItemListener onItemListener;

    public CrossSellingRecyclerAdapter(OnCheckBoxItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected int obterLayout() {
        return R.layout.cross_selling_list_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view) {
        return new CrossSellingViewHolder(view, this.onItemListener);
    }
}
