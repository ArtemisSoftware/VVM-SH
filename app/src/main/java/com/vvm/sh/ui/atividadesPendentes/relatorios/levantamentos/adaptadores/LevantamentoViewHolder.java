package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemLevantamentoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.OnLevantamentoListener;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class LevantamentoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

    public ItemLevantamentoBinding binding;

    private OnLevantamentoListener.OnLevantamentoRegistoListener listener;


    public LevantamentoViewHolder(@NonNull View itemView, OnLevantamentoListener.OnLevantamentoRegistoListener listener) {
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
        this.listener.OnLevantamentoClick(binding.getLevantamento());
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle(Sintaxe.Palavras.OPCOES);
        MenuItem duplicar = menu.add(Menu.NONE, 1, 1, Sintaxe.Palavras.DUPLICAR_REGISTO);
        MenuItem remover = menu.add(Menu.NONE, 2, 2, Sintaxe.Palavras.REMOVER);
        MenuItem galeria = menu.add(Menu.NONE, 2, 2, Sintaxe.Palavras.GALERIA);

        duplicar.setOnMenuItemClickListener(this);
        remover.setOnMenuItemClickListener(this);
        galeria.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {

            case 1:
                listener.OnDuplicarClick(binding.getLevantamento());
                break;

            case 2:
                listener.OnRemoverClick(binding.getLevantamento());
                break;

            case 3:
                listener.OnGaleriaClick(binding.getLevantamento());
                break;

        }
        return true;
    }

}