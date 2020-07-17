package com.vvm.sh.ui.crossSelling;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;

public class CrossSellingRecyclerAdapter extends ItemRecyclerAdapter {

    private OnCheckBoxItemListener onItemListener;

    public CrossSellingRecyclerAdapter(OnCheckBoxItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected int obterLayout(int viewType) {
        return R.layout.item_cross_selling;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view, int viewType) {
        return new CrossSellingViewHolder(view, this.onItemListener);
    }
}
