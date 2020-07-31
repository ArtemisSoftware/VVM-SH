package com.vvm.sh.ui.ocorrencias.adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemOcorrenciaBinding;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class OcorrenciaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemOcorrenciaBinding binding;

    private OnOcorrenciaListener onItemListener;


    public OcorrenciaViewHolder(@NonNull View itemView, OnOcorrenciaListener onItemListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onItemListener.OnOcorrenciaClick(binding.getOcorrencia());
    }
}
