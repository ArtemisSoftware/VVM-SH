package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemChecklistBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class ItemViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

    public ItemChecklistBinding binding;

    private OnChecklistListener listener;


    public ItemViewHolder(@NonNull View itemView, OnChecklistListener listener) {
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
        this.listener.OnItemClick(binding.getItem());
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle(Sintaxe.Palavras.OPCOES);
        MenuItem editar = menu.add(Menu.NONE, 1, 1, Sintaxe.Palavras.EDITAR);
        MenuItem remover = menu.add(Menu.NONE, 2, 2, Sintaxe.Palavras.REMOVER);
        editar.setOnMenuItemClickListener(this);
        remover.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {

            case 1:
                listener.OnEditarClick(binding.getItem());
                break;

            case 2:
                listener.OnRemoverClick(binding.getItem());
                break;

        }
        return true;
    }

}
