package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAvaliacaoIluminacaoBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class IluminacaoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

    public ItemAvaliacaoIluminacaoBinding binding;

    private OnAvaliacaoAmbientalListener listener;


    public IluminacaoViewHolder(@NonNull View itemView, OnAvaliacaoAmbientalListener listener) {
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
        this.listener.OnAvaliacaoClick(binding.getAvaliacao().resultado);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle(Sintaxe.Palavras.OPCOES);
        MenuItem Delete = menu.add(Menu.NONE, 1, 1, Sintaxe.Palavras.REMOVER);
        Delete.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                listener.onRemoverClick(binding.getAvaliacao().resultado);
                break;

        }
        return true;
    }

}
