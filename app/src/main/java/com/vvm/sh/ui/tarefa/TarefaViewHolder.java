package com.vvm.sh.ui.tarefa;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.Tarefa;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TarefaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.txt_titulo)
    TextView txt_titulo;

    @BindView(R.id.img_empresa)
    ImageView img_empresa;


    private OnItemListener onItemListener;


    public TarefaViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    public void onBind(Tarefa tarefa){

        txt_titulo.setText(tarefa.obterDescricao());

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .endConfig()
                .buildRound(tarefa.obterEmpresa(), Color.RED);
        img_empresa.setImageDrawable(drawable);

    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }

}