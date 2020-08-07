package com.vvm.sh.ui.upload.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemPendenciaBinding;
import com.vvm.sh.databinding.ItemUploadBinding;
import com.vvm.sh.ui.upload.modelos.Pendencia;
import com.vvm.sh.ui.upload.modelos.Upload;

import java.util.ArrayList;
import java.util.List;

public class PendenciaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Pendencia> items = new ArrayList<>();
    private Context contexto;

    public PendenciaRecyclerAdapter(Context contexto, List<Pendencia> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemPendenciaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_pendencia, parent, false);
        return new PendenciaViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Pendencia registo = items.get(position);
        ((PendenciaViewHolder)holder).binding.setPendencia(registo);

        ((PendenciaViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Pendencia> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}

