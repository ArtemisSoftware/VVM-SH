package com.vvm.sh.ui.imagens.adaptadores;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemBibliotecaBinding;
import com.vvm.sh.databinding.ItemImagemBinding;

public class ImagemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemImagemBinding binding;
    private OnImagemListener listener;

    public ImagemViewHolder(View itemView, OnImagemListener listener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.listener.OnImagemClick(binding.getImagem());
    }
}
