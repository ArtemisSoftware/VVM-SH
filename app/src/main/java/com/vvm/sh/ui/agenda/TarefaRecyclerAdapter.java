package com.vvm.sh.ui.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemTarefaBinding;
import com.vvm.sh.ui.agenda.adaptadores.TarefaViewHolder;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;

import java.util.ArrayList;
import java.util.List;

public class TarefaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<TarefaDia> items = new ArrayList<>();
    private Context contexto;

    //private OnItemListener onItemListener;
    //private OnItemLongListener onItemLongListener;


    public TarefaRecyclerAdapter(Context contexto, List<TarefaDia> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTarefaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TarefaDia registo = items.get(position);
        ((TarefaViewHolder)holder).binding.setTarefaDia(registo);
        //((TarefaViewHolder)holder).binding.setListener((OnCrossSellingListener) contexto);

        ((TarefaViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<TarefaDia> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}
