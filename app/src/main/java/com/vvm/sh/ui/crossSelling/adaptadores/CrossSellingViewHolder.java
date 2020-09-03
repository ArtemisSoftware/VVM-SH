package com.vvm.sh.ui.crossSelling.adaptadores;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemCrossSellingBinding;

public class CrossSellingViewHolder extends RecyclerView.ViewHolder implements CheckBox.OnClickListener {


    ItemCrossSellingBinding binding;
    private OnCrossSellingListener onItemListener;


    public CrossSellingViewHolder(View itemView, OnCrossSellingListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        binding.chkBoxItem.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemChecked(binding.getCrossSelling(), ((CheckBox) v).isChecked());
    }
}
