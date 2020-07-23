package com.vvm.sh.ui.atividadesPendentes;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAtividadePendenteBinding;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class AtividadePendenteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ItemAtividadePendenteBinding binding;

    private OnItemListener onItemListener;

    public AtividadePendenteViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }


}
