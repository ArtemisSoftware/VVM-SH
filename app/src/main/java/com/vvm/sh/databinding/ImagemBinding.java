package com.vvm.sh.databinding;

import android.widget.GridView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.ui.agenda.adaptadores.MarcacaoRecyclerAdapter;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.ui.imagens.adaptadores.BibliotecaAdapter;
import com.vvm.sh.ui.imagens.adaptadores.ImagemRecyclerAdapter;

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
    public static void setImagens(RecyclerView view, List<ImagemResultado> items/*, OnAgendaListener listener*/) {

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


}
