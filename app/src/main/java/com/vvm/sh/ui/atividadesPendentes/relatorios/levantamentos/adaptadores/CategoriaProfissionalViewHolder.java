package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemCategoriaProfissionalBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class CategoriaProfissionalViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

    public ItemCategoriaProfissionalBinding binding;

    private OnLevantamentoListener.OnCategoriaProfissionalListener listener;


    public CategoriaProfissionalViewHolder(@NonNull View itemView, OnLevantamentoListener.OnCategoriaProfissionalListener listener) {
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
        this.listener.OnCategoriaProfissionalClick(binding.getCategoria());
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
                listener.OnRemoverClick(binding.getCategoria());
                break;


        }
        return true;
    }

}