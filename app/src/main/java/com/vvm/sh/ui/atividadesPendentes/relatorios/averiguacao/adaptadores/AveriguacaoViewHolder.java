package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAveriguacaoAreaBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class AveriguacaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {


    public ItemAveriguacaoAreaBinding binding;
    private OnAveriguacaoListener onItemListener;


    public AveriguacaoViewHolder(View itemView, OnAveriguacaoListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);

        if(PreferenciasUtil.agendaEditavel(itemView.getContext()) == true) {
            itemView.setOnCreateContextMenuListener(this);
        }
    }


    @Override
    public void onClick(View v) {

        onItemListener.OnItemClick(binding.getAveriguacao());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle(Sintaxe.Palavras.OPCOES);
        MenuItem editar = menu.add(Menu.NONE, 1, 1, Sintaxe.Alertas.MARCAR_QUESTOES_NAO_IMPLEMENTADOS);
        editar.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {

            case 1:
                this.onItemListener.OnNaoImplementados(binding.getAveriguacao());
                break;


        }
        return true;
    }
}