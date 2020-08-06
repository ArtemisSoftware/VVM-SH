package com.vvm.sh.databinding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.upload.adaptadores.UploadRecyclerAdapter;
import com.vvm.sh.ui.upload.modelos.Upload;

import java.util.List;

public class UploadBinding {

    @BindingAdapter({"uploads"})
    public static void setUploads(RecyclerView view, List<Upload> items) {

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



    @BindingAdapter({"textoUpload"})
    public static void setTextoUpload(TextView view, List<Resultado> items) {

        if(items == null){
            return;
        }

        String texto = "";

        for(Resultado resultado : items){
            texto += resultado.id  + "\n";
        }

        view.setText(texto);
    }

}
