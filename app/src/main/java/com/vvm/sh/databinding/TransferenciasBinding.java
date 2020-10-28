package com.vvm.sh.databinding;

import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.transferencias.adaptadores.PendenciaRecyclerAdapter;
import com.vvm.sh.ui.transferencias.adaptadores.UploadRecyclerAdapter;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class TransferenciasBinding {

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
    public static void setPendencias(RecyclerView view, List<Pendencia> items) {


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

    @BindingAdapter({"pendencias", "uploads"})
    public static void setTextoUpload(TextView view, List<Pendencia> pendencias, List<Upload> uploads) {

        if(pendencias != null){

            if(pendencias.size() != 0){
                view.setText(view.getContext().getString(R.string.tarefas_pendentes));
            }
        }


        if(uploads != null){

            if(uploads.size() != 0){
                view.setText(view.getContext().getString(R.string.tarefas_upload));
            }
            else{

                view.setVisibility(View.GONE);
            }
        }

    }




    @BindingAdapter({"sincronizacao"})
    public static void setSincronizacao(Chip view, int sincronizacao) {

        ChipDrawable chipDrawable = (ChipDrawable) view.getChipDrawable();

        if(sincronizacao == Identificadores.Sincronizacao.SINCRONIZADO){

            view.setText(view.getContext().getString(R.string.sincronizado));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_sincronizado);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_sincronizado_24dp));

        }
        else{
            view.setText(view.getContext().getString(R.string.nao_sincronizado));
            chipDrawable.setChipBackgroundColorResource(R.color.cor_nao_sincronizado);
            view.setChipIcon(view.getContext().getResources().getDrawable(R.drawable.ic_sincronizado_24dp));
        }
    }


}
