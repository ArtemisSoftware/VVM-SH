package com.vvm.sh.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.ui.quadroPessoal.adaptadores.ColaboradorRecyclerAdapter;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.ui.quadroPessoal.adaptadores.OnColaboradorListener;

import java.util.List;

public class QuadroPessoalBinding {

    @BindingAdapter({"colaboradores" , "listener"})
    public static void setColaboradores(RecyclerView view, List<ColaboradorRegisto> items, OnColaboradorListener listener) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        ColaboradorRecyclerAdapter adapter = (ColaboradorRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new ColaboradorRecyclerAdapter(view.getContext(), items, listener);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"estadoAdmissao"})
    public static void setEstadoAdmissao(ImageView view, int estadoAdmissao) {

        switch (estadoAdmissao){

            default:

                break;
        }
    }


}
