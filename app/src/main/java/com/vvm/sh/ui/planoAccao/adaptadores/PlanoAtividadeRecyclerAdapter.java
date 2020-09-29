package com.vvm.sh.ui.planoAccao.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ItemPlanoAcaoProgramacaoBinding;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;

import java.util.ArrayList;
import java.util.List;

public class PlanoAtividadeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<AtividadeRegisto> items = new ArrayList<>();
    private Context contexto;
    private OnPlanoAtividadeListener listener;

    public PlanoAtividadeRecyclerAdapter(Context contexto, List<AtividadeRegisto> items, OnPlanoAtividadeListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.listener = onItemListener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){

            default:

                ItemPlanoAcaoProgramacaoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_plano_acao_programacao, parent, false);
                return new PlanoAtividadeViewHolder(binding.getRoot(), this.listener);

        }

        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AtividadeRegisto registo = items.get(position);
        ((PlanoAtividadeViewHolder) holder).binding.setAtividade(registo);

        ((PlanoAtividadeViewHolder) holder).binding.executePendingBindings();

    }


    @Override
    public int getItemViewType(int position) {

        AtividadeRegisto registo = items.get(position);
        return super.getItemViewType(registo.tipo);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<AtividadeRegisto> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

}