package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemRiscoBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class RiscoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

    public ItemRiscoBinding binding;

    private OnLevantamentoListener.OnRiscoListener listener;


    public RiscoViewHolder(@NonNull View itemView, OnLevantamentoListener.OnRiscoListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);

        if(PreferenciasUtil.agendaEditavel(itemView.getContext()) == true) {
            itemView.setOnCreateContextMenuListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        this.listener.OnRiscoClick(binding.getRisco());
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle(Sintaxe.Palavras.OPCOES);
        MenuItem remover = menu.add(Menu.NONE, 1, 1, Sintaxe.Palavras.REMOVER);

        remover.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {

            case 1:
                listener.OnRemoverClick(binding.getRisco());
                break;


        }
        return true;
    }

}