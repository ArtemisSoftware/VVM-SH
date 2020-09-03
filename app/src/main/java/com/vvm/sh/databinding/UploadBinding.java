package com.vvm.sh.databinding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.transferencias.adaptadores.PendenciaRecyclerAdapter;
import com.vvm.sh.ui.transferencias.adaptadores.UploadRecyclerAdapter;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;

import java.util.List;

public class UploadBinding {

    @BindingAdapter({"uploads"})
    public static void setUploads(RecyclerView view, Recurso recurso) {

        if(recurso == null){
            return;
        }

        List<Upload> items = (List<Upload>) recurso.dados;

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        UploadRecyclerAdapter adapter = (UploadRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new UploadRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }


    @BindingAdapter({"pendencias"})
    public static void setPendencias(RecyclerView view, Recurso recurso) {


        if(recurso == null){
            return;
        }

        List<Pendencia> items = (List<Pendencia>) recurso.dados;

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }

        PendenciaRecyclerAdapter adapter = (PendenciaRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new PendenciaRecyclerAdapter(view.getContext(), items);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }

    @BindingAdapter({"textoUpload"})
    public static void setTextoUpload(TextView view, List<Resultado> items) {

        if(items == null){
            return;
        }

        String texto = "";

        for(Resultado resultado : items){
            texto += ResultadoId.obterDescricao(resultado.id).toString()  + "\n";
        }

        view.setText(texto);
    }

}
