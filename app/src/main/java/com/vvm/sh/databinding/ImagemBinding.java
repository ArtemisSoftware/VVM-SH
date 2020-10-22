package com.vvm.sh.databinding;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.ui.agenda.adaptadores.MarcacaoRecyclerAdapter;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.ui.imagens.adaptadores.BibliotecaAdapter;
import com.vvm.sh.ui.imagens.adaptadores.ImagemRecyclerAdapter;
import com.vvm.sh.ui.imagens.modelos.ImagemRegisto;

import java.util.List;

public class ImagemBinding {


    @BindingAdapter({"caminhoImagens"})
    public static void setImagensGaleria(GridView view, List<String> items/*, OnAgendaListener listener*/) {

        if(items == null){
            return;
        }

        int gridWidth = view.getContext().getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/3;
        view.setColumnWidth(imageWidth);


//        //use the grid adapter to adapter the images to gridview
//        GridImageAdapter adapter = new GridImageAdapter(getActivity(), R.layout.layout_grid_imageview, imgURLs);
//        gridView.setAdapter(adapter);

        BibliotecaAdapter adapter = (BibliotecaAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new BibliotecaAdapter(view.getContext(), items);
            view.setAdapter(adapter);
        }

    }


    @BindingAdapter({"imagens"})
    public static void setImagens(RecyclerView view, List<ImagemRegisto> items/*, OnAgendaListener listener*/) {

        if(items == null){
            return;
        }

        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();

        if(layoutManager == null){
            view.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        }

        ImagemRecyclerAdapter adapter = (ImagemRecyclerAdapter) view.getAdapter();

        if(adapter == null){
            adapter = new ImagemRecyclerAdapter(view.getContext(), items/*, listener*/);

            view.setAdapter(adapter);
        }
        else{
            adapter.atualizar(items);
        }

    }



    @BindingAdapter({"imagemL"})
    public static void setImagem(ImageView view, byte[] imagem) {

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                //.placeholder(defaultImageUrl)
                .error(R.drawable.ic_launcher_foreground);

        Glide.with(context)
                //.setDefaultRequestOptions(options)
                .asBitmap()
                .load(imagem)
                .into(view);



    }





}
