package com.vvm.sh.ui.tarefa.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOpcaoClienteBinding;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;

import java.util.ArrayList;
import java.util.List;

public class OpcaoClienteRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<OpcaoCliente> items = new ArrayList<>();
    private Context contexto;
    private OnTarefaListener onItemListener;

    public OpcaoClienteRecyclerAdapter(Context contexto, List<OpcaoCliente> items, OnTarefaListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOpcaoClienteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_opcao_cliente, parent, false);
        return new OpcaoClienteViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OpcaoCliente registo = items.get(position);
        ((OpcaoClienteViewHolder)holder).binding.setOpcao(registo);
        ((OpcaoClienteViewHolder)holder).binding.setListener((OnTarefaListener) contexto);

        ((OpcaoClienteViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<OpcaoCliente> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

}
