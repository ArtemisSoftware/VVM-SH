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

public class AnomaliaRegistadaViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener/*, MenuItem.OnMenuItemClickListener*/ {

    public ItemAnomaliaRegistadaBinding binding;
    private OnAnomaliasListener listener;

    public AnomaliaRegistadaViewHolder(@NonNull View itemView,  OnAnomaliasListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.listener = listener;
        //itemView.setOnClickListener(this);

        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Opções");
        MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Editar");
        MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Remover");
        Edit.setOnMenuItemClickListener(onEditMenu);
        Delete.setOnMenuItemClickListener(onEditMenu);
    }


    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {


            switch (item.getItemId()) {
                case 1:
                    listener.onEditarClick(binding.getAnomalia().id);
                    break;

                case 2:
                    listener.onRemoverClick(binding.getAnomalia());
                    break;
            }
            return true;
        }
    };




//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case 1:
//                this.listener.onEditarClick(binding.getAnomalia().id);
//                return true;
//            case 2:
//                this.listener.onRemoverClick(binding.getAnomalia().id);
//                return true;
//
//            default:
//                //return super.onContextItemSelected(item);
//                return false;
//        }
//    }
}
