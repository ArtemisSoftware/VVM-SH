package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOcorrenciaHistoricoBinding;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaHistorico;

import java.util.ArrayList;
import java.util.List;

public class OcorrenciaHistoricoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<OcorrenciaHistorico> items = new ArrayList<>();
    private Context contexto;

    public OcorrenciaHistoricoRecyclerAdapter(Context contexto, List<OcorrenciaHistorico> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOcorrenciaHistoricoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_ocorrencia_historico, parent, false);
        return new OcorrenciaHistoricoViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OcorrenciaHistorico registo = items.get(position);
        ((OcorrenciaHistoricoViewHolder)holder).binding.setOcorrencia(registo);
        ((OcorrenciaHistoricoViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<OcorrenciaHistorico> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

}
