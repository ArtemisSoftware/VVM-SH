package com.vvm.sh.databinding;

import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.tarefa.adaptadores.OnTarefaListener;
import com.vvm.sh.ui.tarefa.adaptadores.OpcaoClienteRecyclerAdapter;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;

import java.util.List;

public class TarefaBinding {

    @BindingAdapter({"opcoesCliente", "onItemClick"})
    public static void setOpcoesCliente(RecyclerView view, List<OpcaoCliente> items, OnTarefaListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        OpcaoClienteRecyclerAdapter adapter = (OpcaoClienteRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new OpcaoClienteRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }



    @BindingAdapter({"viewEnable"})
    public static void setEnable(LinearLayout view, boolean ativar) {

        for (int i = 0; i < view.getChildCount(); i++) {
            View child = view.getChildAt(i);
            child.setEnabled(ativar);
        }
    }


    @BindingAdapter({"tipos_", "email"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, EmailResultado resultado) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(resultado != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id == resultado.idAutorizacao){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }


}
