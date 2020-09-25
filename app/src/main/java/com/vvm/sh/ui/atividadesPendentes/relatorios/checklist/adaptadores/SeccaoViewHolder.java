package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemChecklistAreaBinding;
import com.vvm.sh.databinding.ItemChecklistSeccaoBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class SeccaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ItemChecklistSeccaoBinding binding;

    private OnChecklistListener.OnItemListener listener;


    public SeccaoViewHolder(@NonNull View itemView, OnChecklistListener.OnItemListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        this.listener.OnItemClick(binding.getItem());
    }


}
