package com.vvm.sh.ui.crossSelling;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemCrossSellingBinding;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;

import butterknife.BindView;

public class CrossSellingViewHolder extends RecyclerView.ViewHolder implements CheckBox.OnCheckedChangeListener {


    ItemCrossSellingBinding binding;
    private OnCheckBoxItemListener onItemListener;


    public CrossSellingViewHolder(View itemView, OnCheckBoxItemListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.chkBoxItem.setOnCheckedChangeListener(this);
        //--itemView.setOnLongClickListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        onItemListener.onItemChecked(getAdapterPosition(), isChecked);
    }
}
