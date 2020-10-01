package com.vvm.sh.ui.pesquisa.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ItemPesquisaBinding;
import com.vvm.sh.ui.pesquisa.OnPesquisaListener;

import java.util.ArrayList;
import java.util.List;

public class PesquisaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Tipo> items = new ArrayList<>();
    private Context contexto;
    private OnPesquisaListener.OnPesquisaRegistoListener onItemRegistoListener;
    private OnPesquisaListener.OnPesquisaSelecionadoListener onItemSelecionadoListener;

    public PesquisaRecyclerAdapter(Context contexto, List<Tipo> items, OnPesquisaListener.OnPesquisaRegistoListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemRegistoListener = onItemListener;
    }

    public PesquisaRecyclerAdapter(Context contexto, List<Tipo> items, OnPesquisaListener.OnPesquisaSelecionadoListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemSelecionadoListener = onItemListener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemPesquisaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_pesquisa, parent, false);

        if(onItemSelecionadoListener == null) {
            return new PesquisaViewHolder(binding.getRoot(), this.onItemRegistoListener);
        }
        else{
            return new PesquisaViewHolder(binding.getRoot(), this.onItemSelecionadoListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Tipo registo = items.get(position);
        ((PesquisaViewHolder) holder).binding.setTipo(registo);

        ((PesquisaViewHolder) holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Tipo> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

}