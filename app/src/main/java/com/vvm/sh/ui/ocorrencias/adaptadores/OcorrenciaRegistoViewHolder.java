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

public class OcorrenciaRegistoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{


    public ItemOcorrenciaRegistoBinding binding;

    private OnOcorrenciaRegistoListener listener;


    public OcorrenciaRegistoViewHolder(@NonNull View itemView, OnOcorrenciaRegistoListener listener, boolean visualizar) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;

        if(visualizar == false) {
            itemView.setOnClickListener(this);
        }
        itemView.setOnCreateContextMenuListener(this);

    }



    @Override
    public void onClick(View v) {
        listener.OnOcorrenciaClick(binding.getOcorrencia());
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Opções");
        MenuItem Delete = menu.add(Menu.NONE, 1, 1, "Remover");
        Delete.setOnMenuItemClickListener(onEditMenu);
    }


    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {


            switch (item.getItemId()) {
                case 1:
                    listener.onRemoverClick(binding.getOcorrencia());
                    break;

            }
            return true;
        }
    };




}
