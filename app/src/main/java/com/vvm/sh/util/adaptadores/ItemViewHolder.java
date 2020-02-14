package com.vvm.sh.util.adaptadores;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.Tarefa;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Classe que representa a base para um item de uma RecyclerView
 *
 * <b>O layout deve obrigatoriamento ter uma Textview com o @id <i><u>txt_descricao</u></i></b>
 */
public abstract class ItemViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.txt_descricao)
    TextView txt_descricao;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void onBind(Item item){

        txt_descricao.setText(item.obterDescricao());
        preencherCampos(item);
    }


    //--------------------
    //Metodos abstratos
    //--------------------



    /**
     * Metodo que permite preencher os campos do view holder
     * @param item item com os dados
     */
    protected abstract void preencherCampos(Item item);
}
