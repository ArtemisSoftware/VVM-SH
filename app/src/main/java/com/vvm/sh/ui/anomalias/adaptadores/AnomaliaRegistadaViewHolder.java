package com.vvm.sh.ui.anomalias.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemAnomaliaBinding;
import com.vvm.sh.databinding.ItemAnomaliaRegistadaBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class AnomaliaRegistadaViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    public ItemAnomaliaRegistadaBinding binding;
    private OnAnomaliasListener listener;

    public AnomaliaRegistadaViewHolder(@NonNull View itemView,  OnAnomaliasListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.listener = listener;

        if(PreferenciasUtil.agendaEditavel(itemView.getContext()) == true) {
            itemView.setOnCreateContextMenuListener(this);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle(Sintaxe.Palavras.OPCOES);
        MenuItem Edit = menu.add(Menu.NONE, 1, 1, Sintaxe.Palavras.EDITAR);
        MenuItem Delete = menu.add(Menu.NONE, 2, 2, Sintaxe.Palavras.REMOVER);
        Edit.setOnMenuItemClickListener(this);
        Delete.setOnMenuItemClickListener(this);
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                listener.onEditarClick(binding.getAnomalia().resultado.id);
                break;

            case 2:
                listener.onRemoverClick(binding.getAnomalia());
                break;
        }
        return false;
    }
}
