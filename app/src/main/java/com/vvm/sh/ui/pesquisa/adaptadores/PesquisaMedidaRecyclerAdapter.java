package com.vvm.sh.ui.pesquisa.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemPesquisaMedidaBinding;
import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class PesquisaMedidaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Medida> items = new ArrayList<>();
    private Context contexto;
    private OnPesquisaListener.OnPesquisaMedidaListener onItemListener;


    public PesquisaMedidaRecyclerAdapter(Context contexto, List<Medida> items, OnPesquisaListener.OnPesquisaMedidaListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemPesquisaMedidaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_pesquisa_medida, parent, false);
        return new PesquisaMedidaViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Medida registo = items.get(position);
        ((PesquisaMedidaViewHolder)holder).binding.setMedida(registo);
        ((PesquisaMedidaViewHolder)holder).binding.setBloquear(PreferenciasUtil.agendaEditavel(contexto));
        ((PesquisaMedidaViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Medida> items_){
//        this.items.clear();
//        this.items.addAll(items_);
        this.items = items_;
        notifyDataSetChanged();
    }



}
