package com.vvm.sh.ui.informacaoSst.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemObrigacaoLegalBinding;
import com.vvm.sh.databinding.ItemTrabalhoRealizadoBinding;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.ui.registoVisita.adaptadores.OnRegistoVisitaListener;
import com.vvm.sh.ui.registoVisita.adaptadores.TrabalhoRealizadoViewHolder;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class ObrigacaoLegalRecyclerAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<ObrigacaoLegal> items = new ArrayList<>();
    private Context contexto;
    private OnInformacaoSstListener onItemListener;


    public ObrigacaoLegalRecyclerAdaptor(Context contexto, List<ObrigacaoLegal> items, OnInformacaoSstListener onItemListener) {
        this.items = items;
        this.contexto = contexto;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemObrigacaoLegalBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_obrigacao_legal, parent, false);
        return new ObrigacaoLegalViewHolder(binding.getRoot(), this.onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ObrigacaoLegal registo = items.get(position);
        ((ObrigacaoLegalViewHolder)holder).binding.setObrigacao(registo);
        ((ObrigacaoLegalViewHolder)holder).binding.setBloquear(PreferenciasUtil.agendaEditavel(contexto));
        ((ObrigacaoLegalViewHolder)holder).binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<ObrigacaoLegal> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }



}
