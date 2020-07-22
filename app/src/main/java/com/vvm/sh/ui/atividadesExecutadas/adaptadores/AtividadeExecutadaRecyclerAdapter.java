package com.vvm.sh.ui.atividadesExecutadas.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAtividadeExecutadaBinding;
import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;
import com.vvm.sh.ui.atividadesExecutadas.adaptadores.AtividadeExecutadaViewHolder;
import com.vvm.sh.util.adaptadores.ItemRecyclerAdapter;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AtividadeExecutadaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AtividadeExecutada> items = new ArrayList<>();
    private Context contexto;


    public AtividadeExecutadaRecyclerAdapter(Context contexto, List<AtividadeExecutada> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemAtividadeExecutadaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_atividade_executada, parent, false);
        return new AtividadeExecutadaViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AtividadeExecutada registo = items.get(position);
        ((AtividadeExecutadaViewHolder)holder).binding.setAtividade(registo);

        ((AtividadeExecutadaViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<AtividadeExecutada> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
