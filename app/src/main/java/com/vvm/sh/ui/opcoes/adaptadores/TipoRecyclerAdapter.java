package com.vvm.sh.ui.opcoes.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemTipoBinding;
import com.vvm.sh.ui.opcoes.modelos.Colecao;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;

import java.util.ArrayList;
import java.util.List;

public class TipoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<ResumoTipo> items = new ArrayList<>();
    private Context contexto;
    private OnTipoListener onItemLongListener;

    public TipoRecyclerAdapter(Context contexto, List<ResumoTipo> items, OnTipoListener onItemLongListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemLongListener = onItemLongListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTipoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_tipo, parent, false);
        return new TipoViewHolder(binding.getRoot(), this.onItemLongListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ResumoTipo registo = items.get(position);
        ((TipoViewHolder)holder).binding.setTipo(registo);
        ((TipoViewHolder)holder).binding.setListener((OnTipoListener) contexto);

        ((TipoViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ResumoTipo> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
