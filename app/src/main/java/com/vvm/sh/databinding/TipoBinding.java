package com.vvm.sh.databinding;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.ui.opcoes.adaptadores.OnTipoListener;
import com.vvm.sh.ui.opcoes.adaptadores.TipoChecklistRecyclerAdapter;
import com.vvm.sh.ui.opcoes.adaptadores.TipoNovoRecyclerAdapter;
import com.vvm.sh.ui.opcoes.adaptadores.TipoRecyclerAdapter;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Estados.Equipamentos.ESTADO_DEFINITIVO;
import static com.vvm.sh.util.constantes.Identificadores.Estados.Equipamentos.ESTADO_PENDENTE;
import static com.vvm.sh.util.constantes.Identificadores.Estados.Equipamentos.ESTADO_REJEITADO;

public class TipoBinding {


    @BindingAdapter({"tipos", "onLongClick"})
    public static void setTipos(RecyclerView view, List<ResumoTipo> items, OnTipoListener listener) {

        if(items == null){
            view.setVisibility(View.GONE);
            return;
        }

        if(items.size() == 0){
            view.setVisibility(View.GONE);
            return;
        }

        view.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TipoRecyclerAdapter adapter = (TipoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new TipoRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"tiposChecklist", "onLongClick"})
    public static void setTiposChecklist(RecyclerView view, List<ResumoChecklist> items, OnTipoListener listener) {

        if(items == null){
            view.setVisibility(View.GONE);
            return;
        }

        if(items.size() == 0){
            view.setVisibility(View.GONE);
            return;
        }

        view.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TipoChecklistRecyclerAdapter adapter = (TipoChecklistRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new TipoChecklistRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }



    @BindingAdapter({"tiposNovo"})
    public static void setTiposNovo(RecyclerView view, List<TipoNovo> items) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        TipoNovoRecyclerAdapter adapter = (TipoNovoRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new TipoNovoRecyclerAdapter(view.getContext(), items);
            view.setAdapter(adapter);
        }
    }



    @BindingAdapter({"estadoEquipamento"})
    public static void setEstadoEquipamento(Chip view, int estado) {

        String texto = "";
        int cor = R.color.cor_completo;

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        switch (estado){

            case ESTADO_DEFINITIVO:

                texto = view.getContext().getString(R.string.definitivo);
                cor = R.color.cor_equipamento_definitivo;
                break;

            case ESTADO_PENDENTE:

                texto = view.getContext().getString(R.string.pendente);
                cor = R.color.cor_pendente;
                break;

            case ESTADO_REJEITADO:

                texto = view.getContext().getString(R.string.rejeitado);
                cor = R.color.cor_equipamento_rejeitado;
                break;

            default:

                texto = view.getContext().getString(R.string.incompleto);
                cor = R.color.cor_incompleto;
                break;

        }

        view.setText(texto);
        chipDrawable.setChipBackgroundColorResource(cor);
    }






    @BindingAdapter({"tipos", "id"})
    public static void setTipos(MaterialSpinner view, Tipo [] registos, int id) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(id != 0) {

            for (int index = 0; index < registos.length; ++index) {

                if(registos[index].id == id){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }

}
