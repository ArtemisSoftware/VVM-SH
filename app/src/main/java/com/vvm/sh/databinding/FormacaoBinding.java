package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoRecyclerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.OnFormacaoListener;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

public class FormacaoBinding {


    @BindingAdapter({"tipos_", "acaoFormacao"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, AcaoFormacaoResultado resultado) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(resultado != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id == resultado.idDesignacao){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }


    @BindingAdapter({"tipos_", "genero"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, String sexo) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(sexo != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).codigo.equals(sexo)){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }



    @BindingAdapter({"formandos", "listener"})
    public static void setFormandos(RecyclerView view, List<Formando> items, OnFormacaoListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        FormandoRecyclerAdapter adapter = (FormandoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new FormandoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }
    }


    @BindingAdapter({"estado"})
    public static void setEstado(Chip view, boolean estado) {

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        if(estado == true){

            view.setText(view.getContext().getString(R.string.completo));

            chipDrawable.setChipBackgroundColorResource(R.color.bg_screen1);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.arrow));

        }
        else{
            view.setText(view.getContext().getString(R.string.incompleto));
            //view.setBackgroundColor(view.getContext().getResources().getColor(R.color.profilePrimaryDark));
            chipDrawable.setChipBackgroundColorResource(R.color.profilePrimaryDark);
        }
    }


}
