package com.vvm.sh.ui.atividadesExecutadas.adaptadores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAtividadeExecutadaBinding;

public class AtividadeExecutadaViewHolder extends RecyclerView.ViewHolder {


    public ItemAtividadeExecutadaBinding binding;


    public AtividadeExecutadaViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
