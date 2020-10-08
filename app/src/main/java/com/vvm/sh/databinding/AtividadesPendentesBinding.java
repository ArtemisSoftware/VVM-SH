package com.vvm.sh.databinding;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.AtividadePendenteRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AtividadesPendentesBinding {

    @BindingAdapter({"atividadesPendentes", "onItemClick"})
    public static void setAtividadesPendentes(RecyclerView view, List<AtividadePendenteRegisto> items, OnAtividadePendenteListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        AtividadePendenteRecyclerAdapter adapter = (AtividadePendenteRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new AtividadePendenteRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"tipos_", "atividade"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, AtividadePendenteResultado resultado) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(resultado != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id == resultado.idAnomalia){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }


    @BindingAdapter({"atividade"})
    public static void setImagem(ImageView view, AtividadePendenteResultado atividadeResultado) {

        if (atividadeResultado == null) {
            view.setVisibility(View.GONE);
            return;
        }

        view.setVisibility(View.VISIBLE);

        if(atividadeResultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){
            view.setImageResource(R.drawable.ic_add_24dp);
        }
        else{
            view.setImageResource(R.drawable.ic_lorem_ipsum_24dp);
        }
    }


    @BindingAdapter({"baixaAtividade"})
    public static void setBaixaAtividade(Chip view, AtividadePendenteResultado atividadeResultado) {

        if (atividadeResultado == null) {
            view.setVisibility(View.GONE);
            return;
        }

        view.setVisibility(View.VISIBLE);

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        if(atividadeResultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){

            view.setText(view.getContext().getString(R.string.executada));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_executado);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_validado));
            view.getChipIcon().setTint(view.getContext().getResources().getColor(R.color.cor_branco));
        }
        else{
            view.setText(view.getContext().getString(R.string.nao_executado));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_nao_executado);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_nao_executado_24dp));
        }
    }


    @BindingAdapter({"completudeRelatorio"})
    public static void setCompletudeRelatorio(CircleImageView view, boolean relatorioCompleto) {


        if(relatorioCompleto == true){

            view.setCircleBackgroundColorResource(R.color.cor_completo);
            view.setImageResource(R.drawable.ic_executado_24dp);
        }
        else{
            view.setCircleBackgroundColorResource(R.color.cor_pendente);
            view.setImageResource(R.drawable.ic_nao_executado_24dp);
        }
    }

}
