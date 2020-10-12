package com.vvm.sh.ui.opcoes.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemTipoBinding;
import com.vvm.sh.databinding.ItemTipoChecklistBinding;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;

import java.util.ArrayList;
import java.util.List;

public class TipoChecklistRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<ResumoChecklist> items = new ArrayList<>();
    private Context contexto;
    private OnTipoListener onItemLongListener;

    public TipoChecklistRecyclerAdapter(Context contexto, List<ResumoChecklist> items, OnTipoListener onItemLongListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemLongListener = onItemLongListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTipoChecklistBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_tipo_checklist, parent, false);
        return new TipoChecklistViewHolder(binding.getRoot(), this.onItemLongListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ResumoChecklist registo = items.get(position);
        ((TipoChecklistViewHolder)holder).binding.setTipo(registo);
        ((TipoChecklistViewHolder)holder).binding.setListener((OnTipoListener) contexto);

        ((TipoChecklistViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ResumoChecklist> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
