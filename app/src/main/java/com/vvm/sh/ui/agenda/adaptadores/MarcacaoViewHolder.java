package com.vvm.sh.ui.agenda.adaptadores;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemMarcacaoBinding;

import butterknife.BindView;

public class MarcacaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{


    public ItemMarcacaoBinding binding;

    private OnAgendaListener listener;


    public MarcacaoViewHolder(@NonNull View itemView , OnAgendaListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.listener.onItemClick(binding.getMarcacao());
    }

    @Override
    public boolean onLongClick(View v) {
        this.listener.onItemLongPress(binding.getMarcacao());
        return true;
    }
}