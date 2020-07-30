package com.vvm.sh.ui.atividadesPendentes;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemFormandoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.atividadesPendentes.relatorios.OnFormacaoListener;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;

public class FormandoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener {


    public ItemFormandoBinding binding;

    private OnFormacaoListener listener;


    public FormandoViewHolder(@NonNull View itemView, OnFormacaoListener listener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
        binding.chkSelecionado.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        this.listener.OnSelecionadoCheck(binding.getFormando(), isChecked);
    }


    @Override
    public void onClick(View v) {
        this.listener.OnFormandoClick(binding.getFormando());
    }
}
