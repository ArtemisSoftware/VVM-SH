package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.databinding.ItemOcorrenciaRegistoBinding;
import com.vvm.sh.util.constantes.Sintaxe;

public class OcorrenciaRegistoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{


    public ItemOcorrenciaRegistoBinding binding;

    private OnOcorrenciaRegistoListener listenerRegisto;
    private OnOcorrenciaListener listenerOcorrencia;


    public OcorrenciaRegistoViewHolder(@NonNull View itemView, OnOcorrenciaRegistoListener listener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.listenerRegisto = listener;

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }


    public OcorrenciaRegistoViewHolder(@NonNull View itemView, OnOcorrenciaListener listener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.listenerOcorrencia = listener;

        itemView.setOnCreateContextMenuListener(this);
    }



    @Override
    public void onClick(View v) {
        listenerRegisto.OnOcorrenciaClick(binding.getOcorrencia());
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
                if(listenerRegisto != null) {
                    listenerRegisto.onRemoverClick(binding.getOcorrencia());
                }
                else{
                    listenerOcorrencia.onRemoverClick(binding.getOcorrencia());
                }
                break;

        }
        return true;
    }
}
