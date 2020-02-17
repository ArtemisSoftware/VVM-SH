package com.vvm.sh.ui.crossSelling;

import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

public class CrossSellingRecyclerAdapter extends ItemRecyclerAdapter {

    @Override
    protected int obterLayout() {
        return R.layout.cross_selling_list_item;
    }

    @Override
    protected ItemViewHolder obterViewHolder(View view) {
        return new CrossSellingViewHolder(view);
    }
}
