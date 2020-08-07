package com.vvm.sh.ui.transferencias.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemUploadBinding;
import com.vvm.sh.ui.transferencias.modelos.Upload;

import java.util.ArrayList;
import java.util.List;

public class UploadRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Upload> items = new ArrayList<>();
    private Context contexto;

    public UploadRecyclerAdapter(Context contexto, List<Upload> items) {
        this.items = items;
        this.contexto = contexto;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemUploadBinding binding = DataBindingUtil.inflate(LayoutInflater.from(contexto), R.layout.item_upload, parent, false);
        return new UploadViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Upload registo = items.get(position);
        ((UploadViewHolder)holder).binding.setUpload(registo);

        ((UploadViewHolder)holder).binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void atualizar(List<Upload> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


}

